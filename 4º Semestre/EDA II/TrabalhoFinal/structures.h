struct aeroporto
{
    char identA[5];
    short horaA;
    short minA;
};

struct voo
{
    char identV[7];
    short horaV;
    short minV;
    int dur;
    char aeroP[5];
    char aeroC[5];
    long Peso;
};

typedef struct node 
{
    struct voo voo;
    struct node* next;
} NODE;

struct trajecto
{
    NODE* node;
    struct aeroporto aero;
};