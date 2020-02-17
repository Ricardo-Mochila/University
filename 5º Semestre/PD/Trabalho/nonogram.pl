
matrix([]) :- print('Empty').
matrix([ROW,COL]) :- count(ROW, NROW),
                count(COL, NCOL),
                makeRow(ROW, NCOL, NCOL,MatrixRow),
                makeRow(COL, NROW, NROW, MatrixCol),
                sumRowCol(ROW, SumRow),
                sumRowCol(COL, SumCol),
                applyContrain(SumCol, MatrixCol),
                transpose(MatrixCol, MatrixTCol),
                applyContrain(SumRow, MatrixTCol),
                separate(MatrixTCol, ROW),
                transpose(MatrixTCol, MatrixTTCol),
                separate(MatrixTTCol, COL),
                transpose(MatrixTTCol, MatrixTTTCol),
                rewrite(MatrixTTTCol, MatrixFinal),
                printMatrix(MatrixFinal),
                statistics(user_time, [SinceStart, SinceLast]),
                Time is SinceLast / 1000,
                write('Time: '),write(Time),write('ms').


auxRow(Pos, NCOL,NCOLMAX,[A|B]) :- Pos = NCOLMAX, NCOL > 0, auxRow(Pos, NCOL-1, NCOLMAX,B).
auxRow(Pos, NCOL,NCOLMAX,[A|B]) :- Pos < NCOLMAX, NCOL > 0, auxRow(Pos, NCOL-1, NCOLMAX,B).
auxRow(_, _,_,[]) :- !.

sum([],0).
sum([A|B], V) :- sum(B, C), V is A+C.

sumRowCol([], []).
sumRowCol([Pos1|Pos2], [NEWROW|NEWROWS]) :- count(Pos1, A), A > 0,
                                        sum(Pos1, NEWROW),
                                        sumRowCol(Pos2, NEWROWS).

countOnes([], 0, []).
countOnes([], N, [N]).
countOnes([A], N, [X]) :- A = 1, X is N+1.
countOnes([A], N, []) :- A = 0, N is 0.
countOnes([A, C|B], N,X) :- A = 1, C = 1, Z is N + 2,countOnes(B, Z, X).
countOnes([A, C|B], N,[X|Y]) :- A = 1, C = 0, X is N+1, countOnes(B, 0, Y).
countOnes([A, C|B], N,[N|X]) :- A = 0, N > 0,countOnes([C|B], 0, X).
countOnes([A, C|B], N,X) :- A = 0, countOnes([C|B], 0, X).


separate([], []).
separate([A|B], [C|D]) :- countOnes(A, 0, X), X = C, separate(B,D).

applyContrain([], _).
applyContrain(_, []).
applyContrain([A|B], [E|F]) :- fd_exactly(A, E, 1),applyContrain(B, F).

makeRow([], _, _, _).
makeRow([[Pos1|PosR]|Pos2], NCOL, NCOLMAX,[MatrixRow|Rest]) :- count(PosR, A), A = 0, 
                                                        auxRow(Pos1, NCOL, NCOLMAX, MatrixRow),
                                                        fd_domain(MatrixRow, 0, 1),   
                                                        makeRow(Pos2, NCOL, NCOLMAX,Rest).

makeRow([[_|PosR]|Pos2], NCOL, NCOLMAX,MatrixRow) :- count(PosR, A), A > 0,
                                                    makeRow([PosR|Pos2], NCOL, NCOLMAX,MatrixRow).

count([], 0).
count([_|B], C) :- count(B, Z), C is Z + 1.

transpose([], []).
transpose([F|Fs], Ts) :-
    transpose(F, [F|Fs], Ts).

transpose([], _, []).
transpose([_|Rs], Ms, [Ts|Tss]) :-
        lists_firsts_rests(Ms, Ts, Ms1),
        transpose(Rs, Ms1, Tss).

lists_firsts_rests([], [], []).
lists_firsts_rests([[F|Os]|Rest], [F|Fs], [Os|Oss]) :-
        lists_firsts_rests(Rest, Fs, Oss).

rewrite([], []).
rewrite([A|B], [M|Z]) :- run(A, M), rewrite(B, Z).

run([], []).
run([A|B], [' X '|Y]) :- A = 1, run(B, Y).
run([A|B], [' . '|Y]) :- A = 0, run(B, Y).

printMatrix([]).
printMatrix([A|B]) :- printRow(A), print('\n'),printMatrix(B).

printRow([]).
printRow([A|B]) :- write(A), printRow(B).

