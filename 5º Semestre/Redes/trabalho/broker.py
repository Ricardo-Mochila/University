import select
import types
import pickle
import socket
import json

HOST = '127.0.0.1'  
PORT = 33333        
firmwareVersion = 1.0
listOfSockets = []
listOfSensorsName = []


def startBroker():
    lsock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    lsock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    lsock.bind((HOST, PORT))
    lsock.listen()
    listOfSockets.append(lsock)

    print('listening on', (HOST, PORT))

    lsock.setblocking(0)
    return lsock


def accept_wrapper(sock):
    conn, addr = sock.accept()  
    print('accepted connection from', addr)
    conn.setblocking(0)
    listOfSockets.append(conn)


def service_connection(sock):
    recv_data = sock.recv(2048)  
    if recv_data:
        try:
            data1 = pickle.loads(recv_data)
            if(data1['type'] == 'Sensor'):
                saveData(data1['data'], sock)

            if(data1['type'] == 'Admin'):
                if(data1['searchFor'] == "Id"):
                    searchForId(sock, data1)
                if(data1['searchFor'] == "List"):
                    listAllSensors(sock)
                if(data1['searchFor'] == "Deactivate"):
                    deactivateSensor(data1)
                    sock.send(b' ')

            if(data1['type'] == 'Client'):
                if(data1['searchFor'] == "AreaRegistry"):
                    areaRegistry(data1, sock)
                    
                else:
                    dataToSend = searchData(data1)
                    sock.send(pickle.dumps(dataToSend))
        except:
            with open("firmwareBroker/firmware.txt", "w") as file:
                file.write(recv_data.decode())

    else:
        print('closing connection')
        for elements in listOfSensorsName:
            if(sock in elements['Subscribed']):
                elements['Subscribed'].remove(sock)

        listOfSockets.remove(sock)
        sock.close()
    global firmwareVersion

    with open('firmwareBroker/firmware.txt', 'r') as f:
        versionLine = f.readline()
        version = float(versionLine[8:])

    if(version > firmwareVersion):
        firmwareVersion = version
        with open('firmwareBroker/firmware.txt', 'rb') as file:
            fileSend(sock, file)

def areaRegistry(data, sock):
    for element in listOfSensorsName:
        if(data['choice'] == element['Place']):
            element['Subscribed'].append(sock)

def deactivateSensor(data1):
    with open('data/data.json', 'r') as json_file:
        data = json.load(json_file)
        i = 0
        while(i < len(data)):
            if(int(data[i]['Id']) == int(data1["choice"])):
                for element in listOfSensorsName:
                    if(int(element['Id']) == int(data[i]['Id'])):
                        listOfSockets.remove(element['Socket'])
                        listOfSensorsName.remove(element)
                        break
                data.remove(data[i])
                break
            i += 1
    with open('data/data.json', 'w') as json_file:
        json.dump(data, json_file)

def listAllSensors(sock):
    sensorListToSend = []
    with open('data/data.json') as json_file:
        data = json.load(json_file)
        i = 0
        while(i < len(data)):
            sensorListToSend.append({
                "Id": data[i]['Id'],
                "Type": data[i]['Type'],
                "Place": data[i]["Place"],
                "Firmware": data[i]["Firmware"]})
            i += 1
        sock.send(pickle.dumps(sensorListToSend))


def searchForId(sock, data1):

    with open('data/data.json') as json_file:
        data = json.load(json_file)
        try:
            i = 0
            while(i < len(data)):
                if(int(data[i]['Id']) == int(data1["choice"])):
                    sock.send(pickle.dumps(
                        data[i]['Data'][len(data[i]['Data'])-1]))
                i += 1

        except Exception as err:
            sock.send(b"Place Doesn't exist")


def searchData(clientData):
    send = []
    if(clientData['searchFor'] == "Type"):
        with open('data/data.json') as json_file:
            data = json.load(json_file)
            try:
                i = 0
                while(i < len(data)):
                    if(data[i]['Type'] == clientData["choice"]):
                        send.append(data[i]['Place'])
                    i += 1
                if(len(send) == 0):
                    raise Exception
                else:
                    return send

            except:
                send.append("Place Does'n't exist")

    elif(clientData['searchFor'] == "Area"):
        with open('data/data.json') as json_file:
            data = json.load(json_file)
            try:
                i = 0
                j = 0
                while(i < len(data)):
                    if(data[i]['Place'] == clientData["choice"]):
                        while(j < len(data[i]['Data'])):
                            j += 1

                        send.append(data[i]["Data"][j-1])
                        return send
                    i += 1
                if(len(send) == 0):
                    raise Exception
                else:
                    return send

            except:
                send.append("Place Doesn't exist")
                
    return send

def saveData(data1, sock):

    with open('data/data.json') as json_file:
        data = json.load(json_file)
        try:
            i = 0
            while(i <= len(data)):
                if(data[i]['Id'] == data1["Id"]):
                    if(len(data[i]['Data']) == 10):
                        del data[i]['Data'][0]

                    sendTosocket = {"Date": data1['Date'],
                                    'Value': data1['Value'],
                                    'Unity': data1['Unity'],
                                    'Firmware': data1['Firmware']}

                    for element in listOfSensorsName:
                        if(element['Id'] == data1['Id']):
                            for sockit in element['Subscribed']:
                                sockit.send(pickle.dumps(sendTosocket))

                    data[i]['Data'].append({
                        "Date": data1['Date'],
                        'Value': data1['Value'],
                        'Unity': data1['Unity'],
                        'Firmware': data1['Firmware']})
                    break
                i += 1
            if(i == len(data)):
                raise Exception

        except Exception as err:
            listOfSensorsName.append(
                {
                    'Place' : data1['Place'],
                    'Id' : data1['Id'],
                    'Type': data1['Type'],
                    'Socket': sock,
                    'Subscribed' : []
                }
            )
            data.append({
                "Id": data1['Id'],
                "Type": data1['Type'],
                "Place": data1["Place"],
                "Firmware": data1["Firmware"],
                "Data": []
            })

    with open('data/data.json', 'w') as json_file:
        json.dump(data, json_file, indent=4)


def fileSend(sock, file):
    version = file.readline()

    for element in listOfSensorsName:
        element['Socket'].send(version)

def selectionMode():
    sel = startBroker()
    with open('data/data.json', 'w') as json_file:
        json.dump([], json_file)
    while True:
        readables, writeables, exception = select.select(
            listOfSockets, [], listOfSockets)
        for connection in readables:
            if connection == sel:
                accept_wrapper(connection)
            else:
                service_connection(connection)

selectionMode()
