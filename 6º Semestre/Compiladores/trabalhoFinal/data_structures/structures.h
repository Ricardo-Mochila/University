typedef struct e_exp_ *e_exp;
typedef struct d_decls_ *d_decls;
typedef struct d_decl_ *d_decl;
typedef struct s_stm_ *s_stm;
typedef struct a_argdefs_ *a_argdefs;
typedef struct a_argdef_ *a_argdef;
typedef struct a_args_ *a_args;
typedef struct i_ids_ *i_ids;
typedef struct s_stms_ *s_stms;
typedef struct t_type_ *t_type;
typedef struct l_lit_ *l_lit;


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
        EXP_UNOP,
        EXP_ASSIGN,
        EXP_FUNC,
        EXP_ARRAY
    } kind;
    union {

        l_lit lit;
        char *id;
        struct
        {
            e_exp exp;
            int lit;
        } array;

        struct
        {
            char op[3];
            e_exp arg1;
            e_exp arg2;
        } binop;

        struct
        {
            char op[3];
            e_exp arg1;
        } unop;

        struct
        {
            e_exp id;
            e_exp value;
        } assign;

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
        DECL_ASSIGN
    } kind;

    union {
        d_decl decl;
        struct
        {
            i_ids id_list;
            t_type type;
            e_exp exp;
        } assign;
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