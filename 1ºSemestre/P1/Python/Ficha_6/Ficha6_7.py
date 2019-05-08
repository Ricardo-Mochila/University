def pascal(lin,col ):
    if col == 0 or col == lin:
        return 1
    else:
        return pascal(lin-1, col-1) + pascal(lin-1, col)

print(pascal(4,3))