#include <pthread.h>
#include <stdio.h>

/* Prints x’s to stderr. The parameter is unused. Does not return. */
void* print_xs (void* unused)
{
  int c = 0;
  while (c < 100) {
    fputc ('o', stderr);
    c++;
  }
  return NULL;
}

void* print_os (void* unused)
{
  int c = 0;
  while (c < 100) {
    fputc ('x', stderr);
    c++;
  }
  return NULL;
}

/* The main program. */
int main ()
{
  pthread_t thread_id;
  pthread_t thread2;
  /* Create a new thread. The new thread will run the print_xs() function. */
  pthread_create (&thread_id, NULL, &print_os, NULL);
  pthread_create(&thread2, NULL, &print_xs, NULL);
  
  pthread_join(thread_id, NULL);
  pthread_join(thread2, NULL);
  puts("");

  /*int c = 0;
  while( c < 5) {
    fputc ('o', stderr);
    c++;
  }*/

  return 0;
}