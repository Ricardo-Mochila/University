
 nonogram(RowNums,ColNums,Solution) :-
   length(RowNums,NRows),
   length(ColNums,NCols),
   make_rectangle(NRows,NCols,Rows,Cols),
   append(Rows,Cols,Lines),
   append(RowNums,ColNums,LineNums),
   maplist(make_runs,LineNums,LineRuns),
   combine(Lines,LineRuns,LineTasks),
   solve(LineTasks),
   Solution = Rows.
 
combine([],[],[]).
combine([L1|Ls],[N1|Ns],[task(L1,N1)|Ts]) :- combine(Ls,Ns,Ts).

solve([]).
solve([task(Line,LineRuns)|Tasks]) :- 
   place_runs(LineRuns,Line),
   solve(Tasks).


% (1) The first basic idea is implemented as follows. ----------------------

% make_rectangle(NRows,NCols,Rows,Cols) :- a rectangular array of variables
%    with NRows rows and NCols columns is generated. The variables can
%    be accessed via the Rows or via the Cols list. I.e the variable in 
%    row 1 and column 2 can be addressed in the Rows list as [[_,X|_]|_]
%    or in the Cols list as [_,[X|_]|_]. Cool!
%    (integer,integer,list-of-char-list,list-of-char-list)    (+,+,_,_)

make_rectangle(NRows,NCols,Rows,Cols) :-
   NRows > 0, NCols > 0,
   length(Rows,NRows),
   maplist(inv_length(NCols),Rows),
   length(Cols,NCols),
   maplist(inv_length(NRows),Cols),
   unify_rectangle(Rows,Cols).

inv_length(Len,List) :- length(List,Len).

% unify_rectangle([[]|_],[]).
unify_rectangle(_,[]).
unify_rectangle([],_).
unify_rectangle([[X|Row1]|Rows],[[X|Col1]|Cols]) :-
   unify_row(Row1,Cols,ColsR),
   unify_rectangle(Rows,[Col1|ColsR]). 

unify_row([],[],[]).
unify_row([X|Row],[[X|Col1]|Cols],[Col1|ColsR]) :- unify_row(Row,Cols,ColsR).


% (2) The second basic idea is implemented as follows -----------------------

% make_runs(RunLens,Runs) :- Runs is a list of character-lists that
%    correspond to the given run lengths RunLens. Actually, each run
%    is a difference list; e.g ['x','x'|T]-T.
%    (integer-list,list-of-runs) (+,-)

make_runs([],[]) :- !.
make_runs([Len1|Lens],[Run1-T|Runs]) :- 
   put_x(Len1,Run1,T),
   make_runs2(Lens,Runs).

% make_runs2(RunLens,Runs) :- same as make_runs, except that the runs
%    start with a space character.
make_runs2([],[]).
make_runs2([Len1|Lens],[[' '|Run1]-T|Runs]) :- 
   put_x(Len1,Run1,T),
   make_runs2(Lens,Runs).

put_x(0,T,T) :- !.
put_x(N,['x'|Xs],T) :- N > 0, N1 is N-1, put_x(N1,Xs,T).


% place_runs(Runs,Line) :- Runs is a list of runs, each of them being
%    a difference list of characters. Line is a list of characters.
%    The runs are placed into the Line, optionally separated by
%    additional space characters. Via backtracking, all possibilities
%    are generated.
%   (run-list,square-list)  (+,?)

place_runs([],[]).
place_runs([Line-Rest|Runs],Line) :- place_runs(Runs,Rest).
place_runs(Runs,[' '|Rest]) :- place_runs(Runs,Rest).
   
label([],[]).
label([task(Line,LineRuns)|Tasks],[task(Count,Line,LineRuns)|LTasks]) :- 
   length(Line,N),   
   findall(L,(length(L,N), place_runs(LineRuns,L)),Ls),
   length(Ls,Count),
   label(Tasks,LTasks).

unlabel([],[]).
unlabel([task(_,Line,LineRuns)|LTasks],[task(Line,LineRuns)|Tasks]) :- 
   unlabel(LTasks,Tasks).

% Printing the solution ----------------------------------------------------

% print_nonogram(RowNums,ColNums,Solution) :-

print_nonogram([],ColNums,[]) :- print_colnums(ColNums).
print_nonogram([RowNums1|RowNums],ColNums,[Row1|Rows]) :-
   print_row(Row1),
   print_rownums(RowNums1),
   print_nonogram(RowNums,ColNums,Rows).

print_row([]) :- write('  ').
print_row([X|Xs]) :- print_replace(X,Y), write(' '), write(Y), print_row(Xs).
   
print_replace(' ','.') :- !.
print_replace(x,'X').

print_rownums([]) :- nl.
print_rownums([N|Ns]) :- write(N), write(' '), print_rownums(Ns).

print_colnums(ColNums) :-
   maxlength(ColNums,M,0),
	print_colnums(ColNums,ColNums,1,M).

maxlength([],M,M).
maxlength([L|Ls],M,A) :- length(L,N), B is max(A,N), maxlength(Ls,M,B). 

print_colnums(_,[],M,M) :- !, nl.
print_colnums(ColNums,[],K,M) :- K < M, !, nl,
   K1 is K+1, print_colnums(ColNums,ColNums,K1,M).
print_colnums(ColNums,[Col1|Cols],K,M) :- K =< M, 
   write_kth(K,Col1), print_colnums(ColNums,Cols,K,M).
   
write_kth(K,List) :- nth1(K,List,X), !, writef('%2r',[X]).
write_kth(_,_) :- write('  ').

% -----------------------------------------------------------------------

