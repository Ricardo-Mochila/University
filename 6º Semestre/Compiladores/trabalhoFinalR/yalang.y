%{
#include "ya.h"
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include "semanticAnalysis.h"

int yylex (void);
void yyerror (char const *);
int main(void);

%}

%union
{
        int        ival;
        double     dval;
        char       *str;

        d_decls    decls;
        d_decl     decl;
        e_exp      exp;
        l_lit      lit;
        t_type     type;
        i_ids      ids;
        a_argdefs  argdefs;
        a_argdef   argdef;
        a_args     args;
        s_stms     stms;
        s_stm      stm;
        
        
} 


/*Bison declarations */
%token <ival>       INTLIT BOOLLIT 
%token <dval>       FLOATLIT
%token <str>        STRLIT ID _T_INT _T_FLOAT _T_STRING _T_BOOL _T_VOID 
%token              COLON
%token              DEFINE RETURN STRUCT WHILE DO NEXT BREAK IF THEN ELSE 

%left               SEMI
%right              ASSIGN
%left               COMMA
%left               OR
%left               AND
%left               NOT
%left               SUB ADD
%left               MUL DIV MOD
%right              POW

%nonassoc           EQ NEQ GT GEQ LT LEQ NEG
%nonassoc           LCBRACE RCBRACE
%nonassoc           LSBRACE RSBRACE
%nonassoc           LPAR RPAR

%type <decls>       program
%type <decls>       decls
%type <decl>        decl
%type <type>        type
%type <lit>         lit
%type <exp>         exp
%type <ids>         ids
%type <argdefs>     argdefs
%type <argdef>      argdef
%type <args>        args
%type <stms>        stms
%type <stm>         stm


%%

program:                                                                             { $$ = NULL;                                 }
        |       decls                                                                { $$ = $1;  $$= semanticAnalysis($1)         }
                ;

decls:          decl                                                                 { $$ = d_decls_new($1, NULL);                }
        |       decl decls                                                           { $$ = d_decls_new($1, $2);                  }
                ;

decl:           ids COLON type SEMI                                                  { $$ = d_decl_new_assign($1, $3, NULL);      }
        |       ids COLON type ASSIGN exp SEMI                                       { $$ = d_decl_new_assign($1,$3, $5);         }
        |       ID LPAR RPAR COLON type LCBRACE stms RCBRACE SEMI                    { $$ = d_decl_new_func($1,NULL,$5,$7);       }
        |       ID LPAR argdefs RPAR COLON type LCBRACE stms RCBRACE SEMI            { $$ = d_decl_new_func($1,$3,$6,$8);         }
        |       DEFINE ID type SEMI                                                  { $$ = d_decl_new_define($2,$3);             }
                ; 

argdef:         ID COLON type                                                        { $$ = a_argdef_new($1,$3);                  }
                ; 
  
argdefs:        argdef                                                               { $$ = a_argdefs_new($1,NULL);               }
        |       argdef COMMA argdefs                                                 { $$ = a_argdefs_new($1,$3);       }
                ;  
  
args:           exp                                                                  { $$ = a_args_new($1,NULL);                  }
        |       exp COMMA args                                                       { $$ = a_args_new($1,$3);                    }
                ;                  
  
ids:            ID                                                                   { $$ = i_ids_new($1,NULL);                   }
        |       ID COMMA ids                                                         { $$ = i_ids_new($1,$3);                     }
                ;  
  
stms:           stm                                                                  { $$ = s_stms_new($1,NULL);                  }
        |       stm stms                                                             { $$ = s_stms_new($1,$2);                    }
                ;


stm:            decl                                                                 { $$ = s_stm_new_decl($1);                   }
        |       exp SEMI                                                             { $$ = s_stm_new_exp($1);                    }
        |       RETURN exp SEMI                                                      { $$ = s_stm_new_return($2);                 }
        |       IF exp THEN LCBRACE stms RCBRACE SEMI                                { $$ = s_stm_new_if_else($2,$5,NULL);        }
        |       IF exp THEN LCBRACE stms RCBRACE ELSE LCBRACE stms RCBRACE SEMI      { $$ = s_stm_new_if_else($2,$5,$9);          }
        |       WHILE exp DO LCBRACE stms RCBRACE ELSE LCBRACE stms RCBRACE SEMI     { $$ = s_stm_new_while($2,$5);               }
        |       NEXT                                                                 { $$ = s_stm_new_next();                     } 
                ; 
 
type:           _T_INT                                                                { $$ = t_new_type(0,$1);                     }
        |       _T_FLOAT                                                              { $$ = t_new_type(1,$1);                     } 
        |       _T_STRING                                                             { $$ = t_new_type(2,$1);                     } 
        |       _T_BOOL                                                               { $$ = t_new_type(3,$1);                     } 
        |       _T_VOID                                                               { $$ = t_new_type(4,$1);                     }
        |       ID                                                                   { $$ = t_new_id($1);                         }
        |       type LSBRACE INTLIT RSBRACE                                          { $$ = t_new_array($1,$3);                   }
                ;                    
                     
lit:            INTLIT                                                               { $$ =l_lit_new_intlit($1);                 }
        |       FLOATLIT                                                             { $$ =l_lit_new_floatlit($1);               }
        |       STRLIT                                                               { $$ =l_lit_new_strlit($1);                 }
        |       BOOLLIT                                                              { $$ =l_lit_new_boollit($1);                }
                ;       

exp:            lit                                                                  { $$ = e_exp_new_lit($1);                    }
        |       ID                                                                   { $$ = e_exp_new_id($1);                     }
        |       exp LSBRACE INTLIT RSBRACE                                           { $$ = e_exp_new_array($1, $3);              }
        |       exp ADD exp                                                          { $$ = e_exp_new_binop("+", $1, $3);         }
        |       exp SUB exp                                                          { $$ = e_exp_new_binop("-", $1, $3);         }
        |       exp MUL exp                                                          { $$ = e_exp_new_binop("*", $1, $3);         }
        |       exp DIV exp                                                          { $$ = e_exp_new_binop("/", $1, $3);         } 
        |       exp POW exp                                                          { $$ = e_exp_new_binop("^", $1, $3);         }
        |       exp MOD exp                                                          { $$ = e_exp_new_binop("mod", $1, $3);       }
        |       exp GT exp                                                           { $$ = e_exp_new_binop(">", $1, $3);         }
        |       exp LT exp                                                           { $$ = e_exp_new_binop("<", $1, $3);         } 
        |       exp GEQ exp                                                          { $$ = e_exp_new_binop(">=", $1, $3);        }
        |       exp LEQ exp                                                          { $$ = e_exp_new_binop("<=", $1, $3);        }
        |       exp NEQ exp                                                          { $$ = e_exp_new_binop("!=", $1, $3);        }
        |       exp EQ exp                                                           { $$ = e_exp_new_binop("==", $1, $3);        }
        |       exp AND exp                                                          { $$ = e_exp_new_binop("and", $1, $3);       } 
        |       exp OR exp                                                           { $$ = e_exp_new_binop("or", $1, $3);        }
        |       NOT exp                                                              { $$ = e_exp_new_unop("not", $2);            }
        |       SUB exp %prec NEG                                                    { $$ = e_exp_new_unop("-", $2);              }
        |       LPAR exp RPAR                                                        { $$ = $2;                                   }
        |       ID LPAR RPAR                                                         { $$ = e_exp_new_function($1,NULL);          }
        |       ID LPAR args RPAR                                                    { $$ = e_exp_new_function($1, $3);           }
        |       exp ASSIGN exp                                                       { $$ = e_exp_new_assign($1, $3);             }
                ; 

%%

void yyerror (char const *s)
{
	fprintf (stderr, "%s\n", s);
}

int main (void)
{
	return yyparse();
}