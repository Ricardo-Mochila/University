def vogal(string):
    string = string
    if string in "aeiou" and string != "" :
        return True
    else:
        return False

def conta_vogais(string):
    a = 0
    for i in string:
        if vogal(i) == True:
            a = a+1
    return a
print(conta_vogais("adeus"))