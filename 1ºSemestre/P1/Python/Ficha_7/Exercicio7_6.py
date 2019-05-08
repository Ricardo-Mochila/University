def triangulo(h):
    x = 1
    while ( x <= h):
        z = 1
        while(z <= x):
            print(z," ",end='' )
            z = z+1
        print(" \n")
        x = x+1
triangulo(16)