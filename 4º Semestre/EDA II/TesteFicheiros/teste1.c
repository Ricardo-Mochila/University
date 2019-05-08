#include <stdio.h>
#include <stdlib.h>

void introduce(FILE *file)
{
    int num = 0;
    char result[2];
    scanf("%d", &num);
    sprintf(result, "%d", num);
    fseek(file, num, SEEK_SET);
    fprintf(file, result);
    fclose(file);
}

void read(FILE *file, int n)
{
    char a[2];
    fseek(file, n, SEEK_SET);
    fread(&a, 1, 1, file);
    printf("%s\n", a);
    fclose(file);
}

int main()
{
    FILE* file;
    int a = 0;
    file = fopen("test.txt", "r+");
    if(file == NULL)
    {
        file = fopen("test.txt", "w+");
    }
    //introduce(file);
    scanf("%d" ,&a);
    read(file, a);
    fclose(file);

    return 0;
}
