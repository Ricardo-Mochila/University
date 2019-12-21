import socket
import os
>>> b = os.path.getsize("/path/isa_005.mp3")

server_socket = socket.socket()
server_socket.bind(('localhost', 12345))
server_socket.listen(5)
while True:
    client_socket, addr = server_socket.accept()
    with open('ola.rtf', 'rb') as f:
        client_socket.sendfile(f)
    client_socket.close()
