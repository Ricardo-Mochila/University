#include <stdlib.h>
#include <string.h>
#include "ya.h"

d_decls d_decls_new(d_decl decl, d_decls decls)
{
    d_decls ret = malloc(sizeof(struct d_decls_));
    if (decls)
    {
        ret->kind = DECLS_LIST;
    }
    else
    {
        ret->kind = DECLS_SINGLE;
    }
    ret->u.decl = decl;
    ret->u.decls = decls;
    return ret;
}

d_decl d_decl_new_init(i_ids id_list, t_type type)
{
    d_decl ret = malloc(sizeof(struct d_decl_));

    ret->kind = DECL_INIT;
    ret->u.init.id_list = id_list;
    ret->u.init.type = type;

    return ret;
}

d_decl d_decl_new_assign(i_ids id_list, t_type type, e_exp exp)
{
    d_decl ret = malloc(sizeof(struct d_decl_));

    ret->kind = DECL_ASSIGN;
    ret->u.assign.id_list = id_list;
    ret->u.assign.type = type;
    ret->u.assign.exp = exp;

    return ret;
}

d_decl d_decl_new_func(char *id, a_argdefs argdefs, t_type type, s_stms stms)
{
    d_decl ret = malloc(sizeof(struct d_decl_));

    ret->kind = DECL_FUNC;
    ret->u.func.id = id;
    ret->u.func.type = type;
    ret->u.func.argdefs = argdefs;
    ret->u.func.stms = stms;

    return ret;
}

d_decl d_decl_new_define(char *id, t_type type)
{
    d_decl ret = malloc(sizeof(struct d_decl_));

    ret->kind = DECL_DEFINE;
    ret->u.define.id = id;
    ret->u.define.type = type;

    return ret;
}

a_argdefs a_argdefs_new(a_argdef argdef, a_argdefs argdefs)
{
    a_argdefs ret = malloc(sizeof(struct a_argdefs_));

    ret->u.argdef = argdef;
    ret->u.argdefs = argdefs;
    

    if (argdefs)
        ret->kind = ARGDEFS_LIST;
    else
        ret->kind = ARGDEFS_SINGLE;

    return ret;
}

a_argdef a_argdef_new(char *id, t_type type)
{
    a_argdef ret = malloc(sizeof(struct a_argdef_));

    ret->id = id;
    ret->type = type;

    return ret;
}

a_args a_args_new(e_exp exp, a_args args)
{
    a_args ret = malloc(sizeof(struct a_args_));
    ret->u.exp = exp;
    ret->u.args = args;

    if (args)
        ret->kind = ARGS_LIST;
    else
        ret->kind = ARGS_SINGLE;
    return ret;
}

i_ids i_ids_new(char *id, i_ids id_list)
{
    i_ids ret = malloc(sizeof(struct i_ids_));
    ret->id = id;
    ret->id_list = id_list;
    return ret;
    
}

s_stms s_stms_new(s_stm stm, s_stms stms)
{
    s_stms ret = malloc(sizeof(struct s_stms_));

    ret->u.stm = stm;
    ret->u.stms = stms;

    if (stms)
        ret->kind = STMS_LIST;
    else
        ret->kind = STMS_SINGLE;

    return ret;
}

s_stm s_stm_new_decl(d_decl decl)
{
    s_stm ret = malloc(sizeof(struct s_stm_));

    ret->kind = STM_DECL;
    ret->u.decl = decl;

    return ret;
}
s_stm s_stm_new_exp(e_exp exp)
{
    s_stm ret = malloc(sizeof(struct s_stm_));
    ret->kind = STM_EXP;
    ret->u.exp = exp;
    return ret;
}
s_stm s_stm_new_return(e_exp exp)
{
    s_stm ret = malloc(sizeof(struct s_stm_));

    ret->kind = STM_RETURN;
    ret->u.returnType = exp;

    return ret;
}
s_stm s_stm_new_if_else(e_exp exp, s_stms then_stms, s_stms else_stms)
{
    s_stm ret = malloc(sizeof(struct s_stm_));

    ret->kind = STM_IF_ELSE;
    ret->u.if_else.exp = exp;
    ret->u.if_else.then_stms = then_stms;
    ret->u.if_else.else_stms = else_stms;

    return ret;
}
s_stm s_stm_new_while(e_exp exp, s_stms while_stms)
{
    s_stm ret = malloc(sizeof(struct s_stm_));

    ret->kind = STM_WHILE;
    ret->u.while_.exp = exp;
    ret->u.while_.while_stms = while_stms;

    return ret;
}

s_stm s_stm_new_next()
{
    s_stm ret = malloc(sizeof(struct s_stm_));

    ret->kind = STM_NEXT;

    return ret;
}

t_type t_new_type(int kind, char *type)
{
    t_type ret = malloc(sizeof(struct t_type_));

    ret->kind = kind;
    ret->u.type = type;

    return ret;
}
t_type t_new_id(char *id)
{
    t_type ret = malloc(sizeof(struct t_type_));

    ret->kind = T_ID;
    ret->u.id = id;

    return ret;
}
t_type t_new_array(t_type type, int lit)
{
    t_type ret = malloc(sizeof(struct t_type_));

    ret->kind = T_ARRAY;
    ret->u.array.type = type;
    ret->u.array.lit = lit;

    return ret;
}


l_lit l_lit_new_intlit(int lit)
{
    l_lit ret = malloc(sizeof(struct l_lit_));

    ret->kind = LIT_INTLIT;
    ret->u.it = lit;

    return ret;
}
l_lit l_lit_new_floatlit(double lit)
{
    l_lit ret = malloc(sizeof(struct l_lit_));

    ret->kind = LIT_FLOATLIT;
    ret->u.fit = lit;

    return ret;
}
l_lit l_lit_new_strlit(char *lit)
{
    l_lit ret = malloc(sizeof(struct l_lit_));

    ret->kind = LIT_STRLIT;
    ret->u.cit = lit;

    return ret;
}
l_lit l_lit_new_boollit(int lit)
{
    l_lit ret = malloc(sizeof(struct l_lit_));

    ret->kind = LIT_BOOLLIT;
    ret->u.bit = lit;

    return ret;
}

e_exp e_exp_new_binop(char op[], e_exp arg1, e_exp arg2)
{
    e_exp ret = malloc(sizeof(struct e_exp_));

    ret->kind = EXP_BINOP;
    strcpy(ret->u.binop.op , op);
    ret->u.binop.arg1 = arg1;
    ret->u.binop.arg2 = arg2;

    return ret;
}
e_exp e_exp_new_unop(char op[], e_exp arg1)
{
    e_exp ret = malloc(sizeof(struct e_exp_));

    ret->kind = EXP_UNOP;
    strcpy(ret->u.unop.op , op);
    ret->u.unop.arg1 = arg1;

    return ret;
}
e_exp e_exp_new_assign(e_exp exp, e_exp value)
{
    e_exp ret = malloc(sizeof(struct e_exp_));

    ret->kind = EXP_ASSIGN;
    ret->u.assign.id = exp;
    ret->u.assign.value = value;

    return ret;
}
e_exp e_exp_new_lit(l_lit lit)
{
    e_exp ret = malloc(sizeof(struct e_exp_));

    ret->kind = EXP_LIT;
    ret->u.lit = lit;

    return ret;
}
e_exp e_exp_new_id(char *id)
{
    e_exp ret = malloc(sizeof(struct e_exp_));

    ret->kind = EXP_ID;
    ret->u.id = id;

    return ret;

}
e_exp e_exp_new_array(e_exp exp, int intlit)
{
    e_exp ret = malloc(sizeof(struct e_exp_));

    ret->kind = EXP_ARRAY;
    ret->u.array.exp = exp;
    ret->u.array.lit = intlit;

    return ret;
}
e_exp e_exp_new_function(char *id, a_args args)
{
    e_exp ret = malloc(sizeof(struct e_exp_));

    ret->kind = EXP_FUNC;
    ret->u.func.id = id;
    ret->u.func.args = args;

    return ret;
}

