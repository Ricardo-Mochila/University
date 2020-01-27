
sum([], 0).
sum([A|B], C) :- sum(B, X1), C is X1+A.

prod([A], A).
prod([A|B], C) :- prod(B, X1), C is X1*A. 

len([], 0).
len([A|B], C) :- len(B, X1), C is X1 + 1.

compr(S, Z, [A|B], X):- compr(S, Z, A, X),

roman(z).
roman(i(X)) :- roman(X).
roman(x(X)) :- roman(X).
roman(v(X)) :- roman(X).
roman(l(X)) :- roman(X).
roman(c(X)) :- roman(X).
roman(m(X)) :- roman(X).
roman(d(X)) :- roman(X).



romano_desfaz(z, B) :- B is 0.
romano_desfaz(i(A), B) :- romano_desfaz(A, Y), B is Y + 1.
romano_desfaz(v(A), B) :- romano_desfaz(A, Y), B is Y + 5.
romano_desfaz(x(A), B) :- romano_desfaz(A, Y), B is Y + 10.
romano_desfaz(l(A), B) :- romano_desfaz(A, Y), B is Y + 50.
romano_desfaz(c(A), B) :- romano_desfaz(A, Y), B is Y + 100.
romano_desfaz(m(A), B) :- romano_desfaz(A, Y), B is Y + 500.
romano_desfaz(d(A), B) :- romano_desfaz(A, Y), B is Y + 1000.










/* romano_faz(A, B) :- A >= 1000, A1 is A - 1000, romano_faz(A1, d(B)).
romano_faz(A, B) :- A >= 500,  A1 is A - 500, romano_faz(A1, m(B)).
romano_faz(A, B) :- A >= 100,  A1 is A - 100, romano_faz(A1, c(B)).
romano_faz(A, B) :- A >= 50,  A1 is A - 50, romano_faz(A1, l(B)).
romano_faz(A, B) :- A >= 10,  A1 is A - 10, romano_faz(A1, x(B)).
romano_faz(A, B) :- A >= 5,  A1 is A - 5, romano_faz(A1, v(B)).
romano_faz(A, B) :- A >= 1,  A1 is A - 1, romano_faz(A1, i(B)).
romano_faz(0, B) :- A1 is A - 1 romano_faz(A1, B).
romano_faz(-1, B).
 */
 
romano_faz(N, R ):- R == r_f(N,z) .
r_f(A,B) :- A >= 1000, A1 is A - 1000, r_f(A1, _,d(B)).
r_f(A,B) :- A >= 500,  A1 is A - 500, r_f(A1, m(B)).
r_f(A,B) :- A >= 100,  A1 is A - 100, r_f(A1,c(B)).
r_f(A,B) :- A >= 50,  A1 is A - 50, r_f(A1,l(B)).
r_f(A,B) :- A >= 10,  A1 is A - 10, r_f(A1, x(B)).
r_f(A,B) :- A >= 5,  A1 is A - 5, r_f(A1, v(B)).
r_f(A,B) :- A >= 1,  A1 is A - 1, r_f(A1, i(B)).
r_f(0,B).






/* romano_faz(A, B) :- mod(B, 2), romano_faz(z, B).
romano_faz(A, B) :- A >= 500,  A1 is A - 500, romano_faz(A1, m(B)).
romano_faz(A, B) :- A >= 100,  A1 is A - 100, romano_faz(A1, c(B)).
romano_faz(A, B) :- A >= 50,  A1 is A - 50, romano_faz(A1, l(B)).
romano_faz(A, B) :- A >= 10,  A1 is A - 10, romano_faz(A1, x(B)).
romano_faz(A, B) :- A >= 5,  A1 is A - 5, romano_faz(A1, v(B)).
romano_faz(A, B) :- A >= 1,  A1 is A - 1, romano_faz(A1, i(B)).
romano_faz(0, z).  */