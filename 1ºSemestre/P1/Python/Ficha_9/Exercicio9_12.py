def conta_palavras(string):
    a = 0
    for i in string:
        if i == " " or i == ",":
            a= a +1


    return a

print(conta_palavras(" "))