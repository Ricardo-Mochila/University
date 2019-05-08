#include <stdio.h>

void fibonnaci()
{
    int a = 0;
    int b = 1;
    int c = 2;
    int d;
    printf("%d ", a);
    printf("%d ", b);

    while(c <= 20)
    {   
        d = a;
        a = b;
        b = d + a;
        printf("%d ", b);
        c++;
    }
    printf("\n");
}

int main(void)
{
    fibonnaci();
    return 0;
}