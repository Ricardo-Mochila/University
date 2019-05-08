#include <stdio.h>
#include "abp.h"
#include <string.h>
#include <stdbool.h>

struct no *ligacoes[300000];

int main()
{
    int s;
    scanf(" %d", &s);

    int a;
    int b;

    char temp;
    while (scanf(" %c", &temp) != EOF)
    {
        if (temp == '+')
        {
            scanf(" %d %d", &a, &b);
            ligacoes[a]=insere(b, ligacoes[a]);
        }
        else if (temp == '-')   
        {
            scanf(" %d %d", &a, &b);
            ligacoes[a]=remover(b, ligacoes[a]);
        }
        else if (temp == '?')
        {
            int nl; //numero de ligacoes
            int x = 0;

            scanf(" %d", &nl);
            int temporario[nl + 1];

            while (x < nl+1)
            {
                scanf(" %d", &temporario[x]);
                x++;
            }
            
            int primeiro=temporario[0];
            int segundo=temporario[x-1];
            bool flag=true;
            for (int i = 0; i < x-1; i++) //por cada posicao do temporario
            {
                //printf("%d, %d\n", temporario[i], temporario[i+1]);
                //printEmOrdem(ligacoes[temporario[i]]);
                int j = i +1;
                if ( pesq(temporario[j], ligacoes[temporario[i]]) == NULL)
                {
                    flag=false;
                    break;
                }
            }
            if(flag==true)
            {
                printf("yes [%d..%d]\n", primeiro, segundo);
            }
            else
            {
                printf("no [%d..%d]\n", primeiro, segundo);
            }
        }
    }
}