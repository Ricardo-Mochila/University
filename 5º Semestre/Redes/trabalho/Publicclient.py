#!/usr/bin/env python3

import socket
import types

HOST = '127.0.0.1'  # The server's hostname or IP address
PORT = 65432        # The port used by the server

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))
    s.sendall('Hello, world')
    data = types.SimpleNamespace(addr=addr, inb=b'', outb=b'')


print('Received', repr(data))