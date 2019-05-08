#define N 100000000
#include <math.h>
#include <stdio.h>
#include <pthread.h>
double a = 0;
double b = 0;
double c = 0;
double d = 0;
double calc()
{
    
    for(double i = 1; i < N/4; i++)
    {
        a += sqrt(i);
    }
    return a;
}

double calc2()
{
    
    for(double i = N/4; i < 2*(N/4); i++)
    {
        b += sqrt(i);
    }
    return b;
}

double calc3()
{
    
    for(double i = 2*(N/4); i < 3*(N/4); i++)
    {
        c += sqrt(i);
    }
    return c;
}

double calc4()
{
    
    for(double i = 3*(N/4); i <= N; i++)
    {
        d += sqrt(i);
    }
    return d;
}


int main(void)
{
    pthread_t thread_id;
    pthread_t thread2;
    pthread_t thread3;
    pthread_t thread4;

    pthread_create (&thread_id, NULL, calc, NULL);
    pthread_create(&thread2, NULL, calc2, NULL);
    pthread_create (&thread3, NULL, calc3, NULL);
    pthread_create(&thread4, NULL, calc4, NULL);
  
    pthread_join(thread_id, NULL);
    pthread_join(thread2, NULL);
    pthread_join(thread3, NULL);
    pthread_join(thread4, NULL);

    double result = a+b+c+d;
    printf("\n%f\n", result);
    return 0;
}