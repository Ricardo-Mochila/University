def converter():
    euro = float(input("Qual o valor?  "))

    EUR2 = euro // 2
    EUR1 = euro % 2 // 1
    Cent50 = euro % 2 //0.5 - 2*EUR1
    Cent20 = euro %2 %0.5 //0.2
    Cent10 = euro %2 %0.5 %0.2 *10.001
    print (euro , "euros =  ",int(EUR2) ,"x 2 euros,",int(EUR1) ,"x 1 euro,",
           int(Cent50) ,"x 50 cents,",int(Cent20) ,"x 20 cents,",int(Cent10) ,"x 10 cents")

