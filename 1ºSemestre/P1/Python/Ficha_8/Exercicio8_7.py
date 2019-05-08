def e_primo(n):
    for i in range(2,n+1):
            if (n)%i!=0:
                if  (n)%1==0 and (n)%n==0:
                    s = 'É numero primo'
            elif n==i:
                return('É primo')

            else:
                return'Não é primo'

    return s

print(e_primo(34))