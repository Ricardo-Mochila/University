import random

def listaAll(lista1,numero,listaTG,listaTT,listaTP,contar):
    lista2 = []
    string = ""
    listaTouros = []
    listaPorcos = []
    alga = ["0","1","2","3","4","5","6","7","8","9"]
    for i in alga:
        if i not in lista1:
            lista2 += [i]

    for j in range (len(lista2)):
        lista1[0] = lista2[j]

        for h in lista1:
            string += h

        print ("O seu numero é:", numero)
        print ("O Computador gerou:",string)

        contar += 1
        listaTG += [string]
        string = ""
        touros = int (input ("T: "))
        porcos = int (input ("P: "))
        listaTT += [touros]
        listaTP += [porcos]

        if len (listaTouros) <= 1 and len (listaPorcos) <= 3:

            if touros == 1 and porcos == 0:
                listaTouros += lista2[j]

            elif touros == 0 and porcos == 1:
                listaPorcos += lista2[j]


        if len (listaTouros) == 1 and len (listaPorcos) == 3:
            i = listaPorcos[0]
            j = listaPorcos[1]
            k = listaPorcos[2]
            listaTotal = [[i, j, k], [i, k, j], [j, k, i], [j, i, k], [k, i, j], [k, j, i]]
            for i in range (6):
                tentativa = listaTotal[i]
                listaFinal=listaTouros+tentativa
                stringFinal = ""

                for a in listaFinal:
                    stringFinal += a

                print ("O seu numero é:", numero)
                print("O Computador gerou",stringFinal)
                listaTG += [stringFinal]
                stringFinal = ""
                touros = int (input ('T:'))
                listaTT += [touros]

                if touros < 4:
                    contar += 1
                    porcos = int (input ('P:'))
                    listaTP += [porcos]

                elif touros == 4:
                    listaTP += [0]
                    contar += 1
                    tentativasAutomaticas (listaTG, listaTT, listaTP, contar, touros)
                    return("")


def automatico():
    listaTP = []
    listaTT = []
    listaTG = []
    contar = 0
    numero = int (input ("Introduza o valor: "))
    print ("O seu numero é:", numero)
    geradoPC = randomizer ()
    lista1 = []
    while True:
        print ("O computador gerou:", geradoPC)
        touros = int (input ("T: "))
        listaTT += [touros]
        listaTG += [geradoPC]
        while touros < 4:
            porcos = int (input ("P: "))
            listaTP += [porcos]
            contar += 1
            if  touros + porcos > 0:

                geradoPC = randomizer()
                print ("O seu numero é:", numero)
                print ("O computador gerou:", geradoPC)
                touros = int (input ("T: "))
                listaTT += [touros]
                listaTG += [geradoPC]



            elif touros + porcos == 0:
                lista1 += list(geradoPC)

                listaAll(lista1,numero,listaTG,listaTT,listaTP,contar)
                print("Ganhou o jogo!!! Parabens!!!")
                tentativasAutomaticas(listaTG,listaTT,listaTP,contar,touros)
                return("")


            else:
                print ("Os numeros que introduzio estão errados")

        if touros == 4:
            return "O codigo está correto"

def tentativasAutomaticas(listaTG,listaTT,listaTP,contar,touros):
    tentativa = 1
    iterador = 0
    if touros == 4:
        print ("\nAs suas tentativas foram:")
        while tentativa <= contar:
            print("Tentativa:" + str (tentativa), listaTG[iterador], str (listaTT[iterador]) + "T", str (listaTP[iterador]) + "P")
            tentativa = tentativa + 1
            iterador = iterador + 1



def randomizer():
    lista = []
    numero = ""
    c = 0
    while c < 4:
        nr = int(random.random()*10)
        if nr not in lista:
            lista += [nr]
            c += 1
    for i in range (len (lista)):
        r = str (lista[i])
        numero += r
    return numero


def resultado(touros, porcos):
    if touros > 0:
        if porcos > 0:
            return(str(touros)+"T" + " " + str(porcos)+"P")
        else:
            return(str(touros)+"T")

    elif porcos > 0:
        return(str(porcos)+"P")

    else:
        return "Tente outra vez"





def tentativas(contador,listaInput,listaTouros,listaPorcos):

    tentativa= 1
    iterador= 0
    print("\nAs suas tentativas foram:")
    while tentativa <= contador:
        print("Tentativa:"+str(tentativa),listaInput[iterador],str(listaTouros[iterador])+"T",str(listaPorcos[iterador])+"P")
        tentativa = tentativa+1
        iterador = iterador+1




def conta_algarismos():
    touros = 0
    contador = 0
    listaTouros = []
    listaPorcos = []
    listaInput = []
    string = randomizer ()
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
        listaInput = listaInput + [user]

        print(resultado(touros,porcos))

    if touros == 4:
        print("Parabens, ganhou o jogo")

    tentativas (contador, listaInput, listaTouros, listaPorcos)



def menu():
    gerado = "1- Player VS Computador"
    gerar = "2- Computador VS Player"
    sair = "3- Terminar"
    print(gerado)
    print(gerar)
    print(sair)
    introduzir = int (input ("\nEscolha a sua opção: "))

    while introduzir != 3:
        if introduzir == 1:
            conta_algarismos()

        elif  introduzir == 2:
            print(automatico())

        else:
            print("Introduza uma opçao valida")

        introduzir = int (input ("\nEscolha a sua opção "))

    if introduzir == 3:
        return "\nObrigado por jogar"

print(menu())