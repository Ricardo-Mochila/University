def soma_colunas(l1):

    a = l1[0][0] + l1[1][0] + l1[2][0]
    b = l1[0][1] + l1[1][1] + l1[2][1]
    c = l1[0][2] + l1[1][2] + l1[2][2]
    lista =[a,b,c]
    return lista
print(soma_colunas([[1,2,3],[3,4,5],[6,7,8]]))