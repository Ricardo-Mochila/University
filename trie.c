/* trie implementation, with arrays */

#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

#include "trie.h"

#define ALPHABET_MIN 'a'
#define ALPHABET_MAX 'z'
#define ALPHABET_SIZE (ALPHABET_MAX - ALPHABET_MIN + 1)

#define POS(c)  ((c) - ALPHABET_MIN)	// character position in alphabet
#define CHAR(n) ((n) + ALPHABET_MIN)	// n-th alphabet character


/* trie node */
struct node {
  struct node *child[ALPHABET_SIZE];	// children
  bool        word;			// end-of-word mark
};

/* trie */
struct trie {
  struct node *root;
};


/*
  Allocates and returns a new trie node.
*/
static struct node *node_new(void)
{
  struct node *node = malloc(sizeof(*node));

  if (node != NULL)
    {
      node->word = false;

      for (int i = 0; i < ALPHABET_SIZE; ++i)
	node->child[i] = NULL;
    }

  return node;
}


/* Frees a trie NODE. */
static void node_free(struct node *node)
{
  free(node);
}


/* Destroys the sub-trie with root NODE. */
static void node_destroy(struct node *node)
{
  if (node == NULL)
    return;

  for (int i = 0; i < ALPHABET_SIZE; ++i)
    node_destroy(node->child[i]);

  node_free(node);
}


/*
  Creates and returns a new, empty trie.
*/
struct trie *trie_new(void)
{
  struct trie *trie = malloc(sizeof(struct trie));

  if (trie != NULL)
    trie->root = NULL;

  return trie;
}


/* Destroys trie T, freeing the memory it occupies. */
void trie_destroy(struct trie *t)
{
  node_destroy(t->root);

  free(t);
}


/* Checks whether trie T is empty. */
bool trie_empty(struct trie *t)
{
  return t->root == NULL;
}


/*
  Inserts word P into trie T.

  Returns true if the word was successfully inserted (or was already
  in the trie), false otherwise.
*/
bool trie_insert(struct trie *t, char p[])
{
  struct node *n;
  int i = 0;

  if (t->root == NULL)
    t->root = node_new();	// new, empty root node

  if (t->root == NULL)
    return false;

  n = t->root;

  // follow the word down the trie as long as possible,
  // taking care not to go to a nonexisting node
  while (p[i] != '\0' && n->child[POS(p[i])] != NULL)
    {
      n = n->child[POS(p[i])];
      i++;
    }

  // insert the new suffix into the trie
  while (p[i] != '\0')
    {
      n->child[POS(p[i])] = node_new();

      if (n->child[POS(p[i])] == NULL)
	return false;

      n = n->child[POS(p[i])];

      i++;
    }

  n->word = true;

  return true;
}

/* int wordCount(struct trie *trie) 
{ 
    int result = 0; 
  
    // Leaf denotes end of a word 
    if (trie -> root-> word) 
        result++; 
      
    for (int i = 0; i < ALPHABET_SIZE; i++)     
      if (trie -> root-> child[i]) 
         result += wordCount(trie -> root-> child[i]); 
     
    printf("\n%d", result);
    return result;    
}  */



/* Prints the full contents of trie T. */

/*void trie_print(struct trie *t)
{
  ///inicializo a trie e mando o t->root para print nodes 
  int i=0;

  if (t->root == NULL)
    return NULL;

  struct node* n;
  n = t->root;

  while(i<ALPHABET_SIZE)
  {
    if(n->child[i] != NULL)
    {
      
      printf("%c ", (char)i);
      t->root = n->child[i];
      trie_print(t);
      
    }
    if(n->child[i]->word)
    {
      printf("\n");
      break;
    }
    i++;
    n = n->child[i];
    
  } 
}*/

void print_nodes(struct node* n, int i)
{
  if(i < ALPHABET_SIZE)
  {
    if(n->child[i] != NULL && n->child[i]->word)
    {
      printf("%c\n", i+'a');

    }
    if(n->child[i] != NULL && !n->child[i]->word)
    {
      printf("%c", i+'a');
      n = n->child[i];
      print_nodes(n, 0);    
    }
    
    else
    {
      print_nodes(n, i+1);
    } 
  }
  
}


void trie_print(struct trie *t)
{
  char * str;
  if (t->root == NULL)
    return;

  struct node* n;
  n = t->root;

  print_nodes(n, str);

}

void print_word(struct trie *trie, char letter)
{
  struct node *temp = trie -> root;
  if(temp->child[POS(letter)]!=NULL)
  {
    //printf("%c", child)
  }
}
