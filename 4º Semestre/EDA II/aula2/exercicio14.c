#include <stdio.h>

int main(void)
{   
    int input1;
    int input2;
    int input3;
    float coordenada1;
    float coordenada2;

    scanf("%d", &input1);
    printf("%d\n", input1);

    scanf("%d +%d =", &input2, &input3);
    printf("%d\n", input2+input3);

    scanf(" (%f,%f)", &coordenada1, &coordenada2);
    printf("(%0.3f,%0.3f)\n", coordenada1, coordenada2);


    return 0;
}