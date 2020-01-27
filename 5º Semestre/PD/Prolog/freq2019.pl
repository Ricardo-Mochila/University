/*Exercicio 1 a)*/
num(z).
num(s(z)) :- num(z).

num(z, 0).
num(s(A), Z) :- num(A, X), Z is X+1.

/*Exercicio 1 b)*/
multA(A, B, C) :- C is A*B.

somaA(A, B, C) :- C is A+B.

quadradoA(A, B) :- multA(A, A, B).

/* Exercicio 3a */
avalia([A, B, C], X, Y) :- 
    quadradoA(X, Z), 
    multA(C, Z, D), 
    multA(B, X, E),
    somaA(D, E, G), 
    somaA(G, A, Y).

/* Exercicio 2 */
pares([], []).
pares([_], []).
pares([A, B|C], [B|D]) :- pares(C, D).


