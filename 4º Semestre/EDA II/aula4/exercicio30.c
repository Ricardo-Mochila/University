#include <stdio.h>

FILE* openFile(char name[])
{
    FILE* file;
    file = fopen(name, "r+");
    if(file == NULL)
    {
        file = fopen(name, "w+");
        int zero = 0;
        for(int i = 0; i < 10; i++)
        {
            fwrite(&zero, sizeof(zero), 1, file);
        }
    }
    return file;
}

int le(FILE* file, long pos)
{
    int a = 0;
    fseek(file, (pos-1)*pos, SEEK_CUR);
    fread(&a, 1, 1, file);
    return a;

}

void escreve(FILE* file, int value)
{   
    fseek(file, (value-1)*sizeof(int), SEEK_END);
    fwrite(&value, sizeof(value),1, file);
}

int main(void)
{
    FILE* ficheiro;
    ficheiro = openFile("test");
    int value = le(ficheiro, 0);
    printf("%d\n", value);
    escreve(ficheiro, 11);
    value = le(ficheiro, 40);
    printf("%d\n", value);
    return 0;
}