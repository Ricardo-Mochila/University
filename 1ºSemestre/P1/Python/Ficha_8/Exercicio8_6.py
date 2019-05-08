def fibonacci_ate(v):
    s = ""
    p = 1
    a = 0
    for i in range (v):
        if a < v:
            g = a
            a = p
            if a > v:
                return s
            p = a + g
            s = s + " " + str (a)
    return s


print(fibonacci_ate(33))