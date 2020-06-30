#define SIZE 1000
#include "structures.h"

struct Node * global[SIZE]; 
struct Node * function[SIZE];

typedef struct st_data_ *ST_Data;

struct st_data_
{
    enum {ST_VAR, ST_FUNC, ST_TYPE} kind;

    union
    {
        struct 
        {
            t_type yatype; //type é o tipo  na analise sintatica
            enum{ VARloc, VARarg} kind; //mais tarde, precisaremos de mais info
        } var;
        struct 
        {
            t_type yatype; //tipo de retorno
            a_argdefs arg; //lista de tipos de argumentos, por ordem
        } func;
        
        char*  type; //para este caso só precisamos do tipo destino
    } u;
};


//insere um id na simbol table
struct Node * ST_insert(char *id, ST_Data data, struct Node * hashTable[]);

//devolve os dados de um id, null se nao existir
ST_Data ST_lookup(char *id);

//devolve os dados de um id apenas no scope actual
ST_Data ST_lookup_local(char *id);

//cria um novo scope local - à entrada da funçao
int ST_new_scope();

//descarta o ultimo scope à saida da funcao 
int ST_discard();