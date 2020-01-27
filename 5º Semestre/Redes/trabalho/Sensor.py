import socket
import time
import random
import pickle
import datetime
import sys
import select

HOST = '127.0.0.1'
PORT = 33333
CHUNK_SIZE = 8 * 1024
TIMER = 10
ID = random.getrandbits(10)
firmwareVersion = 0


def firmwareVerifier():
    global firmwareVersion

    with open('firmwareSensor/firmware.txt', 'rb') as f:
        versionLine = f.readline()
        version = float(versionLine[8:])

    if(version > firmwareVersion):
        firmwareVersion = version


def connect():
    global firmwareVersion

    try:
        firmwareVerifier()
    except:
        firmwareVersion = 0.0

    serverInfo = {
        "type": "Sensor",
        "data": {"Id": ID, "Type": sys.argv[1], "Place": sys.argv[2], "Firmware": firmwareVersion}
    }

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect((HOST, PORT))
        s.sendall(pickle.dumps(serverInfo))
        while(True):
            sendInfo(s)
        s.close()


def sendInfo(info):

    global firmwareVersion

    date = datetime.datetime.now()
    year = date.year
    month = date.month
    day = date.day
    hour = date.hour
    minute = date.minute
    fullDate = str(day) + '/' + str(month) + '/' + str(year) + \
        ' ' + str(hour) + ':' + str(minute)

    if( sys.argv[1].lower() == 'SO2'.lower):
        value = random.randint(0, 4)
    elif( sys.argv[1].lower() == 'PM2.5'.lower):
        value = random.randint(0, 17)
    elif( sys.argv[1].lower() == 'PM10'.lower):
        value = random.randint(0, 30)
    elif( sys.argv[1].lower() == 'NO2'.lower):
        value = random.randint(0, 7)
    else:
        value = random.randint(0, 30)
    serverInfo = {
        "type": "Sensor",
        "data": {"Id": ID, "Date": fullDate, "Value": value, "Unity": "µg/m³", "Firmware": firmwareVersion}
    }

    info.sendall(pickle.dumps(serverInfo))

    ready = select.select([info], [], [], 1)
    if ready[0]:
        chunk = info.recv(4096)

        with open('firmwareSensor/firmware.txt', 'wb') as f:
            while True:
                if not chunk:
                    break
                else:
                    f.write(chunk)
                    break

        firmwareVerifier()

    time.sleep(TIMER)


connect()
