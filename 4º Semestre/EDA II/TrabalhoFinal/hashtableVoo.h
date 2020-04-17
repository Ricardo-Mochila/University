struct voo_aero
{
    char vooId[7];
    char aeroId[5];
};

unsigned long hash(char *str);

struct voo_aero *searchVoo(char voo[7]);

void insertVoo(char voo[7], char aerop[5]);

struct voo_aero *deleteVoo(char vooId[7]);

void displayVoo();