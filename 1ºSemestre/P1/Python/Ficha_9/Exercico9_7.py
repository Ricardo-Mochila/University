def palindromo(string):
    str1 = string[::-1]
    if str1 == string:
        return "True"
    else:
        return "False"
print(palindromo("arevivera"))