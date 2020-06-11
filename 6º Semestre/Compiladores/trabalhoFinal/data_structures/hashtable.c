 #include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "hashtable.h"

//struct Node *hashTable[SIZE];

struct Node dummy;

unsigned long hash(char *str) //http://www.cse.yorku.ca/~oz/hash.html
{
    unsigned long hash = 5381;
    int c;

    while ((c = *str++))
        hash = hash * 33 + c; /* hash * 33 + c */
    return hash;
}

struct Node * insert(char *id, ST_Data data, struct Node * hashTable[] )
{
    
    struct Node *elm = (struct Node *)malloc(sizeof(struct Node));
    strcpy(elm -> id,id);
    elm -> data =  data; 

    unsigned long index = hash(id);
    index = index % SIZE;
    
    while (hashTable[index] != NULL && strcmp(hashTable[index]-> id, "-1")!= 0) 
    {
        index++;
        index = index % SIZE;
    }
    
    hashTable[index] = elm;
    
    return *hashTable;
}

ST_Data search(char *id, struct Node *hashTable[])
{
    unsigned long index = hash(id);
    index = index % SIZE;

    while (hashTable[index] != NULL && strcmp(hashTable[index] -> id, "-1")!= 0)
    {
        if (strcmp(hashTable[index] -> id,id )== 0)
        {
            return hashTable[index] -> data;
        }
        index++;
        index = index % SIZE;
    }
    return NULL;
}