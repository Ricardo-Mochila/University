def revFactorial(n):
    c = 1
    if n != 1 and n %2 != 0:
        print("0")
    else:
        while n > 1:
            revFactorial(n/c)
            c += 1

        if n == 1:
            print(n)
        else:
            print("0")

revFactorial(120)