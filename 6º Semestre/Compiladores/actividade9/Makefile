CC = clang
CFLAGS = -g
OUTFILE = yaout
LIBS = -lm
OFILES = t_exp.o

all: lexer.c parser.c parser.h ya.o
	$(CC) $(CFLAGS) parser.c lexer.c ya.o -o $(OUTFILE) $(LIBS)

parser.c: yalang.y
	bison -y -d $< -o $@ -r all
	
lexer.c: yalang.lex
	flex -o $@ $<

ya.o: ya.h ya.c
	gcc $(CFLAGS) -c ya.c

%.o: %.c %.h
	$(CC) -c $(CFLAGS) -o $@ $<

clean: 
	rm -rf parser.c lexer.c parser.h
	rm -f *~
	rm -f *.o
	rm -f *.output

clean-all clean_all: clean
	rm -f $(OUTFILE)
