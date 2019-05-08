
#funçao com itereaçao

def factorial(n):
    i = 1
    while n >= 1:
        i = i*n
        n = n-1
    return i

##########################
##########################

#funçao recursiva 

def factorial_1(n):
    if n == 1:
        return n
    elif n > 1:
        return n * factorial_1(n-1)
    
