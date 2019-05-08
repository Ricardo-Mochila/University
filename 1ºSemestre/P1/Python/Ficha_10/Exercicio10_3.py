def corta(lista):
    lista.pop(0)
    lista.pop(-1)
    return  lista
print(corta([1,2,3,4,5]))

l1 = [1,2,3,4,5]
l2 = corta(l1)
print(l2)