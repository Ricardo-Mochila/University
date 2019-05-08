def multiplo(n,i):
    if i == 1:
        return n
    elif i > 1:
        return n + multiplo(n,i-1)

print(multiplo(4,3))