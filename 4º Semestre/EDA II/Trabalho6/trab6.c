#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include "abp.h"

void plusFunction(struct node *allConnections[])
{
    unsigned int a = 0;
    unsigned int b = 0;

    scanf(" %d %d", &a, &b);
    allConnections[a] = insert(b, allConnections[a]);
}

/*
    This function receives an input of 2 integers and inserts the second on a binary search tree in an array
    position corresponding to the 'a' value
*/

void minusFunction(struct node *allConnections[])
{
    unsigned int a = 0;
    unsigned int b = 0;

    scanf(" %d %d", &a, &b);
    if (search(b, allConnections[a]) != NULL)
        allConnections[a] = removed(b, allConnections[a]);
}

/*
    This function receives an input of 2 integers and removes the second on the binary search tree in the array
    position corresponding to the 'a' value
*/

void questionMark(struct node *allConnections[], unsigned int sensors)
{
    int times = 0;
    bool flag = true;
    unsigned int a = 0;
    unsigned int b = 0;

    scanf(" %d", &times);

    if (times == 1)
    {
        scanf(" %d %d", &a, &b);

        if (search(b, allConnections[a]) != NULL)
            printf("yes [%d..%d]\n", a, b);

        else
            printf("no [%d..%d]\n", a, b);
    }

    else if (times > 1)
    {
        int array[times + 1];

        for (int i = 0; i < times + 1; i++)
            scanf(" %d", &array[i]);

        for (int i = 0; i < times; i++)
        {
            if (search(array[i + 1], allConnections[array[i]]) == NULL)
            {
                flag = false;
                break;
            }
        }

        if (flag == true)
            printf("yes [%d..%d]\n", array[0], array[times]);

        else
            printf("no [%d..%d]\n", array[0], array[times]);
    }
}

/*
    This function receives a integer as an input, if it's 1 receives another two integers finds if the second exists int the bst in the 
    array position corresponding to the first integer.
    If the input is bigger then 1 it creates an array and scans the integers, then searches for the numbers and checks in the array position 
    corresponding to the number if the next is in the bst
*/

int main(void)
{
    unsigned int sensors = 0;
    char c;

    scanf("%d", &sensors);

    struct node *allConnections[sensors];

    for (unsigned int i = 0; i < sensors; i++)
        allConnections[i] = NULL;

    while (scanf("%c", &c) != EOF)
    {
        switch (c)
        {
        case '+':
            plusFunction(allConnections);
            break;

        case '-':
            minusFunction(allConnections);
            break;

        case '?':
            questionMark(allConnections, sensors);
            break;

        default:
            break;
        }
    }
    return 0;
}

/* 
    This function gets the input and calls other functions according to the input received
*/