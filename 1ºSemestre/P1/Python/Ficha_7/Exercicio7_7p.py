def pinheiro(h):
    i=1
    si='*'
    while i<=h:
        s = ' ' * int(h - i)
        s= s + str(si)
        print(s)
        si = ((2 * i)+1 )* "*"
        i = i + 1
    print ((h-2)*' ',str('*'))
    print ((h-2)*' ',str('*'))
pinheiro(4)