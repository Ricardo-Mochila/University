/* Exercicio 1 a)*/
:- dynamic(fib_cache/2).
fib(0, 1).
fib(1, 1).
fib(X, Z):- fib_cache(X,Z), !.
fib(X, Z) :- X > 1, X1 is X-1, X2 is X-2, fib(X1, S), fib(X2, S1), Z is S+S1, assertz(fib_cache(X,Z)).

% /* Exercicio 1d) */
% :- dynamic(fib1/2).
% fib1(0,1).
% fib1(1,1).
% fib1(X, Z) :- X > 1, X1 is X-1, X2 is X-2, fib1(X1, S), fib1(X2, S1), Z is S+S1, asserta(fib1(X, Z))g.

impare([], []).
impare([A], [A]).
impare([A,_|C], [A|X]) :- impare(C, X).

pares([], []).
pares([A], []).
pares([_,B|C], [B|X]) :- pares(C,X).
