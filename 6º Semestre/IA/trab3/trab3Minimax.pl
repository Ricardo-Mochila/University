% cada posicao pode ter "x", "o" ou "v" (vazio)
estado_inicial([[v,v,v,v,v],[v,v,v,v,v],[v,o,v,o,x],[v,o,v,x,x]]).

%estado_inicial([[v,v,v],[v,v,v],[v,v,v]]).

terminal(G) :- linhas(G,_).
terminal(G) :- colunas(G,_).
terminal(G) :- diagonal(G,_).
terminal(G) :- cheio(G).

continua(G, false) :- terminal(G), valor(G, A), A is 1, write('Perdeste'), !.
continua(G, false) :- terminal(G), valor(G, A), A is -1, write('Ganhaste'), !.
continua(_, true).

linhas([A, _, _, _,_],X) :- linhasHelp(A, X), X \= v.
linhas([_, A, _, _,_],X) :- linhasHelp(A, X), X \= v.
linhas([_ ,_ ,A, _,_],X) :- linhasHelp(A, X), X \= v.
linhas([_ ,_ ,_, A,_],X) :- linhasHelp(A, X), X \= v.
linhas([_ ,_ ,_, _,A],X) :- linhasHelp(A, X), X \= v.

linhasHelp([X, X, X, _, _], X) :- X \= v.
linhasHelp([_, X, X, X, _], X) :- X \= v.
linhasHelp([_, _, X, X, X], X) :- X \= v.

colunas([[X,_,_,_,_],[X,_,_,_,_], [X,_,_,_,_], [_,_,_,_,_]],X) :- X \= v.
colunas([[_,_,_,_,_],[X,_,_,_,_], [X,_,_,_,_], [X,_,_,_,_]],X) :- X \= v.
colunas([[_,X,_,_,_],[_,X,_,_,_], [_,X,_,_,_], [_,_,_,_,_]],X) :- X \= v.
colunas([[_,_,_,_,_],[_,X,_,_,_], [_,X,_,_,_], [_,X,_,_,_]],X) :- X \= v.
colunas([[_,_,X,_,_],[_,_,X,_,_], [_,_,X,_,_], [_,_,_,_,_]],X) :- X \= v.
colunas([[_,_,_,_,_],[_,_,X,_,_], [_,_,X,_,_], [_,_,X,_,_]],X) :- X \= v.
colunas([[_,_,_,X,_],[_,_,_,X,_], [_,_,_,X,_], [_,_,_,_,_]],X) :- X \= v.
colunas([[_,_,_,_,_],[_,_,_,X,_], [_,_,_,X,_], [_,_,_,X,_]],X) :- X \= v.
colunas([[_,_,_,_,X],[_,_,_,_,X], [_,_,_,_,X], [_,_,_,_,_]],X) :- X \= v.
colunas([[_,_,_,_,_],[_,_,_,_,X], [_,_,_,_,X], [_,_,_,_,X]],X) :- X \= v.
 
diagonal([[X,_,_,_,_],[_,X,_,_,_],[_,_,X,_,_], [_,_,_,_,_]],X) :- X \= v.
diagonal([[_,_,_,_,_],[X,_,_,_,_],[_,X,_,_,_], [_,_,X,_,_]],X) :- X \= v.
diagonal([[_,_,_,_,_],[_,X,_,_,_],[_,_,X,_,_], [_,_,_,X,_]],X) :- X \= v.
diagonal([[_,X,_,_,_],[_,_,X,_,_],[_,_,_,X,_], [_,_,_,_,_]],X) :- X \= v.
diagonal([[_,_,_,_,_],[_,_,X,_,_],[_,_,_,X,_], [_,_,_,_,X]],X) :- X \= v.
diagonal([[_,_,X,_,_],[_,_,_,X,_],[_,_,_,_,X], [_,_,_,_,_]],X) :- X \= v.
diagonal([[_,_,X,_,_],[_,X,_,_,_],[X,_,_,_,_], [_,_,_,_,_]],X) :- X \= v.
diagonal([[_,_,_,X,_],[_,_,X,_,_],[_,X,_,_,_], [_,_,_,_,_]],X) :- X \= v.
diagonal([[_,_,_,_,_],[_,_,X,_,_],[_,X,_,_,_], [X,_,_,_,_]],X) :- X \= v.
diagonal([[_,_,_,_,X],[_,_,_,X,_],[_,_,X,_,_], [_,_,_,_,_]],X) :- X \= v.
diagonal([[_,_,_,_,_],[_,_,_,X,_],[_,_,X,_,_], [_,X,_,_,_]],X) :- X \= v.
diagonal([[_,_,_,_,_],[_,_,_,_,X],[_,_,_,X,_], [_,_,X,_,_]],X) :- X \= v.

cheio([L1,L2,L3,L4,L5]) :- 
	append(L1,L2, L12),
	append(L12, L3, L123),
    append(L123, L4, L1234),
    append(L1234, L5, L12345),
	\+ member(v, L12345).

%função de utilidade, retorna o valor dos estados terminais, 1 ganha -1 perde
valor(G, 1) :- linhas(G,x).
valor(G, 1) :- colunas(G,x).
valor(G, 1) :- diagonal(G,x).
valor(G, -1) :- linhas(G,o).
valor(G, -1) :- colunas(G,o).
valor(G, -1) :- diagonal(G,o).
valor(_, 0).

% oper(estado,jogador,jogada,estado seguinte)
oper(E, J,joga(X,Y), En) :-
	joga_vazio(E,J,X, Y, En).

joga_vazio([L1,L2,L3,[v,C2,C3,C4,C5]], J, 4, 1, [L1,L2,L3,[J,C2,C3,C4,C5]]).
joga_vazio([L1,L2,L3,[C1,v,C3,C4,C5]], J, 4, 2, [L1,L2,L3,[C1,J,C3,C4,C5]]).
joga_vazio([L1,L2,L3,[C1,C2,v,C4,C5]], J, 4, 3, [L1,L2,L3,[C1,C2,J,C4,C5]]).
joga_vazio([L1,L2,L3,[C1,C2,C3,v,C5]], J, 4, 4, [L1,L2,L3,[C1,C2,C3,J,C5]]).
joga_vazio([L1,L2,L3,[C1,C2,C3,C4,v]], J, 4, 5, [L1,L2,L3,[C1,C2,C3,C4,J]]).
joga_vazio([L1,L2,[v,C2,C3,C4,C5],L4], J, 3, 1, [L1,L2,[J,C2,C3,C4,C5],L4]):- verificaVazio1(L4).
joga_vazio([L1,L2,[C1,v,C3,C4,C5],L4], J, 3, 2, [L1,L2,[C1,J,C3,C4,C5],L4]):- verificaVazio2(L4).
joga_vazio([L1,L2,[C1,C2,v,C4,C5],L4], J, 3, 3, [L1,L2,[C1,C2,J,C4,C5],L4]):- verificaVazio3(L4).
joga_vazio([L1,L2,[C1,C2,C3,v,C5],L4], J, 3, 4, [L1,L2,[C1,C2,C3,J,C5],L4]):- verificaVazio4(L4).
joga_vazio([L1,L2,[C1,C2,C3,C4,v],L4], J, 3, 5, [L1,L2,[C1,C2,C3,C4,J],L4]):- verificaVazio5(L4).
joga_vazio([L1,[v,C2,C3,C4,C5],L3,L4], J, 2, 1, [L1,[J,C2,C3,C4,C5],L3,L4]):- verificaVazio1(L3),verificaVazio1(L4).
joga_vazio([L1,[C1,v,C3,C4,C5],L3,L4], J, 2, 2, [L1,[C1,J,C3,C4,C5],L3,L4]):- verificaVazio2(L3),verificaVazio2(L4).
joga_vazio([L1,[C1,C2,v,C4,C5],L3,L4], J, 2, 3, [L1,[C1,C2,J,C4,C5],L3,L4]):- verificaVazio3(L3),verificaVazio3(L4).
joga_vazio([L1,[C1,C2,C3,v,C5],L3,L4], J, 2, 4, [L1,[C1,C2,C3,J,C5],L3,L4]):- verificaVazio4(L3),verificaVazio4(L4).
joga_vazio([L1,[C1,C2,C3,C4,v],L3,L4], J, 2, 5, [L1,[C1,C2,C3,C4,J],L3,L4]):- verificaVazio5(L3),verificaVazio5(L4).
joga_vazio([[v,C2,C3,C4,C5],L2,L3,L4], J, 1, 1, [[J,C2,C3,C4,C5],L2,L3,L4]):- verificaVazio1(L2),verificaVazio1(L3),verificaVazio1(L4).
joga_vazio([[C1,v,C3,C4,C5],L2,L3,L4], J, 1, 2, [[C1,J,C3,C4,C5],L2,L3,L4]):- verificaVazio2(L2),verificaVazio2(L3),verificaVazio2(L4).
joga_vazio([[C1,C2,v,C4,C5],L2,L3,L4], J, 1, 3, [[C1,C2,J,C4,C5],L2,L3,L4]):- verificaVazio3(L2),verificaVazio3(L3),verificaVazio3(L4).
joga_vazio([[C1,C2,C3,v,C5],L2,L3,L4], J, 1, 4, [[C1,C2,C3,J,C5],L2,L3,L4]):- verificaVazio4(L2),verificaVazio4(L3),verificaVazio4(L4).
joga_vazio([[C1,C2,C3,C4,v],L2,L3,L4], J, 1, 5, [[C1,C2,C3,C4,J],L2,L3,L4]):- verificaVazio5(L2),verificaVazio5(L3),verificaVazio5(L4).


verificaVazio1([C1,_,_,_,_]) :- C1 \= v.
verificaVazio2([_,C2,_,_,_]) :- C2 \= v.
verificaVazio3([_,_,C3,_,_]) :- C3 \= v.
verificaVazio4([_,_,_,C4,_]) :- C4 \= v.
verificaVazio5([_,_,_,_,C5]) :- C5 \= v.

joga(Ei) :-  
	minimax_decidir(Ei,Op),
    jogaOp(Ei, Op, Ef),
    printTable(Ef),nl,
    continua(Ef, A),
    A = true, 
    gameloop(Ef).
	%write(Op),nl.

gameloop(Ei) :- write('c: '), read(UC), alterStateUser(Ei, UC, Es), printTable(Es),nl, continua(Es, A), A \= false, joga(Es).

start :- estado_inicial(Ei), printTable(Ei), nl,gameloop(Ei).

alterStateUser(Ei, UC, Es) :- joga_vazio(Ei, 'o', _, UC, Es). 

printTable([A, B, C, D]) :- printRow(A),printRow(B), printRow(C),printRow(D).

printRow([]) :- nl.
printRow([A|B]) :- write(A),  write(' '), printRow(B).

jogaOp(Ei, joga(A,B), Ef) :- joga_vazio(Ei, 'x' ,A, B, Ef).


% decide qual é a melhor jogada num estado do jogo
% minimax_decidir(Estado, MelhorJogada)

% se é estado terminal não há jogada 
minimax_decidir(Ei,terminou) :- terminal(Ei).

% Para cada estado sucessor de Ei calcula o valor minimax do estado
% Opf é o operador (jogada) que tem maior valor
% Nota: assume que o jogador é o "x"
minimax_decidir(Ei,Opf) :- 
	findall(Vc-Op, (oper(Ei,x,Op,Es), minimax_valor(Es,Vc,1)), L),
	escolhe_max(L,Opf).

% se um estado é terminal o valor é dado pela função de utilidade
% Nota: assume que o jogador é o "x"
minimax_valor(Ei,Val,_) :- 
	terminal(Ei), 
	valor(Ei,Val).

%Se o estado não é terminal o valor é:
% -se a profundidade é par, o maior valor dos sucessores de Ei
% -se aprofundidade é impar o menor valor dos sucessores de Ei
minimax_valor(Ei,Val,P) :- 
	P1 is P+1, jogador(P1,J),
	findall(Val1, (oper(Ei,J,_,Es), minimax_valor(Es,Val1,P1)), V),
	seleciona_valor(V,P,Val).


% jogador "x" nas jogadas impares e jogador "o" nas jogadas pares
jogador(P, o) :- X is P mod 2, X = 0.
jogador(P, x) :- X is P mod 2, X = 1.

% Se a profundidade (P) é par, retorna em Val o maximo de V
seleciona_valor(V,P,Val) :- 
	X is P mod 2, X=0,!, 
	maximo(V,Val).

% Senão retorna em Val o minimo de V
seleciona_valor(V,_,Val):- minimo(V,Val).

%% Predicados auxiliares

escolhe_max([A|R],Val):- escolhe_max(R,A,Val).

escolhe_max([],_-Op,Op).
escolhe_max([A-_|R],X-Op,Val) :- A < X,!, escolhe_max(R,X-Op,Val).
escolhe_max([A|R],_,Val):- escolhe_max(R,A,Val).


maximo([A|R],Val):- maximo(R,A,Val).

maximo([],A,A).
maximo([A|R],X,Val):- A < X,!, maximo(R,X,Val).
maximo([A|R],_,Val):- maximo(R,A,Val).

minimo([A|R],Val):- minimo(R,A,Val).

minimo([],A,A).
minimo([A|R],X,Val):- A > X,!, minimo(R,X,Val).
minimo([A|R],_,Val):- minimo(R,A,Val).