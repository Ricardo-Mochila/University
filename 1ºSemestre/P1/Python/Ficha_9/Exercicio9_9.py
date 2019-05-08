def ocorrencias(a,string):
    b = 0
    for i in string:
        if i == a :
            b = b+1
    return b
print(ocorrencias(" ","      "))