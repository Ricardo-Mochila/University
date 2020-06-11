/* A Bison parser, made by GNU Bison 3.5.1.  */

/* Bison interface for Yacc-like parsers in C

   Copyright (C) 1984, 1989-1990, 2000-2015, 2018-2020 Free Software Foundation,
   Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* Undocumented macros, especially those whose name start with YY_,
   are private implementation details.  Do not rely on them.  */

#ifndef YY_YY_PARSER_H_INCLUDED
# define YY_YY_PARSER_H_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int yydebug;
#endif

/* Token type.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
    INTLIT = 258,
    BOOLLIT = 259,
    FLOATLIT = 260,
    STRLIT = 261,
    ID = 262,
    _T_INT = 263,
    _T_FLOAT = 264,
    _T_STRING = 265,
    _T_BOOL = 266,
    _T_VOID = 267,
    COLON = 268,
    DEFINE = 269,
    RETURN = 270,
    STRUCT = 271,
    WHILE = 272,
    DO = 273,
    NEXT = 274,
    BREAK = 275,
    IF = 276,
    THEN = 277,
    ELSE = 278,
    SEMI = 279,
    ASSIGN = 280,
    COMMA = 281,
    OR = 282,
    AND = 283,
    NOT = 284,
    SUB = 285,
    ADD = 286,
    MUL = 287,
    DIV = 288,
    MOD = 289,
    POW = 290,
    EQ = 291,
    NEQ = 292,
    GT = 293,
    GEQ = 294,
    LT = 295,
    LEQ = 296,
    NEG = 297,
    LCBRACE = 298,
    RCBRACE = 299,
    LSBRACE = 300,
    RSBRACE = 301,
    LPAR = 302,
    RPAR = 303
  };
#endif
/* Tokens.  */
#define INTLIT 258
#define BOOLLIT 259
#define FLOATLIT 260
#define STRLIT 261
#define ID 262
#define _T_INT 263
#define _T_FLOAT 264
#define _T_STRING 265
#define _T_BOOL 266
#define _T_VOID 267
#define COLON 268
#define DEFINE 269
#define RETURN 270
#define STRUCT 271
#define WHILE 272
#define DO 273
#define NEXT 274
#define BREAK 275
#define IF 276
#define THEN 277
#define ELSE 278
#define SEMI 279
#define ASSIGN 280
#define COMMA 281
#define OR 282
#define AND 283
#define NOT 284
#define SUB 285
#define ADD 286
#define MUL 287
#define DIV 288
#define MOD 289
#define POW 290
#define EQ 291
#define NEQ 292
#define GT 293
#define GEQ 294
#define LT 295
#define LEQ 296
#define NEG 297
#define LCBRACE 298
#define RCBRACE 299
#define LSBRACE 300
#define RSBRACE 301
#define LPAR 302
#define RPAR 303

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
union YYSTYPE
{
#line 15 "yalang.y"

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
        
        

#line 173 "parser.h"

};
typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;

int yyparse (void);

#endif /* !YY_YY_PARSER_H_INCLUDED  */
