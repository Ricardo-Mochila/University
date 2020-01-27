with open('./fonte.txt', 'r') as f:
    listem = {'on-----\n' : 0, 'broken-\n' : 0, 'off----\n' : 0, 'unknown\n' : 0}
    for e in f:
        listem[e] += 1
    
    print(listem)