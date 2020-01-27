lista([]).                  % Lista() diz que é uma lista 
lista([_|L]) :- lista(L).

membro(X, [X|_]).                %Ve se o elemento está na lista
membro(X, [_|L]) :- membro(X, L).

prefixo([], _).                 %Vê se um elemento ou lista é prefixo de outro 
prefixo([X|A], [X|B]) :- prefixo(A, B).

sufixo(A, A).                       %Vê se um elemento ou lista é sufixo de outro
sufixo(A, [_|B]) :- sufixo(A, B).

sublista(S, L) :- prefixo(S, L).            %vê se é um elemento de uma lista ou não
sublista(S, [_|L]) :- sublista(S, L).

catena([], L, L).                               %junta duas listas  
catena([X|Xs], L, [X|Y]) :- catena(Xs, L, Y).

ultimo(X, Y) :- catena(_, [X], Y).          

adjacente(X, Y, Z) :- catena(_, [X, Y|_], Z).

nrev([], []).
nrev([X|A], B) :-nrev(A, AR),catena(AR, [X], B).

rev(L, R) :- rev(L, [], R).

rev([], R, R).
rev([A|B], X, R) :- rev(B, [A|X], R).

compr([], z).
compr([_|T], s(X)) :- compr(T, X).

compr([], 0).
compr([_|T], X) :- compr(T, Y), X is Y+1.

sel(E, [E|L], L).
sel(E, [X|L], [X|M]) :- sel(E, L, M).

perm([], []).
perm(L, [X|LP]) :-sel(X, L, LX),perm(LX, LP).

ord([]).
ord([_]).
ord([A,B|X]) :- A<B, ord([B|X]).

isort(I, S) :- isort(I, [], S).
isort([], S, S).
isort([X|Xs], SI, SO) :- insord(X, SI, SX), isort(Xs, SX, SO).

insord(X, [], [X]).
insord(X, [A|As], [X,A|As]) :- X=<A.
insord(X, [A|As], [A|AAs]) :- X>A, insord(X, As, AAs). 
 



