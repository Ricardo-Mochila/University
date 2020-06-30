#include "data_structures/structures.h"


d_decls d_decls_new(d_decl decl, d_decls decls);

d_decl d_decl_new_init(i_ids id_list, t_type type);
d_decl d_decl_new_assign(i_ids id_list, t_type type, e_exp exp);
d_decl d_decl_new_func(char *id, a_argdefs argdefs, t_type type, s_stms stms);
d_decl d_decl_new_define(char *id, t_type type);

a_argdefs a_argdefs_new(a_argdef argdef, a_argdefs argdefs);
a_argdef a_argdef_new(char *id, t_type type);
a_args a_args_new(e_exp exp, a_args args);

i_ids i_ids_new(char *id, i_ids id_list);

s_stms s_stms_new(s_stm stm, s_stms stms);

s_stm s_stm_new_if_else(e_exp exp, s_stms then_stms, s_stms else_stms);
s_stm s_stm_new_decl(d_decl decl);
s_stm s_stm_new_exp(e_exp exp);
s_stm s_stm_new_return(e_exp exp);
s_stm s_stm_new_while(e_exp exp, s_stms while_stms);
s_stm s_stm_new_next();

t_type t_new_type(int kind, char *type);
t_type t_new_id(char *id);
t_type t_new_array(t_type type, int intlit);   

l_lit l_lit_new_intlit(int intlit);
l_lit l_lit_new_floatlit(double floatlit);
l_lit l_lit_new_strlit(char *strlit);
l_lit l_lit_new_boollit(int boollit);

e_exp e_exp_new_id(char *id);
e_exp e_exp_new_binop(char op[], e_exp argt, e_exp arg2);
e_exp e_exp_new_unop(char op[], e_exp args);
e_exp e_exp_new_assign(e_exp exp, e_exp value);
e_exp e_exp_new_lit(l_lit lit);
e_exp e_exp_new_id(char *id);
e_exp e_exp_new_array(e_exp exp, int intlit);
e_exp e_exp_new_function(char *id, a_args args);