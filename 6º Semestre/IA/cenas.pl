%estado_inicial
estado_inicial(agente(1,1)).

dim(4).

bloq((1,1), (1,2)).
bloq((2,1), (2,2)).
bloq((3,1), (4,1)).
bloq((3,2), (3,3)).
bloq((4,2), (4,3)).

%verificar o bloqueio
ublock((A,B),(C,D)) :- \+ bloq((A,B),(C,D)), \+ bloq((C,D),(A,B)).

%estado_final(Estado)
estado_final(agente(4,4)).

%representacao dos operadores
%op(Eact,OP,Eseg,Custo)

%MoveEsquerda
op(agente(X,Y), esquerda,agente(X,P),1):- P is Y-1, Y \= 1, ublock((X,Y),(X,P)) .

%MoveDireita
op(agente(X,Y), direita,agente(X,P),1):-dim(D), P is Y+1, Y \= D,  ublock((X,Y),(X,P)).

%MoveCima
op(agente(X,Y), cima,agente(P,Y),1):-P is X-1, X \= 1,  ublock((X,Y),(P,Y)).

%MoveBaixo
op(agente(X,Y), baixo,agente(P,Y),1):-dim(D), P is X+1, X\= D,  ublock((X,Y),(P,Y)).

%Heuristica
dist(agente(A,B),S) :- estado_final(agente(F1,F2)) ,S is (F1 - A) + (F2 - B), S >= 0.
dist(agente(A,B),S) :- estado_final(agente(F1,F2)),S is -((F1 - A) + (F2 - B)), S >= 0.


%representacao dos nos
%no(Estado,no_pai,Operador,Custo,CHeur,Profundidade)

pesquisa_aux([no(E,Pai,Op,C,CH,P)|_],no(E,Pai,Op,C,CH,P)) :- 
	estado_final(E).

pesquisa_aux([E|R],Sol):- 
		expande(E,Lseg),
        insere_ordenado(Lseg,R,LFinal),
		length(LFinal,Size),
		write(Size),nl,
        pesquisa_aux(LFinal,Sol).

expande(no(E,Pai,Op,C,CH,P),L):- 
	findall(no(En,no(E,Pai,Op,C,CH,P), Opn, Cnn,CHn, P1),
                (op(E,Opn,En,Cn), P1 is P+1, Cnn is Cn+C,dist(En,H), CHn is Cnn + H),
                L).

pesquisa :-
	estado_inicial(S0),
	pesquisa_aux([no(S0,[],[],0,0,0)], S),
	write(S), nl.

ins_ord(E, [], [E]).
ins_ord(no(E,Pai,Op,C,CH,P), [no(E1,Pai1,Op1,C1,CH1,P1)|T], [no(E,Pai,Op,C,CH,P),no(E1,Pai1,Op1,C1,CH1,P1)|T]) :- CH =< CH1.
ins_ord(no(E,Pai,Op,C,CH,P), [no(E1,Pai1,Op1,C1,CH1,P1)|T], [no(E1,Pai1,Op1,C1,CH1,P1)|T1]) :-
	ins_ord(no(E,Pai,Op,C,CH,P), T, T1).	

insere_ordenado([],L,L).
insere_ordenado([A|T], L, LF):- 
	ins_ord(A,L,L1),
	insere_ordenado(T, L1, LF).


