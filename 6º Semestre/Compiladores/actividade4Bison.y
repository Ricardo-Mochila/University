%{
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

int yylex (void);
void yyerror (char const *);
double GET_VAR(char*);
void SET_VAR(char*, double);

struct elements{
    char * string;
    double value;
};

struct elements variables[100];
int sizeOfArray = 0;
%}

%union {
	double val;
	char *id;
}



/* Bison declarations. */
%token 		<val> 		INTLIT
%token 		<id> 		ID
%token 		NL 		/* newline */

/*Precedence - the (vertical) order matters!*/
%right 		ASSIGN
%left 		SUB 		ADD
%left 		MUL 		DIV
%left 		NEG 		/* negation--unary minus */
%token 		LPAR 		RPAR

/*Types for the grammar rules*/
%type 		<val> 		line exp


%%

input: 		/* empty */
		| line input
;

line: 		NL 	 { $$ = 0; }
		| exp NL { $$ = $1; printf ("%.2lf\n", $$); }
;

exp: 		INTLIT 			    { $$ = $1; }
		| exp ADD exp 		    { $$ = $1 + $3; }
		| exp MUL exp 		    { $$ = $1 * $3; }
        | exp DIV exp 		    { $$ = $1 / $3; }
        | exp SUB exp 		    { $$ = $1 - $3; }
		| SUB exp %prec NEG     { $$ = (0 - $2); }
		| LPAR exp RPAR 	    { $$ = $2; }
        | ID                    { $$ = GET_VAR($1); }
        | ID ASSIGN exp         { $$ = $3; SET_VAR($1, $3); }
;
%%

double GET_VAR(char * var){
    int i = 0;
    while(i < sizeOfArray){
        if(strcmp(variables[i].string, var)==0){
            return variables[i].value;
        }
        i++;
    }
    return 0;
}

void SET_VAR(char * var, double value){

    bool flag = false; 
    for(int i = 0; i < sizeOfArray; i++){
        if(strcmp(variables[i].string, var)==0){
            flag = true;
            variables[i].value = value;
            break;
        }
    }

    if(!flag){
        variables[sizeOfArray].string = var;
        variables[sizeOfArray].value = value;
        sizeOfArray++;
    }
    
}

/* Called by yyparse on error. */
void yyerror (char const *s)
{
	fprintf (stderr, "%s\n", s);
}

int main (void)
{
	return yyparse();
}
