def minimo(a,b,c):
    if a > b and a > c and b > c:
        return c
    if a > b and a > c and b < c:
        return b
    elif a > b and a < c:
        return b
    elif a < b and b > c and a > c:
        return c
    elif a < b and b > c and a < c:
        return a
    elif a < b and b < c:
        return a
    elif a < b and a > c:
        return c
    else:
        return "introduce valid numbers"