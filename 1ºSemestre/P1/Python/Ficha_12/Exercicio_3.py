def meio_segmento(p1,p2):
    media1 = (p1[0]+p2[0])/2
    media2 = (p1[1]+p2[1])/2
    tuplo = (media1,media2)
    return tuplo
print(meio_segmento((3,2),(2,5)))