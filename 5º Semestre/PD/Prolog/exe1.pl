filho(ricardo, joao). 
filho(ricardo, mariana). 
filho(tiago, joao). 
filho(tiago, mariana). 
filho(kevin, guida).
filho(guida, custodio).
filho(guida, luisa).
filho(joao, custodio).
filho(joao, luisa).

homem(joao).
homem(ricardo).
homem(tiago).
homem(custodio).
homem(kevin).

mulher(luisa).
mulher(guida).
mulher(mariana).

progenitor(B, A) :- filho(A,B).
irmao(A,B) :- progenitor(A,X), progenitor(B,X), A \= B. 
neto(A, B) :- progenitor(X, A), progenitor(B, X).
pai(A,B) :- progenitor(A,B) , homem(B).
mae(A,B) :- progenitor(A,B) , mulher(B).
primo(A,B) :- progenitor(A,X), progenitor(B,Y), irmao(X, Y).

antepassado(A,B) :- progenitor(A, B).
antepassado(A,B) :- progenitor(A,X), antepassado(X, B).