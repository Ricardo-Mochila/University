import sys
import re

array = []
array.append('broken-')
array.append('on-----')
array.append('off----')
array.append('unknown')

received = []
output = []
def readFromStd():
    e = sys.stdin.buffer.read()
    j = 0
    while(j < len(e)):
        firstByte = bytes([e[j]])
        secondByte =  bytes([e[j+1]])
        sume = firstByte + secondByte
        
        sume = int.from_bytes(sume, "little")
        sume = sume >> 1
        received.append(sume)
        j+=2

def decode():
    output.append(array[int(received[0])])
    try:
        while(len(received) > 1):
            actual = array[int(received[0])]
            try:
                prox = array[int(received[1])]
                output.append(prox)
                if(len(prox) > 7):
                    prox = array[int(received[1])][:7]
                    
                array.append(actual+prox)
         
                received.pop(0)
            except:
                if(len(actual) > 7):
                    actual2 = actual[:7]
                else:
                    actual2 = actual
                prox= actual + actual2
                output.append(prox)
                array.append(prox)
       
                received.pop(0)
                
    except Exception as err:
        print(err)
        return

def formatText():
    for element in output:
        while(len(element) > 7):
            print(element[:7])
            element = element[7:]
        if(len(element) > 0):
            print(element)

readFromStd()
decode()
formatText()
