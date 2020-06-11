#include "symbolTable.h"

struct Node{
    char *id;
    ST_Data data;
};

unsigned long hash(char *str);

ST_Data search(char *id, struct Node * hashTable[]);

struct Node * insert(char *id, ST_Data data, struct Node * hashTable[] );
