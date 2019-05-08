def ocorrencias_case(letra, string, indice):
    b = 0
    c = 0




    for i in string:
        if c >= indice and i == letra:
            b = b + 1
        else:
            c= c+1
    return b

print(ocorrencias_case("A","AULA",0))