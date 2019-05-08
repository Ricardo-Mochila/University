def revFactorial(n):
    c = 0
    if n != 1 and n %2 != 0:
        return "0"
    else:
        while n > 1:
            c = c + 1
            n = n / c
        if n == 1:
            return "O Factorial é: ", c
        else:
            return "0"

print(revFactorial(120))