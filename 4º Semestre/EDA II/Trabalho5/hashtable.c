#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include "hashtable.h"
#define SIZE 1000003

struct element *hashTable[SIZE];
//this struct takes the struct element that is in hastable.h and creates an array of structs

int hashCode(short a, short b, short c)
{
    return (a * 1000000 + b * 1000 + c) % SIZE;
}
//this function receives 3 integers and creates a value that is later used too introduce elements in the HashTable

void insert(short a, short b, short c, short interface)
{
    struct element *elm = (struct element *)malloc(sizeof(struct element));
    elm->a = a;
    elm->b = b;
    elm->c = c;
    elm->interface = interface;

    int index = hashCode(a, b, c);

    while (hashTable[index] != NULL)
    {
        index++;
        index = index % SIZE;
    }
    hashTable[index] = elm;
    
}

/*
This function receives 4 integers, allocates memory for an element and inserts the integers to their corresponding
position in the element, then calls the function hashCode to create a integer value and it saves it to index
then cicles in a loop until it finds a position in the hashTable to insert the element elm. 
*/

struct element *search(short a, short b, short c)
{
    int index = hashCode(a, b, c);

    while (hashTable[index] != NULL)
    {
        if (hashTable[index]->a == a && hashTable[index]->b == b && hashTable[index]->c == c)
        {
            return hashTable[index];
        }
        index++;
        index = index % SIZE;
    }
    return NULL;
};

/*
This function calls the hashcode(a,b,c) to get a index position to work with in an array of struct elements
while the position is different from Null it means it has some element on it, if its equal to the element that's wanted
it will return that same element, if not it checks the next position. If it finds a null it means the element we want it's not 
in the hashTable 
*/
