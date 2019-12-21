matrix([],[]) :- print('\n').
matrix([A|C], []) :- matrix(C, []).
matrix([], [A|C]) :- matrix([],C) .
matrix([A|B], [C|D]) :- matrix(B, []), matrix([], D), print(A), print(C).