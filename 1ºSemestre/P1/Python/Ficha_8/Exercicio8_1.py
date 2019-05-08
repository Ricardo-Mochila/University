def expoente(num):
    i = num
    for i in range(num+1):
        d = num**i
        i = i-1
        print(num, "*", i+1, "=", d)



def expoente_1(num):
    i = num
    while i >= 0:
        d = num**i
        i = i-1
        print (num, "*", i + 1, "=", d)
expoente_1(2)