typedef struct t_exp_ *t_exp;

typedef struct t_decls_ *t_decls;

typedef struct t_decl_ *t_decl;

typedef struct t_stm_ *t_stm;

t_exp t_exp_new_id(char *id);

t_decls t_decls_new(t_decl decl, t_decls decls);

t_stm t_stm_new_return(t_exp exp);