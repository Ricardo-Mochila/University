fazIstoPa: actividade4C.out
			./actividade4C.out

lexer.c : actividade4.l
	flex -o lexer.c actividade4.l

parser.c: actividade4Bison.y
	bison -y -d -o parser.c actividade4Bison.y

actividade4C.out: lexer.c parser.c
	gcc parser.c lexer.c -o actividade4C.out

