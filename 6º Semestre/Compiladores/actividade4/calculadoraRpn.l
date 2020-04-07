%option noyywrap
%option main

%{
    
/////////////////////////////////////////////////////////////
//                                                         //
// Trabalho de Ines Verissimo 40102, Ricardo Mochila 37762 //
//                                                         //
/////////////////////////////////////////////////////////////

#include <stdio.h>
#include <stdbool.h>
#include <math.h>

struct items{
    int value;
    int character;
    bool ischar;
};

struct items result;
struct items stack[20];
int sp = -1;
int A;
int array[26];

#define EMPTY()		sp == -1
#define POP() 		stack[sp-- ]
#define PUSH(x) 	stack[++sp] = x

%}

NUM [0-9]+
WS [\n\t ]+
LETTER [a-z]

%%
{NUM} 		{ struct items item; 
                item.value = atoi(yytext);
                item.ischar = false;
                PUSH(item); 
            };

{LETTER} 	{   struct items item; 
                item.character = yytext[0]-97;
                item.ischar = true;
                PUSH(item); 
            };

"+" 		{   if(sp > 0){
                    struct items item;
                    struct items item1 = POP(); 
                    struct items item2 = POP();

                    if(item1.ischar == false){
                        if(item2.ischar == false)
                        {
                            item.value = item1.value + item2.value;
                            PUSH(item);
                        }
                        else
                        {
                            item.value = item1.value + array[item2.character];
                            PUSH(item);
                        }
                    }
                    else{
                        if(item2.ischar == false)
                        {
                            item.value = array[item1.character] + item2.value;
                            PUSH(item);
                        }
                        else
                        {
                            item.value = array[item1.character] + array[item2.character];
                            PUSH(item);
                        }
                    }
                    
                }
            }

"-" 		{   if(sp > 0){
                struct items item;
                struct items item1 = POP(); 
                struct items item2 = POP(); 

                if(item1.ischar == false){
                    if(item2.ischar == false)
                    {
                        item.value =  item2.value - item1.value;
                        PUSH(item);
                    }
                    else
                    {
                        item.value = array[item2.character] - item1.value;
                        PUSH(item);
                    }
                }
                else{
                    if(item2.ischar == false)
                    {
                        item.value =  item2.value - array[item1.character];
                        PUSH(item);
                    }
                    else
                    {
                        item.value = array[item2.character] - array[item1.character];
                        PUSH(item);
                    }
                }
                
            }
        }

"*" 		{   if(sp > 0){
                    struct items item;
                    struct items item1 = POP(); 
                    struct items item2 = POP();

                    if(item1.ischar == false){
                        if(item2.ischar == false)
                        {
                            item.value = item1.value * item2.value;
                            PUSH(item);
                        }
                        else
                        {
                            item.value = item1.value * array[item2.character];
                            PUSH(item);
                        }
                    }
                    else{
                        if(item2.ischar == false)
                        {
                            item.value = array[item1.character] * item2.value;
                            PUSH(item);
                        }
                        else
                        {
                            item.value = array[item1.character] * array[item2.character];
                            PUSH(item);
                        }
                    }
                    
                }
            }       
    
"/" 	{   if(sp > 0){
                struct items item;
                struct items item1 = POP(); 
                struct items item2 = POP(); 

                if(item1.ischar == false){
                    if(item2.ischar == false)
                    {
                        item.value =  item2.value / item1.value;
                        PUSH(item);
                    }
                    else
                    {
                        item.value = array[item2.character] / item1.value;
                        PUSH(item);
                    }
                }
                else{
                    if(item2.ischar == false)
                    {
                        item.value =  item2.value / array[item1.character];
                        PUSH(item);
                    }
                    else
                    {
                        item.value = array[item2.character] / array[item1.character];
                        PUSH(item);
                    }
                }
                
            }
        }

"^" 		{   if(sp > 0){
    struct items item;
    struct items item1 = POP(); 
    struct items item2 = POP(); 

    if(item1.ischar == false){
        if(item2.ischar == false)
        {
            item.value =  pow(item2.value, item1.value);
            PUSH(item);
        }
        else
        {
            item.value = pow(array[item2.character],item1.value);
            PUSH(item);
        }
    }
    else{
        if(item2.ischar == false)
        {
            item.value = pow(item2.value,array[item1.character]);
            PUSH(item);
        }
        else
        {
            item.value = pow(array[item2.character], array[item1.character]);
            PUSH(item);
        }
    }
    
}
} 

{WS} 		{ /* ignore whitespace */ }

"store"     { struct items item1 = POP(); 
              struct items item2 = POP();

              array[item1.character] = item2.value;
            }

"show" 		{ printf("(%d)\n", POP().value); }

"peek" 		{ result = POP(); printf("(%d)\n", result.value);
		PUSH( result); }

"quit" 		{ printf("bye...\n"); exit(0); }

. 		{ printf("Syntax error.\n"); }
