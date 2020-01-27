with open('source.txt', 'rb') as source:
    with open('destination.txt', 'rb') as destination:
        destination = destination.read()
        source = source.read()
        i = 0
        j = 0
        counter = 0
        cenas = {}
        for x in range(len(destination)):
            if(destination[x] == 0b1100011):
                print(bin(source[x]), 'index: ',x, bin(destination[x]), 'index: ', x, counter)
                counter += 1
