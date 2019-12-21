#include <stdio.h>

int max(int a, int b)
{
    if(a > b)
    {
        return 1;
    }
    return 0;
}

int main(void)
{
    int a;
    int b;
    int c;
    scanf("a-> %d", &a);
    scanf("b-> %d", &b);
    scanf("c-> %d", &c);

    if(max(a,b) == 1)
    {
        if(max(a,c) == 1)
        {
            printf("%d",a);
        }
        else
        {
            printf("%d",c);
        }
        
    }
    else{
        if(max(b,c) == 1)
        {
            printf("%d",b);
        }
        else{
            printf("%d",c);
        }
    }
}
