def descodifica(l1,l2):
    for i in range(len(l1)):
        for a in l2:
            l1.insert(l2[a], l1.pop(i))
            print(l1)

print(descodifica(["exemplo","Isto","um","pode","ser"],[4,0,3,1,2]))
