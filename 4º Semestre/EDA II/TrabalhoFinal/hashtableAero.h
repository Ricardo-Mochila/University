#define SIZE 400009
struct trajecto *hashTableAero[SIZE];

unsigned long hashAero(char *str);

void insertAero(struct aeroporto aero, NODE* node);

struct trajecto *searchAero(char aeroId[7]);

void displayAero();