quarto(l,c).
bloqueadas([[quarto(1,1), quarto(1, 2)], [quarto(2,1), quarto(2, 2)], [quarto(3,1), quarto(4, 1)], [quarto(3,2), quarto(3, 3)], [quarto(4,2), quarto(4, 3)]]).
estado_inicial([quarto(1, 1)]).

dim(4).

estado_final([quarto(4,4)]).

percorre_bloqueadas(_, []).
percorre_bloqueadas(A, [B|C]):- A \= B ,percorre_bloqueadas(A,C).

opr([quarto(A, B)], direita, [quarto(A, C)],1) :- dim(SIZE), C is B+1 ,C =< SIZE, bloqueadas(BLOCK), percorre_bloqueadas([quarto(A, B),quarto(A, C)], BLOCK).
opr([quarto(A, B)], esquerda, [quarto(A, C)],1) :- C is B-1 ,C > 0, bloqueadas(BLOCK), percorre_bloqueadas([quarto(A, B),quarto(A, C)], BLOCK).
opr([quarto(A, B)], baixo, [quarto(C, B)],1) :- dim(SIZE), C is A+1 ,C =< SIZE, bloqueadas(BLOCK), percorre_bloqueadas([quarto(A, B),quarto(C, B)], BLOCK).
opr([quarto(A, B)], cima, [quarto(C, B)],1) :- C is A-1 ,C > 0, bloqueadas(BLOCK), percorre_bloqueadas([quarto(A, B),quarto(C, B)], BLOCK).

pesquisa_aux(_, [no(E,Pai,Op,C,P)|_],no(E,Pai,Op,C,P)) :- 
	estado_final(E).
pesquisa_aux(N, [no(E,Pai,Op,C,P)|R],Sol):- 
	P < N,
	expande(no(E,Pai,Op,C,P),Lseg),
        insere_inicio(Lseg,R,LFinal),
        pesquisa_aux(N, LFinal,Sol).
pesquisa_aux(N, [no(_,_,_,_,P)|R],Sol):- 
	P == N,
        pesquisa_aux(N, R, Sol).


expande(no(E,Pai,Op,C,P),L):- 
	findall(no(En,no(E,Pai,Op,C,P), Opn, Cnn, P1),
                (opr(E,Opn,En,Cn), P1 is P+1, Cnn is Cn+C),
                L).

p_iterativa(N, S0, S) :-
	pesquisa_aux(N, S0, S).

p_iterativa(N, S0, S) :-
	N1 is N + 1,
	p_iterativa(N1, S0, S).

pesquisa :-
	estado_inicial(S0),
	p_iterativa(1, [no(S0,[],[],0,0)], S),
	write(S), nl.


insere_inicio(A,B,C) :- append(A, B, C).
insere_fim(A,B,C) :- append(B, A, C).

