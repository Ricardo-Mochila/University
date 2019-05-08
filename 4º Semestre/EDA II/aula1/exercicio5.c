#include <stdio.h>

int main(void)
{   
    int i, j;
    printf("Choose two values ");
    scanf("%d %d",&i,&j);

    if (i > j)
    {
        printf("%d\n", i);
    }
    else
    {
        printf("%d\n", j);
    }
    
    return 0;
}