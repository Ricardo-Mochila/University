open Format

let par = function a -> a mod 2 = 0;;

let rec pares = function 
    |[] -> []
    |h::t -> if(par(h)) then h :: pares(t) else pares(t);;

let rec append = function 
    |[] -> (function l -> l)
    |x::y -> function l -> x::(append y l);;

let appenda = function
    | [] -> (function e -> e)
    | x::y -> (function   
        | [] -> (x::y)
        | j::k -> (x::y)::(j::k));;

let rec member = function 
    | [] -> (function e -> false)
    | h::t -> (function e -> e == h || member t e);;

let rec countElements = function
    | [] -> 0
    | h::t -> (countElements t)+1;;

let rec countElementInList = function 
    | e -> (function
        |[] -> 0
        |j::k -> if(e == j) then (countElementInList e k)+1 else countElementInList e k);;

let rec impares = function 
    | [] -> []
    | h::t -> if(not (par h)) then h::impares t else impares t;;

let rec removeElement = function
    | [] -> (function e -> [])
    | h::t -> (function e -> if(h == e) then removeElement t e else h::removeElement t e);;

let rec remove = function 
    | [] -> (function e -> e)   
    | h::t -> (function 
        |[] -> []
        |j::k -> if( member(j::k) h) then remove (h::t) (removeElement (j::k) h) else remove t (j::k));; 

let rec contagem = function
    | [] -> [] ;
    | j::k -> (j,countElementInList j (j::k)) :: (contagem (removeElement (j::k) j));; 

type numeral =
    | Z
    | S of numeral;;

let rec valor = function
    | Z -> 0
    | S e -> (valor e + 1);;

let rec numeral = function
    | 0 -> Z
    | e -> S (numeral (e-1));;

let rec soma = function e -> function a -> numeral( valor e + valor a);;