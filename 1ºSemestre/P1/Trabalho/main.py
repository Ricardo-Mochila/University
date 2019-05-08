
#####Trabalho de Programação 1, realizado por Ricardo Mochila l37762#####
#########################################################################
#                                                                       #
# randomizer ---> esta função define um valor de quatro algarismos      #
# todos diferentes entre si                                             #
#                                                                       #
#########################################################################

import random

def randomizer():
    al1 = str(int(random.random()*10))
    al2 = str(int(random.random()*10))
    al3 = str(int(random.random()*10))
    al4 = str(int(random.random()*10))

    if al2 == al1:
        while al2 == al1:
            al2 = str(int(random.random()*10))

    if al3 == al1 or al3 == al2 :
        while al3 == al1 or al3 == al2 :
            al3 = str(int(random.random()*10))

    if al4 == al1 or al4 == al2 or al4 == al3:
        while al4 == al1 or al4 == al2 or al4 == al3:
            al4 = str(int(random.random()*10))

    string = al1+al2+al3+al4
    return(string)
#########################################################################
#                                                                       #
# conta_algarismos ----> esta função usa o valor aleatorio e compara-o  #
# com o valor introduzido pelo utilizador                               #
#                                                                       #
#########################################################################

def conta_algarsimos():
    touros = 0
    contador = 0
    listaTouros = []
    listaPorcos = []
    listaContador = []
    string = randomizer ()
    print(string)
    while touros < 4:
        user = str(input("\nIntroduza o seu palpite: "))
        touros = 0
        porcos = 0
        contador = contador + 1
        if len (user) == 4:
            for i in string:
                for j in user:
                    if i == j:

                        if string.index(i) == user.index(j):
                            touros = touros +1

                        else:
                            porcos = porcos +1
        else:
            print("")


        listaTouros = listaTouros + [touros]
        listaPorcos = listaPorcos + [porcos]
        listaContador = listaContador + [user]

        def resultado():
            if touros > 0:
                if porcos > 0:
                    return(str(touros)+"T" + " " + str(porcos)+"P")
                else:
                    return(str(touros)+"T")

            elif porcos > 0:
                return(str(porcos)+"P")

            else:
                return "Tente outra vez"

        print(resultado ())

        def tentativas():

            tentativa= 1
            iterador= 0
            print("\nAs suas tentativas foram:")
            while tentativa <= contador:
                print("T"+str(tentativa),listaContador[iterador],str(listaTouros[iterador])+"T",str(listaPorcos[iterador])+"P")
                tentativa = tentativa+1
                iterador = iterador+1




    if touros == 4:
        print("Parabens, ganhou o jogo")


    tentativas()

conta_algarsimos()