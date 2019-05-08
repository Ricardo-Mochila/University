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




def simplifica(n,d):
    s = mdc(n,d)
    n1 = int(n/s)
    d1 = int(d/s)
    print('n=',n1,", " ,"d=" , d1)

simplifica(1536,78360)