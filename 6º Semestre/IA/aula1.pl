%1
homem('Afonso Henriques','rei de Portugal',1109).
homem('Henrique de Borgonha','conde de Portugal',1069).

homem('Sancho I','rei de Portugal',1154).
homem('Fernando II','rei de Leão',1137).
homem('Afonso IX', 'rei de Leão e Castela', 1171).
homem('Afonso II', 'rei de Portugal',1185).

homem('Sancho II', 'rei de Portugal',1207).
homem('Afonso III', 'rei de Portugal',1210).


mulher('Teresa de Castela', 'condessa de Portugal', 1080).
mulher('Mafalda', 'condessa de Saboia', 1125).
mulher('Urraca', 'infanta de Portugal',1151).
mulher('Dulce de Barcelona','infanta de Aragão',1160).
mulher('Berengária', 'infanta de Portugal',1194).
mulher('Urraca C','infanta de Castela',1186).


filho('Afonso Henriques','Henrique de Borgonha').
filho('Afonso Henriques','Teresa de Castela').
filho('Urraca','Afonso Henriques').
filho('Sancho I','Afonso Henriques').
filho('Urraca','Mafalda').
filho('Sancho I','Mafalda').
filho('Afonso IX','Urraca').
filho('Afonso IX','Fernando II').
filho('Afonso II','Sancho I').
filho('Afonso II','Dulce de Barcelona').
filho('Berengária','Sancho I').
filho('Berengária','Dulce de Barcelona').
filho('Sancho II','Afonso II').
filho('Afonso III','Afonso II').
filho('Sancho II','Urraca C').
filho('Afonso III','Urraca C').

%2
irmao(Nome1, Nome2) :- filho(Nome1, Pai), filho(Nome2,Pai), Nome1 \= Nome2. 

%3
primoDireito(Nome1,Nome2) :- filho(Nome1,Pai1), filho(Nome2,Pai2), irmao(Pai1, Pai2).

%4
irmaos(Nome1,B) :- setof(Nome2, irmao(Nome1,Nome2), B).

%5
primo(Nome1,Nome2) :- primoDireito(Nome1, Nome2).
primo(Nome1,Nome2) :- filho(Nome2, Pai), primoDireito(Nome1, Pai).
primo(Nome1,Nome2) :- filho(Nome1, Pai1), filho(Nome2,Pai2), primo(Pai1, Pai2).

primos(Nome1,B) :- setof(Nome2, primo(Nome1, Nome2), B).

%6
esposa(Mulher, Homem) :- mulher(Mulher, _, _), homem(Homem, _, _), filho(X, Mulher), filho(X, Homem).

%7
ascendente(Nome1,Nome2) :- filho(Nome1,Nome2).
ascendente(Nome1,Nome2) :- filho(Nome1,Pai), ascendente(Pai, Nome2).
descendente(Nome1,B) :- setof(Nome2, ascendente(Nome1, Nome2), B).

%8
desc(Nome1,Nome2) :- filho(Nome2,Nome1).
desc(Nome1,Nome2) :- filho(Filho,Nome1), desc(Filho, Nome2).
descendentes(Nome1,B) :- setof(Nome2, desc(Nome1, Nome2), B).

%9
ascendente(Nome1, X) :- X = c(Nome1, )