quarto(l,c).
bloqueadas([[quarto(1,1), quarto(1, 2)], [quarto(2,1), quarto(2, 2)], [quarto(3,1), quarto(4, 1)], [quarto(3,2), quarto(3, 3)], [quarto(4,2), quarto(4, 3)]]).
estado_inicial([quarto(1, 1)]).

dim(6).

estado_final([quarto(5,5)]).

percorre_bloqueadas(_, []).
percorre_bloqueadas(A, [B|C]):- A \= B ,percorre_bloqueadas(A,C).

peso([quarto(A,B)], [quarto(C,D)], E) :- F is A-C, G is B-D, E is F+G, E >= 0. 
peso([quarto(A,B)], [quarto(C,D)], E) :- F is A-C, G is B-D, H is F+G, H < 0, E is -H. 

opr([quarto(A, B)], direita, [quarto(A, C)],1) :- dim(SIZE), C is B+1 ,C =< SIZE, bloqueadas(BLOCK), percorre_bloqueadas([quarto(A, B),quarto(A, C)], BLOCK).
opr([quarto(A, B)], esquerda, [quarto(A, C)],1) :- C is B-1 ,C > 0, bloqueadas(BLOCK), percorre_bloqueadas([quarto(A, B),quarto(A, C)], BLOCK).
opr([quarto(A, B)], baixo, [quarto(C, B)],1) :- dim(SIZE), C is A+1 ,C =< SIZE, bloqueadas(BLOCK), percorre_bloqueadas([quarto(A, B),quarto(C, B)], BLOCK).
opr([quarto(A, B)], cima, [quarto(C, B)],1) :- C is A-1 ,C > 0, bloqueadas(BLOCK), percorre_bloqueadas([quarto(A, B),quarto(C, B)], BLOCK).

%representacao dos nos
%no(Estado,no_pai,Operador,Custo,Profundidade)

pesquisa_aux([no(E,Pai,Op,C,CF,P)|_],no(E,Pai,Op,C,CF,P)) :- 
	estado_final(E).
pesquisa_aux([E|R],Sol):- 
	expande(E,Lseg),
        insere_ordenado(Lseg,R,LFinal),
        pesquisa_aux(LFinal,Sol).

expande(no(E,Pai,Op,C,CF,P),L):- 
	findall(no(En,no(E,Pai,Op,C,CF,P), Opn, Cnn, CFF, P1),
                (opr(E,Opn,En,Cn), P1 is P+1, Cnn is Cn+C, estado_final(FIM), peso(En, FIM, H), CFF is Cnn + H ),
                L).

pesquisa :-
	estado_inicial(S0),
	pesquisa_aux([no(S0,[],[],0,0,0)], S),
	write(S), nl.


ins_ord(E, [], [E]).
ins_ord(no(E,Pai,Op,C,CF,P), [no(E1,Pai1,Op1,C1,CF1,P1)|T], [no(E,Pai,Op,C,CF,P),no(E1,Pai1,Op1,C1,CF1,P1)|T]) :- C =< C1.
ins_ord(no(E,Pai,Op,C,CF,P), [no(E1,Pai1,Op1,C1,CF1,P1)|T], [no(E1,Pai1,Op1,C1,CF1,P1)|T1]) :-
	ins_ord(no(E,Pai,Op,C,CF,P), T, T1).	

insere_ordenado([],L,L).
insere_ordenado([A|T], L, LF):- 
	ins_ord(A,L,L1),
	insere_ordenado(T, L1, LF).



