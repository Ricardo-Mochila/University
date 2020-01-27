import sys
import os

firstInput = input("")
array = []
array.append('broken-')
array.append('on-----')
array.append('off----')
array.append('unknown')

i = 0
secondArray = []
secondArray.append(firstInput)
tosend = []

def encode():
    global firstInput
    global array
    
    while(firstInput in array):
        try:

            secondInput = input("")
            secondArray.append(secondInput)
            appended = firstInput+secondInput
            if(appended in array):
                firstInput = appended
                
            else:
                output = int(array.index(firstInput)) << 1
                output = output.to_bytes(2, 'little')
                sys.stdout.buffer.write(output)
                array.append(appended)
                firstInput = secondInput
                
        except:
            
            output = int(array.index(firstInput)) << 1
            output = output.to_bytes(2, 'little')
            sys.stdout.buffer.write(output)
            return
encode()
