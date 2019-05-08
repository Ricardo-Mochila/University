#include <stdio.h>

void troca(int* a, int* b)
{
    int c = *a;
    *a = *b;
    *b = c; 
}

int main(void)
{
  int a, b;

  // lê dois valores inteiros
  printf("insira dois inteiros: "); scanf("%d %d", &a, &b);

  // mostra-os
  printf("a = %d, b = %d\n", a, b);

  troca(&a, &b);	// troca os valores das variáveis

  // mostra os valores depois da troca
  printf("a = %d, b = %d\n", a, b);

  return 0;
}