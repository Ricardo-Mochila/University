#include <stdbool.h>
#define T 39;

struct elemento
{
    char marca[21];
    char modelo[21];
    unsigned short cilindrada;
    unsigned short anoIntrodução;
    unsigned short anoRetirado;
};

struct no[2*T-1]
{
    bool isLeaf;
    unsigned short contains;
    struct elemento elm[2*T-1];
    int filho[2*T];    
}

