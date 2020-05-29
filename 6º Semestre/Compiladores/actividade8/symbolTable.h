#define SIZE 1000

typedef struct st_data_*ST_Data;

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