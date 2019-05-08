#include <stdio.h>
#include <stdbool.h>
#include "hashtable.h"

void manage(int a, int b, int c, struct element *defaultRoute)
{
    struct element *wanted = search(a, b, c);

    if (wanted == NULL)
    {
        if (defaultRoute != NULL)
            printf("%d\n", defaultRoute->interface);
        else
        {
            printf("no route\n");
        }
    }
    else
    {
        printf("%d\n", wanted->interface);
    }
}

/*
This function creates a struct element that corresponds to the element returned from the function search(), it can be null
if it is it will look for a default route, in case there's none it will print no route, if it does find a default route it prints the interface number
corresponding to that same route. In case the element wanted is found it prints its corresponding interface number
*/

int main(void)
{
    int r = 0;
    int a = 0;
    int b = 0;
    int c = 0;
    int d = 0;
    int interface = 0;
    scanf("%d", &r);

    for (int i = 0; i < r; i++)
    {
        scanf("%d.%d.%d.0 %d", &a, &b, &c, &interface);
        insert(a, b, c, interface);
    }

    struct element *defaultRoute = search(0, 0, 0);

    while (scanf("%d.%d.%d.%d", &a, &b, &c, &d) != EOF)
    {
        manage(a, b, c, defaultRoute);
    }
    return 0;
}

/*
The main function creates 6 integers, then does one scan to know how many times it needs to run the next scan, in the second scan
runs the values through insert function described in the file hashtable.c
It creates an struct element that as the outcome of the function search with the parameters corresponding to the default route
Then scans until it finds the end-of-file, in this loop runs the function manage
*/