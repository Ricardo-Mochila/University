
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int pilha[10];   //array de inteiros,
int top = -1;   //top iniciado a -1 para quando der push o

void push(int pilha[10], int a) {  //função para colocar um valor na pilha
    pilha[++top] = a;
}

int pop(int pilha[10]) {
    top--;
}


int soma(int pilha[10]) {
    int a, b, c;
    a = pilha[top];
    pop(pilha);
    b = pilha[top];
    pop(pilha);
    c = a + b;
    push(pilha, c);
    return c;
}

int sub(int pilha[10]) {
    int a, b, c;
    a = pilha[top];
    pop(pilha);
    b = pilha[top];
    pop(pilha);
    c = b-a;
    push(pilha, c);
    return c;
}

int multi(int pilha[10]) {
    int a, b, c;
    a = pilha[top];
    pop(pilha);
    b = pilha[top];
    pop(pilha);
    c = a * b;
    push(pilha, c);
    return c;
}


int divi(int pilha[10]) {
    int a, b, c;
    a = pilha[top];
    pop(pilha);
    b = pilha[top];
    pop(pilha);
    c = b / a;
    push(pilha, c);
    return c;
}

int neg(int pilha[10]) {
    int a, c;
    a = pilha[top];
    pop(pilha);
    c = -a;
    push(pilha, c);
    return c;
}

int swap(int pilha[10]) {
    int a, b, c;
    a = pilha[top];
    pop(pilha);
    b = pilha[top];
    pop(pilha);
    push(pilha, a);
    push(pilha, b);
}

int dup(int pilha[10]) {
    int a, b;
    a = pilha[top];
    b = a;
    push(pilha, b);
}

int drop(int pilha[10]) {
    pop(pilha);

}

int clear(int pilha[10]) {
    while (top != -1) {
        pop(pilha);
    }
}

int main() {
    int a, b, i;
    int count = 0;
    char str[100];
    char adicao[10] = "+";
    char subtracao[10] = "-";
    char multiplicacao[10] = "*";
    char divisao[10] = "/";
    char negacao[10] = "neg";
    char trocar[10] = "swap";
    char duplicacao[10] = "dup";
    char eliminar[10] = "drop";
    char apagar[10] = "clear";
    char desligar[10] = "off";
    char help[10] = "help";
    char print[10] = "\0";
    printf("\n***************************************************");
    printf("\n*** RPN - Reverse Polish Notation Calculator    ***");
    printf("\n*** Vicente Romão (45467) e João Fortio (45274) ***");
    printf("\n*** ASC1 2019/2020                              ***");
    printf("\n***************************************************");
    printf("\ntype ’help’ for available commands\n");
    printf("\n stack:\n(empty)\n");
    printf("->");


    while (strcmp(str, desligar) != 0) {
        scanf("%s", str);


        if (strcmp(str, adicao) == 0) {
            soma(pilha);
            printf("stack:\n");
            for (i = 0; i <= top; i++) {
                printf("%d\n", pilha[i]);
            }
        } else if (strcmp(str, subtracao) == 0) {
            sub(pilha);
            printf("stack:\n");
            for (i = 0; i <= top; i++) {
                printf("%d\n", pilha[i]);
            }
        } else if (strcmp(str, multiplicacao) == 0) {
            multi(pilha);
            printf("stack:\n");
            for (i = 0; i <= top; i++) {
                printf("%d\n", pilha[i]);
            }


        } else if (strcmp(str, divisao) == 0) {
            divi(pilha);
            printf("stack:\n");
            for (i = 0; i <= top; i++) {
                printf("%d\n", pilha[i]);
            }
        } else if (strcmp(str, negacao) == 0) {
            neg(pilha);
            printf("stack:\n");
            for (i = 0; i <= top; i++) {
                printf("%d\n", pilha[i]);
            }

        } else if (strcmp(str, trocar) == 0) {
            swap(pilha);

            for (i = 0; i <= top; i++) {
                printf("%d\n", pilha[i]);
            }
        } else if (strcmp(str, duplicacao) == 0) {
            dup(pilha);
            printf("stack:\n");
            for (i = 0; i <= top; i++) {
                printf("%d\n", pilha[i]);
            }

        } else if (strcmp(str, eliminar) == 0) {
            drop(pilha);

            printf("stack:\n");
            for (i = 0; i <= top; i++) {
                printf("%d\n", pilha[i]);
            }


        } else if (strcmp(str, apagar) == 0) {
            clear(pilha);
            printf("stack:\n(empty)\n");

        } else if (strcmp(str, help) == 0) {
            printf("+ --> Adição");
            printf("\n- --> Subtracção");
            printf("\n* --> Multiplicação");
            printf("\n/ --> Divisão");
            printf("\nneg --> Calcula o simétrico");
            printf("\nswap --> Troca posição dos dois operandos do topo da pilha");
            printf("\ndup --> Duplica (clone) o operando do topo da pilha");
            printf("\ndrop --> Elimina um operando do topo da pilha");
            printf("\nclear --> Limpa toda a pilha");
            printf("\noff --> Desliga a calculadora\n");
        } else{
            a = atoi(str);
            push(pilha, a);
            if (strlen(str)<3)
            {
                if(strcmp(&str[strlen(str)-1],"\n")) {
                    printf("stack:\n");
                    for (i = 0; i <= top; i++) {
                        printf("%d\n", pilha[i]);
                    }
                }
            }
            
            
        }
        printf("->");
    }
    if(str[strlen(str)-1]=='\n') {
        printf("stack:\n");
        for (i = 0; i <= top; i++) {
            printf("%d\n", pilha[i]);
        }
    }

printf("bye!");
}

