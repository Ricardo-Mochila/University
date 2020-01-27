num(z).
num(s(X)) :- num(X).

soma(z, A, A).
soma(s(A), B, s(C)) :- soma(A, B, C).

mult(z,_,z).
mult(s(A), B, C):- mult(A, B, D), soma(B, D, C).

fact(s(z), s(z)).
fact(s(X), Y) :- fact(X, A), mult(A, s(X), Y).

divisivel(X, Y) :- mult(Y, _, X).

pares([], []).
pares([_],[]).
pares([_,A|As], [A|AAs]) :- pares(As, AAs).

multA(A, B, C) :- C is A*B.

somaA(A, B, C) :- C is A+B.

quadradoA(A, B) :- multA(A, A, B).

avalia([C,D,E], X, Y) :-
                    quadradoA(X, B), multA(B, E, G),
                    multA(D, X, H), 
                    somaA(G, H, J), 
                    somaA(J, C, Y).  
 
fib(z, z).
fib(s(z), s(z)).
fib(s(s(A)), X) :- fib(s(A), Z), fib(A, Y), soma(Z, Y, X).

grau([_], 0).
grau([_|B], Z) :- grau(B, X), somaA(X, 1, Z).

potA(_, 0, 1).
potA(A, p(B),X) :- potA(A, B, Z), multA(A, Z, X).

