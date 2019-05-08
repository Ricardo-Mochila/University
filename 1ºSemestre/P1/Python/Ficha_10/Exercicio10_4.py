def conta_elementos(l1, l2):
    b = 0

    for i in range(len(l1)):
        for a in range(len(l2)):
            if l1[i] == l2[a]:
                b = b+1
    return b
print(conta_elementos([1,2],[1,2,2]))