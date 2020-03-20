#include "stdio.h"
#include <stdlib.h>

struct node{
    int nodeElement;
    struct node *next;
};


void push(struct node** no, int new_data) 
{ 
    /* 1. allocate node */
    struct node* new_node = (struct node*) malloc(sizeof(struct node)); 
   
    /* 2. put in the data  */
    new_node->nodeElement  = new_data; 
   
    /* 3. Make next of new node as head */
    new_node->next = (*no); 
   
    /* 4. move the head to point to the new node */
    (*no)    = new_node; 
} 



int main(void){

    struct node *array[1000000];
    array[0] = NULL;
    push(&array[0], 2);
    push(&array[0], 3);
    push(&array[0], 4);
    push(&array[0], 5);
    push(&array[0], 6);
    push(&array[0], 7);

    while (array[0] != NULL)
    {
        printf("%d ", array[0] ->nodeElement);
        array[0] = array[0] -> next;
    }
    
    return 0;
}