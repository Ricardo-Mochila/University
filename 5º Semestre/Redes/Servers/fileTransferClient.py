import socket

CHUNK_SIZE = 8 * 1024

sock = socket.socket()
sock.connect(('localhost', 12345))
with open('olass.rtf', 'wb') as f:
    chunk = sock.recv(CHUNK_SIZE)
    while chunk:
        f.write(chunk)
        chunk = sock.recv(CHUNK_SIZE)

sock.close()
