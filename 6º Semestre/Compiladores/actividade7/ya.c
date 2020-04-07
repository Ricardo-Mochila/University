#include "ya.h"
#include <stdlib.h>

struct t_exp
{
    enum
    {
        EXP_INTLIT,
        EXP_FLOATLIT,
        EXP_ID,
        EXP_BINOP,
        /*   */
    } kind;
    union {
        int intlit;
        float floatlit;
        char *id;
        /*   */
    } u;
};

struct t_decls
{
    enum
    {
        DECLS_SINGLE,
        DECLS_LIST
    } kind;
    struct
    {
        t_decl decl;
        t_decls decls;
    } u;
};

struct t_decl
{
    struct
    {
        t_decl decl;
        t_decls decls;
    } u;
};

struct t_stm
{
    enum
    {
        STM_DECL,
        STM_EXP,
        STM_RETURN,
        /*. . .*/
    } kind;

    union {
        t_decl decl;
        t_exp exp;
        t_exp returnType;
    } u;
};

t_exp t_exp_new_id(char *id)
{
    t_exp ret = (t_exp) malloc (sizeof(t_exp));
    ret -> kind = EXP_ID;
    ret -> u.id = id;

    return ret;
}

t_decls t_decls_new(t_decl decl, t_decls decls)
{
    t_decls ret = (t_decls*)malloc(sizeof(ret));
    if (decls)
    {
        ret -> kind = DECLS_LIST;
    }
    else
    {
        ret -> kind = DECLS_SINGLE;
    }
    ret -> u.decl = decl;
    ret -> u.decls = decls;
    return ret;
}

t_stm t_stm_new_return(t_exp exp)
{
    t_stm ret = (t_stm) malloc (sizeof(ret));
    
    ret -> kind = STM_RETURN;
    ret -> u.returnType = exp;
    
    return ret;
}

