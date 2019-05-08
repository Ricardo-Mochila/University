def s(str):
    nstr = ""
    for i in str:
        x = ord(i) + 13
        if x > 122:
            x = x -122 +96
        elif x>90 and ord(i) <= 90:
            x = x-90-64
        nstr = nstr + chr(x)
    return nstr

print(s("pilas"))