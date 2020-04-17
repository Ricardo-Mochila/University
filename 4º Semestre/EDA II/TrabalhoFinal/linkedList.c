#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "structures.h"


void init(NODE** head) {
    *head = NULL;
}

void print_list(NODE* head) {
    NODE * temp;
    for (temp = head; temp; temp = temp->next)
        printf("%s ->", temp->voo.identV);
    puts("");
}

NODE* searchList(NODE* head, char vooId[7]) 
{
    NODE * temp;
    temp = head;
    while(temp != NULL)
    {
        if(strcmp(temp->voo.identV, vooId)==0)
        {
            return temp;
        }
        temp = temp->next;
    }
    return NULL;
}

NODE* add(NODE* node, struct voo data) {
    NODE* temp = (NODE*) malloc(sizeof (NODE));
    if (temp == NULL) {
        exit(0); // no memory available
    }
    temp->voo = data;
    temp->next = node;
    node = temp;
    return node;
}

void add_at(NODE* node, struct voo data) {
    NODE* temp = (NODE*) malloc(sizeof (NODE));
    if (temp == NULL) {
        exit(EXIT_FAILURE); // no memory available
    }
    temp->voo = data;
    temp->next = node->next;
    node->next = temp;
}

NODE* remove_node(NODE* head, char vooId[]) {
    NODE* temp = (NODE*) malloc(sizeof (NODE));
    NODE* actual = (NODE*) malloc(sizeof (NODE));

    if (temp == NULL) {
        exit(EXIT_FAILURE); // no memory available
    }
    temp = head;
    
    while(temp != NULL)
    {
        if(strcmp(temp -> voo.identV,vooId)==0)
            break;
        actual = temp;
        temp = temp -> next;
    }

    if(temp != head)
        actual -> next = temp -> next;
    
    else
    {
        head = temp -> next;
        free(temp);
        return head;
    }
    free(temp);
    return actual;
}

