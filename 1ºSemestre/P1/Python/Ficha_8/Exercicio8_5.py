def fibonacci(num):
    s = ""
    p = 1
    a = 0
    for i in range (num):
        g = a
        a = p
        p = a + g
        s = s + " " + str(a)

    return s

print(fibonacci(8))