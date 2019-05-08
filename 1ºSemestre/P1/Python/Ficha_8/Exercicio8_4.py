def factorial(num):
    p = 1
    for i in range(num):
        p = p * (i+1)
    return p
print(factorial(6))