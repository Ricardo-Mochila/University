#include <lista.h>

struct node
{
    int elemento struct node *next;
};

struct list
{
    struct node *head;
};

struct list *list_new(void)
{
    struct list *list = malloc(sizeof(struct list));

    if (list != NULL)
        list->head = NULL;

    return list;
}
