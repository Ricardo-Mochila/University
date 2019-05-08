def mdc(m,n):
    m = int(m)
    n = int(n)
    if m > 0 and n > 0:

        if n==m:
            return m
        elif m > n:
            return mdc(m-n,n)
        elif m < n:
            return mdc(m,n-m)

    elif m <= 0:
        return "The number m needs to be bigger then 0"

    elif n <= 0:
        return "The number n needs to be bigger then 0"

def fracionarios(n1,d1,n2,d2):
    if d1 == d2:
        s = n1+n2
        r = s/d1
        return r
    else:
        a = n1 * d2
        b = d1 * d2
        c = n2 * d1
        d = a+c
        return d,b,simplifica(d,b)


def simplifica(n,d):
    s = mdc(n,d)
    n1 = int(n/s)
    d1 = int(d/s)
    return('n=',n1,", " ,"d=" , d1)

print(fracionarios(2,2,2,4))