

struct list *list_new();

bool list_empty(struct list *list);

bool list_insert(struct list *list, int value);

int list_find(struct list *list, int value);

void list_print(struct list *list);

void list_destroy(struct listt *list);