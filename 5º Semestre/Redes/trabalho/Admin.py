import pickle
import socket
import select

HOST = '127.0.0.1'  
PORT = 33333        

def startAdmin():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect((HOST, PORT))
        while True:
            menu(s)
            ready = select.select([s],[],[],1)
            if(ready[0]):
                data = s.recv(1024)
                try:
                    print(pickle.loads(data))
                except:
                    print(data.decode())

def menu(sock):

    print("---------- MENU ----------\n" +
          "1 - List All Sensors\n" +
          "2 - Get last data from\n" +
          "3 - Send Firmware Update\n" +
          "4 - Deactivate Sensor\n"
          "5 - Exit\n")

    selection = input("Your Choice --> ")
    try:
        if(int(selection) == 1):
            searchFor = "List"
            clientDataToSend = ''
        
        elif(int(selection) == 2):
            typeInput = input("Id: ")
            searchFor = "Id"
            clientDataToSend = typeInput

        elif(int(selection) == 3):
            typeInput = input("Firmware Version: ")
            searchFor = "FirmwareUpdate"
            clientDataToSend = typeInput
            with open('firmwareAdmin/firmware.txt', 'w') as file:
                file.write("version: ")
                file.write(typeInput)
            
            with open('firmwareAdmin/firmware.txt', 'r') as file:                 
                sock.send(file.read().encode())
        
        elif(int(selection) == 4):
            typeInput = input("Id of Sensor you want to deactivate: ")
            searchFor = "Deactivate"
            clientDataToSend = typeInput

        elif(int(selection) == 5):
            exit()
        
        else:
            print("That's not an Option")

    except ValueError:
        print("That's not an Option")

    clientInfo = {"type": 'Admin', 'searchFor': searchFor,
                  'choice': clientDataToSend}

    sock.sendall(pickle.dumps(clientInfo))


startAdmin()
