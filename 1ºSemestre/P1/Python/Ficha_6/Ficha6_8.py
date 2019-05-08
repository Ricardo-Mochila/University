def pascal ( lin , col ):
    if col == 0 or col == lin:
        return 1
    else:
        print(pascal ( lin-1 , col-1 ),pascal ( lin-1 , col-1 ) + pascal ( lin-1 , col ),pascal ( lin-1 , col ))
        return pascal ( lin-1 , col-1 ) + pascal ( lin-1 , col )

print(pascal(4,2))

def trianguloPascal ( n ) :
    if n == 0:
        return 1
    else:
        return trianguloPascal(n-1) + trianguloPascal ( n-1 )


print(trianguloPascal (0))