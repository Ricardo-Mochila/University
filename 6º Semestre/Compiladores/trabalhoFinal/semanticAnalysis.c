
#include <stdio.h>
#include <stdbool.h>
#include "data_structures/structures.h"
#include "data_structures/symbolTable.h"


#define EBINOPCHECK 1
#define EUNOPCHECK 2
#define EARRAYCHECK 3
#define DASSIGNCHECK 4



void semanticAnalysis(a){
    d_decl_ant(a);
}

void errorPrint(){
    puts("Semantic Error!");
}

bool check_types(int op, int type1, int type2)
{
    switch(op){
        case EBINOPCHECK:
            if(type1 == T_INT && type2 == T_INT)
            {
                return true;
            }
            else if(type1 == T_FLOAT && type2 == T_FLOAT)
            {
                return true;
            }
            if(type1 == T_INT && type2 == T_FLOAT)
            {
                puts("Cannot opetate between an integer and a float");
                return false;
            }
            else if(type1 == T_FLOAT && type2 == T_INT)
            {
                puts("Cannot opetate between an integer and a float");
                return false;
            }
            else
            {
               errorPrint();
            }
            break;
            
        case EUNOPCHECK:
            if(type1 != NULL && type1 ==T_BOOL)
            {
                return true;
            }
            
        case EARRAYCHECK:
            if( type1 != EXP_LIT)
            {
                errorPrint();
            }
            break;

        case DASSIGNCHECK:
            if(type1 == T_INT && type2 == LIT_INTLIT){
                return true;
            }
            else if(type1 == T_FLOAT && type2 == LIT_FLOATLIT){
                return true;
            }
            else if(type1 == T_STRING && type2 == LIT_STRLIT){
                return true;
            }
            else if(type1 == T_BOOL && type2 == LIT_BOOLLIT){
                return true;
            }
            else
            {
                puts("Error between types");
            }
            
    }
}



void d_decl_ant(d_decl d)
{
    int i = 0;
    int j = 0;
    switch(d->kind) 
    {    
        case DECL_INIT:
            
            while(& d -> u.init.id_list[i] != NULL)
            {
                ST_insert(& d -> u.init.id_list[i], d -> u.init.type, global);
                i++;
            } 
            
        case DECL_ASSIGN: 
            
            while(& d -> u.assign.id_list[j] != NULL)
            {
                ST_insert(& d->u.assign.id_list[j], d->u.assign.type,  global);
                j++;
            } 
            check_types(DASSIGNCHECK, d->u.assign.type -> kind, d->u.assign.exp->u.lit->kind ); 
        break;
    } 
}

void d_decls_ant(d_decls ds)
{
    switch (ds->kind)
    {
        case DECLS_SINGLE:
            d_decl_ant(ds -> u.decl);
            break;
        case DECLS_LIST:
            d_decls_ant(ds -> u.decls);
            break;
        default:
            errorPrint();
            break;
    }
}

e_exp t_exp_ant(e_exp e) 
{
    ST_Data lval_type;
    switch(e->kind) { 
        case EXP_ASSIGN:
            lval_type = ST_lookup_local(e->u.assign.id); 
            if(lval_type == NULL){
                lval_type = ST_lookup(e->u.assign.id); 
            } 
            //check_types(lval_type, e->u.assign.exp);
            break; 
            
        case EXP_BINOP: 
            //check_types(EBINOPCHECK ,e -> u.binop.arg1 -> kind,e -> u.binop.arg2 -> kind); 
            break;

        case EXP_UNOP:
            check_types(EUNOPCHECK ,e -> u.unop.arg1 -> kind, NULL); 
            break; 
           
        case EXP_ARRAY:
            check_types(EARRAYCHECK,e -> u.array.exp-> kind, NULL);  
            break;
    }
}
