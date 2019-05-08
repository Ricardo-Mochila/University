def verifica_ordem(lista):
    a = 0
    for i in range(len(lista)-1):
        a = a+1
        if lista[i] <= lista[a]:
            b = True
        else:
            b = False
    return b
print(verifica_ordem([1,2,2,3,4,]))