import math

def raizcompleta(a,b,c):
    a = int(a)
    b = int(b)
    c = int(c)

    def raiz1(a, b, c):
        d = b**2 - 4*(a*c)
        e = (-b + math.sqrt(d))/2*a
        return (e)

    def raiz2(a, b, c):
        d = b**2 - 4*(a*c)
        e = (-b - math.sqrt(d))/2*a
        return(e)

    return ( raiz1(a,b,c),"or" ,raiz2(a,b,c))

print(raizcompleta(1,-12,-28))