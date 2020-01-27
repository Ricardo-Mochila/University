num(z).
num(s(A)) :- num(A).

soma(z, A, A).
soma(s(A), B, s(C)) :- soma(A, B, C) .

mult(z,_,z).
mult(s(A), B, C) :- mult(A, B, Y), soma(B, Y, C).

fact(z, 1).
fact(s(A), B) :- fact(A, C), mult(s(A), A, Y), soma(s(A), Y, B).

pares([], []).
pares([A,B|C], [B|X]) :- pares(C, X).
