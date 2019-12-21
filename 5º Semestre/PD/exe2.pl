num(z).
num(s(X)) :- num(X).

mais1(A, s(A)).

soma(z, A, A).
soma(s(A), B, s(C)) :- soma(A, B, C).

sub(A,B,C) :- soma(B,C,A).

dobro(A,B) :- soma(A, A, B).

mult(z,_,z).
mult(s(A), B, C):- mult(A, B, D), soma(B, D, C).

pot(A, s(z) , A).
pot(A, s(B), C) :- pot(A, B, D), mult(A, D, C).

quadrado(A, B) :- mult(A, A, B).
