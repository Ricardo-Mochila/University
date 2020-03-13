%Descricao do problema:

%estado_inicial(Estado)
jarro(l,c).
estado_inicial([jarro(0,3), jarro(0,2)]).

%estado_final(Estado)
estado_final([_, jarro(1, 2)]).

%representacao dos operadores

opr([jarro(_, A), C], enche(1), [jarro(A, A), C],1).
opr([C, jarro(_, A)], enche(2), [C, jarro(A, A)],1).
opr([jarro(C, A), _], esvazia(1), [jarro(0, A), C],1).
opr([C, jarro(_, A)], esvazia(2), [C, jarro(0, A)],1).
opr([jarro(A, B), jarro(C, D)], passa(1), [jarro(0, B), jarro(E, D)],1) :- E is A+C, E =< D.
opr([jarro(A, B), jarro(C, D)], passa(2), [jarro(E, B), jarro(0, D)],1) :- E is A+C, E =< B.
opr([jarro(A, B), jarro(C, D)], passa(1), [jarro(F, B), jarro(D, D)],1) :- E is A+C, E > D, F is A-(D-C).
opr([jarro(A, B), jarro(C, D)], passa(2), [jarro(B, B), jarro(F, D)],1) :- E is A+C, E > B, F is C-(A-B).



%representacao dos nos
%no(Estado,no_pai,Operador,Custo,Profundidade)

pesquisa_largura([no(E,Pai,Op,C,P)|_],no(E,Pai,Op,C,P)) :- 
	estado_final(E).
pesquisa_largura([E|R],Sol):- 
	expande(E,Lseg),
        insere_fim(Lseg,R,LFinal),
        pesquisa_largura(LFinal,Sol).

expande(no(E,Pai,Op,C,P),L):- 
	findall(no(En,no(E,Pai,Op,C,P), Opn, Cnn, P1),
                (opr(E,Opn,En,Cn), P1 is P+1, Cnn is Cn+C),
                L).

pesquisa :-
	estado_inicial(S0),
	pesquisa_largura([no(S0,[],[],0,0)], S),
	write(S), nl.



insere_fim([],L,L).
insere_fim(L,[],L).
insere_fim(R,[A|S],[A|L]):- insere_fim(R,S,L).