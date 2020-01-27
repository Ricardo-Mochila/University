import sys
def readFromStd():
    e = sys.stdin.buffer.read()
    j = 0
    while( j < len(e)):
        j+=2
    print(j)
readFromStd()