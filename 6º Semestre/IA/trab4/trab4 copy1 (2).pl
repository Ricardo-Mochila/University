/* 
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


								Quando quiser fazer alguma coisa comigo diga sff..........


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

heur(TAB, DIM, E) :- countNumberOfQueens(TAB, DIM, X), E is DIM - X. */
%hill climb

estado_final(TAB, DIM) :- checkMatrix(TAB, DIM).


heur(TAB, DIM, E) :- countNumberOfQueens(TAB, DIM, X), E is DIM - X.

viewRow([A|_], 1, _) :- count(A, Num), Num = 0.
viewRow([_|As], ROW, VAL) :- viewRow(As, D, VAL), ROW is D + 1.

count([], 0).
count([A|B], C) :- count(B, D), C is D + A.

transpose([], []).
transpose([F|Fs], Ts) :- transpose(F, [F|Fs], Ts).

transpose([], _, []).
transpose([_|Rs], Ms, [Ts|Tss]) :- lists_firsts_rests(Ms, Ts, Ms1), transpose(Rs, Ms1, Tss).

lists_firsts_rests([], [], []).
lists_firsts_rests([[F|Os]|Rest], [F|Fs], [Os|Oss]) :- lists_firsts_rests(Rest, Fs, Oss).


valueOfPos(TAB, ROW, COL, E) :- nth1(ROW, TAB, ROWRes), nth1(COL, ROWRes, E).

lucheckDiagonalAllTable(TAB, 1, COL, _, VAL) :- valueOfPos(TAB, 1, COL, VAL).
lucheckDiagonalAllTable(TAB, ROW, 1, _, VAL) :- valueOfPos(TAB, ROW, 1, VAL).
lucheckDiagonalAllTable(TAB, ROW, COL, DIM, VAL) :- ROW > 1, COL > 1, ROW1 is ROW -1 ,COL1 is COL -1 ,lucheckDiagonalAllTable(TAB, ROW1, COL1, DIM, VAL1), 
                                                    valueOfPos(TAB, ROW, COL, E), VAL is E + VAL1, VAL =< 1 .

ldcheckDiagonalAllTable(TAB, DIM, COL, DIM, VAL) :- valueOfPos(TAB, DIM, COL, VAL).
ldcheckDiagonalAllTable(TAB, ROW, 1, _, VAL) :- valueOfPos(TAB, ROW, 1, VAL).
ldcheckDiagonalAllTable(TAB, ROW, COL, DIM, VAL) :- ROW < DIM, COL > 1, ROW1 is ROW +1 ,COL1 is COL -1 ,ldcheckDiagonalAllTable(TAB, ROW1, COL1, DIM, VAL1), 
                                                   valueOfPos(TAB, ROW, COL, E), VAL is E + VAL1, VAL =< 1 .

rucheckDiagonalAllTable(TAB, ROW, DIM, DIM, VAL) :- valueOfPos(TAB, ROW, DIM, VAL).
rucheckDiagonalAllTable(TAB, 1, COL, _, VAL) :- valueOfPos(TAB, 1, COL, VAL).
rucheckDiagonalAllTable(TAB, ROW, COL, DIM, VAL) :-ROW > 1, COL < DIM, ROW1 is ROW -1 ,COL1 is COL +1 ,rucheckDiagonalAllTable(TAB, ROW1, COL1, DIM, VAL1), 
                                                    valueOfPos(TAB, ROW, COL, E), VAL is E + VAL1, VAL =< 1 .

rdcheckDiagonalAllTable(TAB, DIM, COL, DIM, VAL) :- valueOfPos(TAB, DIM, COL, VAL).
rdcheckDiagonalAllTable(TAB, ROW, DIM, DIM, VAL) :- valueOfPos(TAB, ROW, DIM, VAL).
rdcheckDiagonalAllTable(TAB, ROW, COL, DIM, VAL) :- ROW < DIM, COL < DIM, ROW1 is ROW +1 ,COL1 is COL +1 ,rdcheckDiagonalAllTable(TAB, ROW1, COL1, DIM, VAL1), 
                                                    valueOfPos(TAB, ROW, COL, E), VAL is E + VAL1, VAL =< 1 .


checkAllDiagonals(TAB, ROW, COL, _, _) :- valueOfPos(TAB, ROW, COL, 0).
checkAllDiagonals(TAB, ROW, COL, DIM, VAL) :-lucheckDiagonalAllTable(TAB, ROW, COL, DIM, VAL1), ldcheckDiagonalAllTable(TAB, ROW, COL, DIM, VAL2), 
                                        rucheckDiagonalAllTable(TAB, ROW, COL, DIM, VAL3), rdcheckDiagonalAllTable(TAB, ROW, COL, DIM, VAL4), VAL is VAL1 + VAL2 + VAL3 + VAL4.

checkAllMatrixDiagonals(_, DIM, DIM, DIM).
checkAllMatrixDiagonals(TAB, ROW, COL, DIM) :- ROW < DIM, checkAllDiagonals(TAB, ROW, COL, DIM, 1), ROW1 is ROW + 1, checkAllMatrixDiagonals(TAB, ROW1, COL, DIM).
checkAllMatrixDiagonals(TAB, ROW, COL, DIM) :- COL < DIM, checkAllDiagonals(TAB, ROW, COL, DIM, 1), COL1 is COL + 1, checkAllMatrixDiagonals(TAB, ROW, COL1, DIM).

checkMatrix(TAB, DIM) :- viewRow(TAB, _, 1),  transpose(TAB, TrTAB), viewRow(TrTAB, _, 1), checkAllMatrixDiagonals(TAB, 1, 1, DIM), countNumberOfQueens(TAB, DIM, DIM).

countNumberOfQueens([], _, 0).
countNumberOfQueens([A|As], DIM, VAL) :- countNumberOfQueens(As, DIM, VAL1), count(A, VALUE1), VAL is VALUE1 + VAL1.




replace([_|T], 1, X, [X|T]).
replace([H|T], I, X, [H|R]):- I > -1, NI is I-1, replace(T, NI, X, R), !.
replace(L, _, _, L).

make_num_matrix(N, Matrix) :-make_matrix(N, N, Matrix).
make_matrix(_, N, []) :- N =< 0, !.
make_matrix(M, N, [R|Rs]) :- make_list(M, R), N2 is N - 1, make_matrix(M, N2, Rs).

make_list(N, []) :-N =< 0, !.
make_list(N, [0|Rest]) :-N > 0, N2 is N - 1, make_list(N2, Rest).


fill_matrix([], _, []).
fill_matrix([A|As], DIM, [L|Ls]) :- random(1, DIM, COL), replace(A, COL, 1, L), fill_matrix(As, DIM, Ls).

pesquisa_local_hill_climbingSemCiclos(E, _, DIM, _) :- estado_final(E, DIM), write(E), write(' ').

pesquisa_local_hill_climbingSemCiclos(E, L, DIM) :- 
	write(E), write(' '), 
	expande(E,LSeg, DIM),
	sort(3, @=<, LSeg, LOrd),
	obtem_no(LOrd, no(ES, _)),
	\+ member(ES, L), nl,
	(pesquisa_local_hill_climbingSemCiclos(ES,[E|L], DIM)).

expande(E, L, DIM) :- findall(no(En, Heur), (make_num_matrix(DIM, Matrix),fill_matrix(Matrix,DIM, E), heur(E, DIM,Heur)), L).


obtem_no([H|_], H).
obtem_no([_|T], H1) :- obtem_no(T, H1).

pesquisa :- write('Number of Queens: '), read(DIM), make_num_matrix(DIM, Matrix), fill_matrix(Matrix, DIM, Matrix1), checkMatrix(Matrix1, DIM).



