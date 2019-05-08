def rot13(string):
    s = ""
    for i in string:
        ch = ord(i)
        if ch >= ord("a") and ch <= ord("z"):

            if ch > ord("m"):
                ch = ch - 13


            else:
                ch = ch + 13

        if ch >= ord("A") and ch <= ord("Z"):
            if ch > ord("M"):
                ch = ch - 13

            else:
                ch = ch + 13

        s = s + chr(ch)

    return s
print(rot13("ola"))
