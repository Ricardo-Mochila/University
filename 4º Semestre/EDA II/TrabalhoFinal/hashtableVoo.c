#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "hashtableVoo.h"
#define SIZE 1500000

struct voo_aero *hashTable[SIZE];

struct voo_aero dummy;

unsigned long hash(char *str) //http://www.cse.yorku.ca/~oz/hash.html
{
    unsigned long hash = 5381;
    int c;

    while ((c = *str++))
        hash = hash * 33 + c; /* hash * 33 + c */
    return hash;
}

void insertVoo(char voo[7], char aerop[5])
{
    
    struct voo_aero *elm = (struct voo_aero *)malloc(sizeof(struct voo_aero));
    strcpy(elm -> vooId,voo);
    strcpy(elm -> aeroId,aerop);
    
    unsigned long index = hash(voo);
    index = index % SIZE;
    
    while (hashTable[index] != NULL && strcmp(hashTable[index]->vooId, "-1")!= 0) 
    {
        index++;
        index = index % SIZE;
    }
    
    hashTable[index] = elm;
}

struct voo_aero *searchVoo(char voo[7])
{
    unsigned long index = hash(voo);
    index = index % SIZE;

    while (hashTable[index] != NULL && strcmp(hashTable[index]->vooId, "-1")!= 0)
    {
        if (strcmp(hashTable[index]->vooId,voo)== 0)
        {
            return hashTable[index];
        }
        index++;
        index = index % SIZE;
    }
    return NULL;
}

struct voo_aero *deleteVoo(char vooId[7]) 
{
    strcpy(dummy.vooId, "-1");

    unsigned long index = hash(vooId);
    index = index % SIZE;

    while(hashTable[index] != NULL && strcmp(hashTable[index]->vooId, "-1")!= 0)
    {
	
      if(strcmp(hashTable[index]->vooId, vooId) == 0) 
      {
         struct voo_aero *temp = hashTable[index]; 
         hashTable[index] = &dummy; 
         return temp;
      }
      ++index;
      index %= SIZE; 
   }      
	
   return NULL;        
}

void displayVoo() {
   int i = 0;
	
   for(i = 0; i<SIZE; i++) {
	
      if(hashTable[i] != NULL)
         printf("(%s,%s)",hashTable[i]->vooId,hashTable[i]->aeroId); // <- a ines esteves aqui
      else
         printf(" ~~ ");
   }
	
   printf("\n");
}