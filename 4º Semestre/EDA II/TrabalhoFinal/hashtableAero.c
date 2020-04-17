#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "hashtableVoo.h"
#include "structures.h"
#define SIZE 400009
struct trajecto *hashTableAero[SIZE];

unsigned long hashAero(char *str) //http://www.cse.yorku.ca/~oz/hash.html
{
    unsigned long hash = 5381;
    int c;

    while ((c = *str++))
        hash = hash * 33 + c; /* hash * 33 + c */
    return hash;
}

void insertAero(struct aeroporto aero, NODE* node)
{
    struct trajecto *elm = (struct trajecto *)malloc(sizeof(struct trajecto));
    elm -> aero = aero;
    elm -> node = node;

    unsigned long index = hashAero(aero.identA);
    index = index % SIZE;
    
    while (hashTableAero[index] != NULL) 
    {
        index++;
        index = index % SIZE;
    }
    
    hashTableAero[index] = elm;
}

struct trajecto *searchAero(char aeroId[5])
{
    unsigned long index = hashAero(aeroId);
    
    
    index = index % SIZE;
    
    while (hashTableAero[index] != NULL )
    {
        if (strcmp(hashTableAero[index]->aero.identA,aeroId)== 0)
        {
            return hashTableAero[index];
        }
        index++;
        index = index % SIZE;
    }
    return NULL;
}

void displayAero() 
{
   int i = 0;
	
   for(i = 0; i<SIZE; i++) {
	
      if(hashTableAero[i] != NULL)
         printf("(%s,%s)",hashTableAero[i]-> aero.identA, hashTableAero[i]-> node->voo.identV); // <- a ines esteves aqui
      else
         printf(" ~~ ");
   }
	
   printf("\n");
}