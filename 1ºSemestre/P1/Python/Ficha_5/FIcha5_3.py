def triangulo(lmax, l1, l2):
    if lmax >= l1+l2:
        return "Não existe triangulo"

    elif lmax**2 == l1**2 + l2**2:
        return "Triangulo rectangulo"

    elif lmax**2 > l1**2 + l2**2:
        return "Triangulo obtuso"

    elif lmax**2 < l1**2 + l2**2:
        return "Triangulo agudo"
