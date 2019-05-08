def escala_in(string):
    a = 1
    print(string)
    for i in range(len(string)-1):
        str1= string[:-a]
        a = a+1
        print (str1)

print(escala_in("Aula"))