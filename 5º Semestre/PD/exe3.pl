
sum([], 0).
sum([A|B], C) :- sum(B, X1), C is X1+A.

prod([A], A).
prod([A|B], C) :- prod(B, X1), C is X1*A. 

len([], 0).
len([A|B], C) :- len(B, X1), C is X1 + 1.

compr(S, Z, [A|B], X):- compr(S, Z, A, X),