/* A Bison parser, made by GNU Bison 2.3.  */

/* Skeleton interface for Bison's Yacc-like parsers in C

   Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor,
   Boston, MA 02110-1301, USA.  */

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

/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     INTLIT = 258,
     BOOLLIT = 259,
     FLOATLIT = 260,
     STRLIT = 261,
     ID = 262,
     T_INT = 263,
     T_FLOAT = 264,
     T_STRING = 265,
     T_BOOL = 266,
     T_VOID = 267,
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
     ADD = 285,
     SUB = 286,
     MOD = 287,
     DIV = 288,
     MUL = 289,
     POW = 290,
     NEG = 291,
     LEQ = 292,
     LT = 293,
     GEQ = 294,
     GT = 295,
     NEQ = 296,
     EQ = 297,
     RCBRACE = 298,
     LCBRACE = 299,
     RSBRACE = 300,
     LSBRACE = 301,
     RPAR = 302,
     LPAR = 303
   };
#endif
/* Tokens.  */
#define INTLIT 258
#define BOOLLIT 259
#define FLOATLIT 260
#define STRLIT 261
#define ID 262
#define T_INT 263
#define T_FLOAT 264
#define T_STRING 265
#define T_BOOL 266
#define T_VOID 267
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
#define ADD 285
#define SUB 286
#define MOD 287
#define DIV 288
#define MUL 289
#define POW 290
#define NEG 291
#define LEQ 292
#define LT 293
#define GEQ 294
#define GT 295
#define NEQ 296
#define EQ 297
#define RCBRACE 298
#define LCBRACE 299
#define RSBRACE 300
#define LSBRACE 301
#define RPAR 302
#define LPAR 303




#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE
#line 14 "yalang.y"
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
/* Line 1529 of yacc.c.  */
#line 165 "parser.h"
	YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif

extern YYSTYPE yylval;

