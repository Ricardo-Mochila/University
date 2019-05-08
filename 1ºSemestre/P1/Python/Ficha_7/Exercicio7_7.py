def pinheiro(h):
    x = 0
    while x<h:
        x = x+1
        space = h - x
        space_c = 0
        while space_c < space:
            print (" ", end='')
            space_c = space_c + 1

        n = 2*x-1
        while n > 0:
            print("*", end='')
            n = n-1
        print("\n")
    print(" "*(h-2),"*")
    print (" " * (h - 2), "*")
pinheiro(4)
