#include <stdlib.h>
#include <stdio.h>
#include "abp.h"

/*
    Implemented using the java code from EDA 1 as reference and some internet sources when i didn't know what to do
*/

struct node *newNode(unsigned int element)
{
    struct node *new = malloc(sizeof(struct node));
    new->element = element;
    new->left = NULL;
    new->right = NULL;

    return new;
}

/*
    This function creates a node of the bst, allocating it in the memmory
*/

struct node *insert(unsigned int element, struct node *new)
{
    if (new == NULL)
        new = newNode(element);

    else if (element > new->element)
        new->right = insert(element, new->right);

    else if (element < new->element)
        new->left = insert(element, new->left);

    return new;
}

/*
    This function has the objective to create a new node if the node given is null just creates it, if not goes to the right or left recursively,
    according to the element given, and when it finds a null node creates it
*/

struct node *findMin(struct node *minimum)
{
    if (minimum == NULL)
        return NULL;

    else if (minimum->left == NULL)
        return minimum;

    return findMin(minimum->left);
}

/*
    This function finds the node more to the left of the given node
*/

struct node *removed(unsigned int x, struct node *toRemove)
{
    if (toRemove == NULL)
        return NULL;

    if (x > toRemove->element)
        toRemove->right = removed(x, toRemove->right);

    else if (x < toRemove->element)
        toRemove->left = removed(x, toRemove->left);

    else
    {
        if (toRemove->left == NULL && toRemove->right == NULL)
        {
            free(toRemove);
            return NULL;
        }

        else if (toRemove->left == NULL || toRemove->right == NULL)
        {
            struct node *temp;

            if (toRemove->left == NULL)
                temp = toRemove->right;

            else
                temp = toRemove->left;

            free(toRemove);
            return temp;
        }

        else
        {
            struct node *temp = findMin(toRemove->right);

            toRemove->element = temp->element;
            toRemove->right = removed(temp->element, toRemove->right);
        }
    }
    return toRemove;
}
/*
    Remove function copied from https://www.codesdope.com/blog/article/binary-search-tree-in-c/
    My original function was giving me a lot of trouble so I used one from the link above
*/

struct node *search(unsigned int toFind, struct node *IWillFindYou)
{
    if (IWillFindYou == NULL)
        return NULL;

    else if (toFind > IWillFindYou->element && IWillFindYou->right != NULL)
        return search(toFind, IWillFindYou->right);

    else if (toFind < IWillFindYou->element && IWillFindYou->left != NULL)
        return search(toFind, IWillFindYou->left);

    else if (toFind == IWillFindYou->element)
        return IWillFindYou;

    return NULL;
}

/*
    This function will search for the node with the element correspondent to the int given 'toFind'
    it goes to the right or left node if the int given is bigger or smaller, respectively, then the current node element 
    if it finds the element returns the corresponding node, if it does not find it, will return null
*/


