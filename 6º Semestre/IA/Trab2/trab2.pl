quarto(l,c).

estado_inicial([quarto(1, 1)]).
estado_final([quarto(1,4)]).

dim(4).

bloqueadas([[quarto(1,1), quarto(1, 2)], [quarto(2,1), quarto(2, 2)], [quarto(3,1), quarto(4, 1)], [quarto(3,2), quarto(3, 3)], [quarto(4,2), quarto(4, 3)]]).

percorre_bloqueadas(_, []).
percorre_bloqueadas([A,B], [[C,D]|E]) :- [A,B] \= [C,D], [A,B] \= [D,C],percorre_bloqueadas([A,B],E).

peso([quarto(A,B)], E) :- estado_final([quarto(A, B)]), E is 0. 
peso([quarto(_,_)], E) :- E is 1. 

opr([quarto(A, B)], esquerda, [quarto(A, C)],1) :- C is B-1 ,C > 0, bloqueadas(BLOCK), percorre_bloqueadas([quarto(A, B),quarto(A, C)], BLOCK).
opr([quarto(A, B)], direita, [quarto(A, C)],1) :- dim(SIZE), C is B+1 ,C =< SIZE, bloqueadas(BLOCK), percorre_bloqueadas([quarto(A, B),quarto(A, C)], BLOCK).
opr([quarto(A, B)], cima, [quarto(C, B)],1) :- C is A-1 ,C > 0, bloqueadas(BLOCK), percorre_bloqueadas([quarto(A, B),quarto(C, B)], BLOCK).
opr([quarto(A, B)], baixo, [quarto(C, B)],1) :- dim(SIZE), C is A+1 ,C =< SIZE, bloqueadas(BLOCK), percorre_bloqueadas([quarto(A, B),quarto(C, B)], BLOCK).

pesquisa_local_hill_climbingSemCiclos(E, _) :- estado_final(E), write(E), write(' ').

pesquisa_local_hill_climbingSemCiclos(E, L) :- 
    write(E), write(' '),
	expande(E,LSeg),
	sort(3, @=<, LSeg, LOrd),
	obtem_no(LOrd, no(ES, Op, _)),
	\+ member(ES, L),
	write(Op), nl,
	(pesquisa_local_hill_climbingSemCiclos(ES,[E|L]) ; write(undo(Op)), write(' '), fail).

ins_ord(E, [], [E]).
ins_ord(no(E,Pai,Op,C,CF,P),  [no(E1,Pai1,Op1,C1,CF1,P1)|T], [no(E,Pai,Op,C,CF,P),no(E1,Pai1,Op1,C1,CF1,P1)|T]) :- CF =< CF1.
ins_ord(no(E,Pai,Op,C,CF,P), [no(E1,Pai1,Op1,C1,CF1,P1)|T], [no(E1,Pai1,Op1,C1,CF1,P1)|T1]) :- ins_ord(no(E,Pai,Op,C,CF,P), T, T1).	

insere_ordenado([],L,L).
insere_ordenado([A|T], L, LF):- ins_ord(A,L,L1),
								insere_ordenado(T, L1, LF).

expande(E, L):- 
	findall(no(En,Opn, Heur),
                (opr(E,Opn,En,_), peso(En, Heur)),
                L).

obtem_no([H|_], H).
obtem_no([_|T], H1) :-
	obtem_no(T, H1).

pesquisa :-
	estado_inicial(S0),
	pesquisa_local_hill_climbingSemCiclos(S0, []).