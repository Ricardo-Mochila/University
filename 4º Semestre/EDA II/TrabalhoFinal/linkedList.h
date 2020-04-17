#include "structures.h"

void init(NODE** head);

void print_list(NODE* head);

NODE* add(NODE* node, struct voo data);

void add_at(NODE* node, struct voo data);

NODE* remove_node(NODE * head, char voidId[]);

NODE* searchList(NODE* head, char vooId[7]);
