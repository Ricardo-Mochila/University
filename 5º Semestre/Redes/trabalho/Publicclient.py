

import socket
import pickle
import os
import threading
import time
import signal
import select
import sys

HOST = '127.0.0.1'  
PORT = 33333        
count = 0
def startClient():

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect((HOST, PORT))
        process = os.fork()
        if (process == 0):
            while True:
                receiveData(s)
                
            os.kill(0, signal.SIGSTOP)
        else:
            while True:
                menu(s)
            os.kill(0, signal.SIGSTOP)
                    
def receiveData(sock):
    data = sock.recv(1024)
    print('\n{}\n'.format(pickle.loads(data)))
 
def menu(sock):
    global count
    print("---------- MENU ----------\n"+
            "1 - Search for Type\n"+
            "2 - Search for area\n"+
            "3 - Subsribe to Location\n"+
            "4 - Exit\n")

    selection = input("Your Choice --> ")
    try:     
        typeInput = ''
        searchFor = ''
        clientDataToSend = ''
        if(int(selection) == 1):
            typeInput = input("Type: ")
            searchFor = "Type" 
            clientDataToSend = typeInput
        elif(int(selection) == 2):
            typeInput = input("Area: ")
            searchFor = "Area" 
            clientDataToSend = typeInput
        elif(int(selection) == 3):
            if(count == 0):
                typeInput = input("Area you want to receive notifications from: ")
                searchFor = "AreaRegistry"
                clientDataToSend = typeInput
                count += 1
            else:
                print("You are already subscribed to an Area")
                menu(sock)

        elif(int(selection) == 4):  
            exit()
        else:
            print("That's not an Option")
            menu(sock)

        clientInfo = {"type": 'Client', 'searchFor': searchFor, 'choice': clientDataToSend}
    
    except ValueError:
        print("That's not an Option")
    
    sock.sendall(pickle.dumps(clientInfo))

startClient()