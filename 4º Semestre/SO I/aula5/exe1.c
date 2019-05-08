#define N 100000000
#include <stdio.h>
#include <math.h>
double calc()
{
    double c = 0;
    for(int i = 1; i <= N; i++)
    {
        c += sqrt(i);
    }
    return c;
}

int main(void)
{
    double result = calc();
    printf("%f", result);
    return 0;
}