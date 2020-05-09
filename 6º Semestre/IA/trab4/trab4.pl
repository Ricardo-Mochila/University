
estado_final(TAB, DIM) :- countNumberOfQueens(TAB, DIM, DIM).

make_num_matrix(N, Matrix) :-make_matrix(N, N, Matrix).

make_matrix(_, N, []) :- N =< 0, !.
make_matrix(M, N, [R|Rs]) :- make_list(M, R), N2 is N - 1, make_matrix(M, N2, Rs).

make_list(N, []) :-N =< 0, !.
make_list(N, [0|Rest]) :-N > 0, N2 is N - 1, make_list(N2, Rest).

count([], 0).
count([A|B], C) :- count(B, D), C is D + A.

viewRow([A|_], 1, VAL) :- count(A, Num), Num = VAL.
viewRow([A|_], 1, _) :- count(A, Num), Num = 0.
viewRow([_|As], ROW, VAL) :- viewRow(As, D, VAL), ROW is D + 1.


transpose([], []).
transpose([F|Fs], Ts) :- transpose(F, [F|Fs], Ts).

transpose([], _, []).
transpose([_|Rs], Ms, [Ts|Tss]) :- lists_firsts_rests(Ms, Ts, Ms1), transpose(Rs, Ms1, Tss).

lists_firsts_rests([], [], []).
lists_firsts_rests([[F|Os]|Rest], [F|Fs], [Os|Oss]) :- lists_firsts_rests(Rest, Fs, Oss).


valueOfPos(TAB, ROW, COL, E) :- nth1(ROW, TAB, ROWRes), nth1(COL, ROWRes, E).

lucheckDiagonal(_, 1, _, _).
lucheckDiagonal(_, _, 1, _).
lucheckDiagonal(TAB, ROW, COL, DIM) :- ROW > 1, COL > 1, ROW1 is ROW -1 ,COL1 is COL -1 ,
                                            valueOfPos(TAB, ROW1, COL1, E), E == 0, lucheckDiagonal(TAB, ROW1, COL1, DIM).

ldcheckDiagonal(_, DIM, _, DIM).
ldcheckDiagonal(_, _, 1, _).
ldcheckDiagonal(TAB, ROW, COL, DIM) :- ROW < DIM, COL > 1, ROW1 is ROW +1 ,COL1 is COL -1 ,
                                            valueOfPos(TAB, ROW1, COL1, E), E == 0, ldcheckDiagonal(TAB, ROW1, COL1, DIM).

rucheckDiagonal(_, _, DIM, DIM).
rucheckDiagonal(_, 1, _, _).
rucheckDiagonal(TAB, ROW, COL, DIM) :- ROW > 1, COL < DIM, ROW1 is ROW -1 ,COL1 is COL +1 ,
                                            valueOfPos(TAB, ROW1, COL1, E), E == 0, rucheckDiagonal(TAB, ROW1, COL1, DIM).

rdcheckDiagonal(_, DIM, _, DIM).
rdcheckDiagonal(_, _, DIM, DIM).
rdcheckDiagonal(TAB, ROW, COL, DIM) :- ROW < DIM, COL < DIM, ROW1 is ROW +1 ,COL1 is COL +1 ,
                                            valueOfPos(TAB, ROW1, COL1, E), E == 0, rdcheckDiagonal(TAB, ROW1, COL1, DIM).

checkDiagonals(TAB, ROW, COL, DIM) :-lucheckDiagonal(TAB, ROW, COL, DIM), ldcheckDiagonal(TAB, ROW, COL, DIM), rucheckDiagonal(TAB, ROW, COL, DIM), rdcheckDiagonal(TAB, ROW, COL, DIM).


replace( L , X , Y , Z , R ) :- append(RowPfx,[Row|RowSfx],L), length(RowPfx,X), append(ColPfx,[_|ColSfx],Row),   
                                length(ColPfx,Y), append(ColPfx,[Z|ColSfx],RowNew), append(RowPfx,[RowNew|RowSfx],R).


op(TAB, pos(ROW,COL), _, 1, DIM) :-  viewRow(TAB, ROW, 0),  transpose(TAB, TrTAB), viewRow(TrTAB, COL, 0), checkDiagonals(TAB, ROW, COL, DIM) .

alterMatrix(TAB, pos(ROW,COL), RETAB, 1, DIM) :- op(TAB, pos(ROW,COL), _, 1, DIM), ROW1 is ROW-1 ,COL1 is COL-1 ,replace(TAB,  ROW1, COL1, 1, RETAB).

countNumberOfQueens([], _, 0).
countNumberOfQueens([A|As], DIM, VAL) :- countNumberOfQueens(As, DIM, VAL1), count(A, VALUE1), VAL is VALUE1 + VAL1.

heur(TAB, DIM, E) :- estado_final(TAB, DIM), E is 0.
heur(TAB, DIM, E) :- countNumberOfQueens(TAB, DIM, X), E is DIM - X.
%hill climb

pesquisa_local_hill_climbingSemCiclos(E, _, DIM, _) :- estado_final(E, DIM), write(E), write(' ').

pesquisa_local_hill_climbingSemCiclos(E, L, DIM, VALUE) :- 
	write(E), write(' '), write(VALUE),
    VALUE > 0, VALUE1 is VALUE -1,
	expande(E,LSeg, DIM),
	sort(3, @=<, LSeg, LOrd),
	obtem_no(LOrd, no(ES, Op, _)),
	\+ member(ES, L),
	write(Op), nl,
	(pesquisa_local_hill_climbingSemCiclos(ES,[E|L], DIM, VALUE1)).

expande(E, L, DIM) :- findall(no(En,Opn, Heur), (alterMatrix(E,Opn,En,_,DIM), heur(En, DIM,Heur)), L).


obtem_no([H|_], H).
obtem_no([_|T], H1) :- obtem_no(T, H1).

pesquisa :- write('Number of Queens: '), read(DIM), make_num_matrix(DIM, Matrix), pesquisa_local_hill_climbingSemCiclos(Matrix, [], DIM, DIM).



