import sys;

len = len(sys.argv)
i = 0;
a = int(sys.argv[1])

if(len == 2):
    while(a > 0) :
        print(sys.argv[0])
        a -= 1
else :
    while(i < 5): 
        print(sys.argv[0])
        i+= 1