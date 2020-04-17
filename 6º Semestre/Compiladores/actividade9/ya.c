#include <stdlib.h>
#include <string.h>
#include "ya.h"

struct l_lit_
{
    enum
    {
        LIT_INTLIT,
        LIT_FLOATLIT,
        LIT_STRLIT,
        LIT_BOOLLIT
    } kind;

    union {
        int it;
        float fit;
        char *cit;
        int bit;
    } u;
};

struct t_type_
{
    enum
    {
        T_INT,
        T_FLOAT,
        T_STRING,
        T_BOOL,
        T_VOID,
        T_ID,
        T_ARRAY
    } kind;

    union {
        char *type;
        char *id;
        struct
        {
            t_type type;
            int lit;
        } array;

    } u;
};

struct a_args_
{
    enum
    {
        ARGS_SINGLE,
        ARGS_LIST
    } kind;

    struct
    {
        e_exp exp;
        a_args args;
    } u;
};

struct i_ids_
{
    char *id;
    i_ids id_list;
};

struct e_exp_
{
    enum
    {
        EXP_LIT,
        EXP_ID,
        EXP_BINOP,
        EXP_UNNOP,
        EXP_ASSIGN,
        EXP_FUNC,
        EXP_ARRAY
    } kind;
    union {

        //lit
        l_lit lit;

        //id
        char *id;

        //array
        struct
        {
            e_exp exp;
            int lit;
        } array;

        //binop
        struct
        {
            char op[3];
            e_exp arg1;
            e_exp arg2;
        } binop;

        //unop
        struct
        {
            char op[3];
            e_exp arg1;
        } unop;

        //exp_assing
        struct
        {
            e_exp id;
            e_exp value;
        } assing;

        //exp_func
        struct
        {
            char *id;
            a_args args;
        } func;
    } u;
};

struct d_decl_
{
    enum
    {
        DECL_FUNC,
        DECL_DEFINE,
        DECL_INIT,
        DECL_ASSING
    } kind;

    union {
        d_decl decl;
        d_decls decls;
        struct
        {
            i_ids id_list;
            t_type type;
            e_exp exp;
        } assing;
        struct
        {
            i_ids id_list;
            t_type type;
        } init;
        struct
        {
            char *id;
            t_type type;
            a_argdefs argdefs;
            s_stms stms;
        } func;
        struct
        {
            char *id;
            t_type type;
        } define;
    } u;
};
struct d_decls_
{
    enum
    {
        DECLS_SINGLE,
        DECLS_LIST
    } kind;
    struct
    {
        d_decl decl;
        d_decls decls;
    } u;
};

struct s_stm_
{
    enum
    {
        STM_DECL,
        STM_EXP,
        STM_RETURN,
        STM_IF_ELSE,
        STM_WHILE,
        STM_NEXT,
    } kind;

    union {
        d_decl decl;
        e_exp exp;
        e_exp returnType;
        struct
        {
            e_exp exp;
            s_stms then_stms;
            s_stms else_stms;
        } if_else;
        struct
        {
            e_exp exp;
            s_stms while_stms;
        } while_;

    } u;
};

struct a_argdefs_
{
    enum
    {
        ARGDEFS_LIST,
        ARGDEFS_SINGLE,
    } kind;

    struct
    {
        a_argdef argdef;
        a_argdefs argdefs;
    } u;
};

struct a_argdef_
{
    char *id;
    t_type type;
};

struct s_stms_
{
    enum
    {
        STMS_SINGLE,
        STMS_LIST
    } kind;

    struct
    {
        s_stm stm;
        s_stms stms;
    } u;
};

d_decls d_decls_new(d_decl decl, d_decls decls)
{
    d_decls ret = (d_decls )malloc(sizeof(d_decls));
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
    d_decl ret = (d_decl)malloc(sizeof(d_decl));

    ret->kind = DECL_INIT;
    ret->u.init.id_list = id_list;
    ret->u.init.type = type;

    return ret;
}

d_decl d_decl_new_assign(i_ids id_list, t_type type, e_exp exp)
{
    d_decl ret = (d_decl)malloc(sizeof(d_decl));

    ret->kind = DECL_ASSING;
    ret->u.assing.id_list = id_list;
    ret->u.assing.type = type;
    ret->u.assing.exp = exp;

    return ret;
}

d_decl d_decl_new_func(char *id, a_argdefs argdefs, t_type type, s_stms stms)
{
    d_decl ret = (d_decl)malloc(sizeof(d_decl));

    ret->kind = DECL_FUNC;
    strcpy(ret->u.func.id, id);
    ret->u.func.type = type;
    ret->u.func.argdefs = argdefs;
    ret->u.func.stms = stms;

    return ret;
}

d_decl d_decl_new_define(char *id, t_type type)
{
    d_decl ret = (d_decl)malloc(sizeof(d_decl));

    ret->kind = DECL_DEFINE;
    strcpy(ret->u.define.id, id);
    ret->u.define.type = type;

    return ret;
}

a_argdefs a_argdefs_new(a_argdef argdef, a_argdefs argdefs)
{
    a_argdefs ret = (a_argdefs)malloc(sizeof(a_argdefs));

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
    a_argdef ret = (a_argdef)malloc(sizeof(a_argdef));

    strcpy(ret->id, id);
    ret->type = type;

    return ret;
}

a_args a_args_new(e_exp exp, a_args args)
{
    a_args ret = (a_args)malloc(sizeof(a_args));
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
    i_ids ret = (i_ids)malloc(sizeof(i_ids));
    strcpy(ret->id, id);
    ret->id_list = id_list;
    return ret;
}

s_stms s_stms_new(s_stm stm, s_stms stms)
{
    s_stms ret = (s_stms)malloc(sizeof(s_stms));

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
    s_stm ret = (s_stm)malloc(sizeof(s_stm));

    ret->kind = STM_DECL;
    ret->u.decl = decl;

    return ret;
}
s_stm s_stm_new_exp(e_exp exp)
{
    s_stm ret = (s_stm)malloc(sizeof(s_stm));
    ret->kind = STM_EXP;
    ret->u.exp = exp;
    return ret;
}
s_stm s_stm_new_return(e_exp exp)
{
    s_stm ret = (s_stm)malloc(sizeof(ret));

    ret->kind = STM_RETURN;
    ret->u.returnType = exp;

    return ret;
}
s_stm s_stm_new_if_else(e_exp exp, s_stms then_stms, s_stms else_stms)
{
    s_stm ret = (s_stm)malloc(sizeof(ret));

    ret->kind = STM_IF_ELSE;
    ret->u.if_else.exp = exp;
    ret->u.if_else.then_stms = then_stms;
    ret->u.if_else.else_stms = else_stms;

    return ret;
}
s_stm s_stm_new_while(e_exp exp, s_stms while_stms)
{
    s_stm ret = (s_stm)malloc(sizeof(ret));

    ret->kind = STM_WHILE;
    ret->u.while_.exp = exp;
    ret->u.while_.while_stms = while_stms;

    return ret;
}

s_stm s_stm_new_next()
{
    s_stm ret = (s_stm)malloc(sizeof(s_stm));

    ret->kind = STM_NEXT;

    return ret;
}

t_type t_new_type(int kind, char *type)
{
    t_type ret = (t_type)malloc(sizeof(t_type));

    ret->kind = kind;
    strcpy(ret->u.type, type);

    return ret;
}
t_type t_new_id(char *id)
{
    t_type ret = (t_type)malloc(sizeof(t_type));

    ret->kind = T_ID;
    ret->u.id = id;

    return ret;
}
t_type t_new_array(t_type type, int lit)
{
    t_type ret = (t_type)malloc(sizeof(t_type));

    ret->kind = T_ARRAY;
    ret->u.array.type = type;
    ret->u.array.lit = lit;

    return ret;
}

l_lit l_lit_new_intlit(int lit)
{
    l_lit ret = (l_lit)malloc(sizeof(l_lit));

    ret->kind = LIT_INTLIT;
    ret->u.it = lit;

    return ret;
}
l_lit l_lit_new_floatlit(double lit)
{
    l_lit ret = (l_lit)malloc(sizeof(l_lit));

    ret->kind = LIT_FLOATLIT;
    ret->u.fit = lit;

    return ret;
}
l_lit l_lit_new_strlit(char *lit)
{
    l_lit ret = (l_lit)malloc(sizeof(l_lit));

    ret->kind = LIT_STRLIT;
    strcpy(ret->u.cit, lit);

    return ret;
}
l_lit l_lit_new_boollit(int lit)
{
    l_lit ret = (l_lit)malloc(sizeof(l_lit));

    ret->kind = LIT_BOOLLIT;
    ret->u.bit = lit;

    return ret;
}

e_exp e_exp_new_binop(char op[], e_exp arg1, e_exp arg2)
{
    e_exp ret = (e_exp)malloc(sizeof(e_exp));

    ret->kind = EXP_BINOP;
    strcpy(ret->u.binop.op, op);
    ret->u.binop.arg1 = arg1;
    ret->u.binop.arg2 = arg2;

    return ret;
}
e_exp e_exp_new_unop(char op[], e_exp arg1)
{
    e_exp ret = (e_exp)malloc(sizeof(e_exp));

    ret->kind = EXP_UNNOP;
    strcpy(ret->u.unop.op, op);
    ret->u.unop.arg1 = arg1;

    return ret;
}
e_exp e_exp_new_assign(e_exp exp, e_exp value)
{
    e_exp ret = (e_exp)malloc(sizeof(e_exp));

    ret->kind = EXP_ASSIGN;
    ret->u.assing.id = exp;
    ret->u.assing.value = value;

    return ret;
}
e_exp e_exp_new_lit(l_lit lit)
{
    e_exp ret = (e_exp)malloc(sizeof(e_exp));

    ret->kind = EXP_LIT;
    ret->u.lit = lit;

    return ret;
}
e_exp e_exp_new_id(char *id)
{
    e_exp ret = (e_exp)malloc(sizeof(e_exp));

    ret->kind = EXP_ID;
    strcpy(ret->u.id, id);

    return ret;

}
e_exp e_exp_new_array(e_exp exp, int intlit)
{
    e_exp ret = (e_exp)malloc(sizeof(e_exp));

    ret->kind = EXP_ARRAY;
    ret->u.array.exp = exp;
    ret->u.array.lit = intlit;

    return ret;
}
e_exp e_exp_new_function(char *id, a_args args)
{
    e_exp ret = (e_exp)malloc(sizeof(e_exp));

    ret->kind = EXP_FUNC;
    strcpy(ret->u.func.id, id);
    ret->u.func.args = args;

    return ret;
}




float f (int a){
    
    int i;

    if ( i < 2){
        int j = 0;
    }

}
