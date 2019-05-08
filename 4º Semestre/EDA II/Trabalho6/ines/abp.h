
struct no
{
    int elemento;
    struct no *esq;
    struct no *drt;
};

struct no *criar(int elem);
struct no *insere(int elemento, struct no *guarda);
struct no *minimo(struct no *min);
struct no *remover(int elemento, struct no *delete);
void printEmOrdem(struct no *print);
struct no *pesq(int elemento,struct no *pesquisa);