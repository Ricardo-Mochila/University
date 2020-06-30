#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include "data_structures/symbolTable.h"
#include "semanticAnalysis.h"


#define EBINOPCHECK 1
#define EUNOPCHECK 2
#define EARRAYCHECK 3
#define DASSIGNCHECK 4

char * funcId;

void s_stm_ant(s_stm ds);


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
            if(type1 != 0 && type1 ==T_BOOL)
            {
                return true;
            }
            errorPrint();
            return false;
            
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
                return false;
            }
            break;
        default:
            errorPrint();
            return false;
    }
    return false;

}

bool check_types_small(int type1, int type2){

    return type1 == type2;
}



void d_decl_ant(d_decl d)
{

    ST_Data data = malloc(sizeof(struct st_data_));

    switch(d->kind) 
    {    
        case DECL_INIT:

            data -> u.var.yatype = d->u.init.type ;
            data -> u.var.kind = VARloc;

            data -> kind = ST_VAR;

            while(d -> u.init.id_list != NULL)
            {
                ST_insert(d -> u.init.id_list -> id, data, global);
                d -> u.init.id_list = d -> u.init.id_list ->id_list;
            } 
            break;
            
        case DECL_ASSIGN: 

            data -> u.var.yatype = d->u.assign.type ;
            data -> u.var.kind = VARloc;

            data -> kind = ST_VAR;


            while(d -> u.assign.id_list != NULL)
            {
                ST_insert(d -> u.assign.id_list -> id, data,  global);
                d -> u.assign.id_list = d -> u.assign.id_list ->id_list;
            } 
            check_types(DASSIGNCHECK, d->u.assign.type -> kind, d->u.assign.exp->u.lit->kind ); 
            break;

        case DECL_DEFINE: 

            data -> kind = ST_TYPE;
            data -> u.type = d->u.define.type -> u.type;
            ST_insert(d->u.define.id, data,  global);            
            break;    

        case DECL_FUNC: 
            
            data -> kind = ST_FUNC;
            data -> u.func.arg = d->u.func.argdefs;
            data -> u.func.yatype = d -> u.func.type;

            ST_insert(d->u.func.id, data,  global);

            funcId = d->u.func.id;

            //Supostamente Aqui Deveria Posicionar-se Todo Um Codigo Relativo A Um Novo Scope

            while( d -> u.func.argdefs  != NULL){
                ST_Data argsData = malloc(sizeof(struct st_data_));

                argsData -> kind = ST_VAR;
                argsData -> u.var.kind = VARarg;
                argsData -> u.var.yatype = d->u.func.argdefs -> u.argdef -> type;

                ST_insert(d->u.func.argdefs -> u.argdef -> id, argsData,  global);

                d -> u.func.argdefs = d->u.func.argdefs -> u.argdefs;

            }

            break;
            
        default:
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
            d_decl_ant(ds -> u.decl);
            d_decls_ant(ds -> u.decls);
            break;
        default:
            errorPrint();
            break;
    }
}


void s_stms_ant(s_stms ds)
{
    switch (ds->kind)
    {
        case STMS_SINGLE:
            s_stm_ant(ds -> u.stm);
            break;
        case STMS_LIST:
            s_stm_ant(ds -> u.stm);
            s_stms_ant(ds -> u.stms);
            break;
        default:
            errorPrint();
            break;
    }
}



void s_stm_ant(s_stm ds)
{
    ST_Data data = malloc(sizeof(struct st_data_));

    switch (ds->kind)
    {
        case STM_DECL:
            d_decl_ant(ds -> u.decl);
            break;            
        case STM_EXP:
            e_exp_ant(ds -> u.exp);
            break;
        case STM_RETURN:

            data = ST_lookup(funcId);
            if(!check_types_small(data -> u.func.yatype -> kind, e_exp_ant(ds -> u.returnType)) )
            {
                errorPrint();
            }
            break;

        case STM_IF_ELSE:

            if(!check_types_small(T_BOOL ,e_exp_ant(ds -> u.if_else.exp))){
                errorPrint();
                break;
            }

            s_stms_ant(ds -> u.if_else.then_stms);
            if(ds -> u.if_else.else_stms != NULL){
                s_stms_ant(ds -> u.if_else.else_stms);
            }
            break;

        case STM_WHILE:
            if(!check_types_small(T_BOOL ,e_exp_ant(ds -> u.while_.exp))){
                errorPrint();
                break;
            }

            s_stms_ant(ds -> u.while_.while_stms);

        case STM_NEXT:
            break;
        default:
            errorPrint();
            break;
    }
}

int e_exp_ant(e_exp exp)
{
    switch (exp -> kind)
    {
    case EXP_LIT:
        return exp -> u.lit ->kind;
        break;
    case EXP_ID:
        return ST_lookup(exp -> u.id)-> u.var.yatype->kind;
        break;
    case EXP_BINOP:
        check_types(EBINOPCHECK, e_exp_ant(exp -> u.binop.arg1), e_exp_ant(exp -> u.binop.arg2));
        return e_exp_ant(exp -> u.binop.arg1);
        break;
    case EXP_UNOP:
        check_types(EUNOPCHECK, e_exp_ant(exp -> u.unop.arg1), 0);
        return e_exp_ant(exp -> u.unop.arg1);
        break;
    case EXP_ASSIGN:
        check_types_small( ST_lookup(exp -> u.assign.id) -> u.var.yatype->kind,e_exp_ant(exp -> u.assign.value));
        return e_exp_ant(exp -> u.assign.value);
        break;
    case EXP_FUNC:

        while(exp -> u.func.args != NULL && ST_lookup(exp -> u.func.id) ->u.func.arg ->u.argdefs->u.argdef != NULL){
            
            check_types_small(ST_lookup(exp -> u.func.id) ->u.func.arg ->u.argdefs->u.argdef ->type->kind,  exp -> u.func.args ->kind);
            ST_lookup(exp -> u.func.id) ->u.func.arg ->u.argdefs = ST_lookup(exp -> u.func.id) ->u.func.arg ->u.argdefs->u.argdefs ;
            exp -> u.func.args = exp -> u.func.args ->u.args;
        }
        if(exp -> u.func.args != NULL || ST_lookup(exp -> u.func.id) ->u.func.arg ->u.argdefs->u.argdef != NULL){
            errorPrint();
            break;
        }

        break;
    case EXP_ARRAY:
        return ST_lookup(exp ->u.id)->u.var.yatype -> kind;
        break;
    
    default:
        errorPrint();
    }
    return -1;
}


void semanticAnalysis(d_decls a){
    d_decls_ant(a);
}