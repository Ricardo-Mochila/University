mdc(A, A, A).
mdc(A, B, C) :- B > A, D is B-A, mdc(A,D,C).
mdc(A, B, C) :- A > B, D is A-B, mdc(D,B,C).
