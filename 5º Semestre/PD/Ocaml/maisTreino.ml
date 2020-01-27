let rec appendList = function
    | [] -> (function e -> e)
    | x::y -> (function 
        | [] -> x::y
        | h::t -> x :: appendList y (h::t));;

let rec membro = function
    | [] -> (function e -> false)
    | x::y ->(function e ->  if(x == e) then true else membro y e);;

let rec conta = function
    | e -> (function 
        | [] -> 0
        | x::j -> if e == x then conta e j +1 else conta e j);; 

let rec removeAll = function 
    | e -> (function
        | [] -> []
        | x::j -> if x == e then removeAll e j else x::removeAll e j);;

let rec contagem = function
    | [] -> []
    | x::y -> ((x, conta x (x::y)) :: contagem (removeAll x (x::y)));;

type numeral = 
    | Z
    | S of numeral;;

let rec removeList = function
    | [] -> (function e -> e)
    | x::y -> (function 
        | [] -> []
        | j::k -> if membro (j::k) x then removeList y (removeAll x (j::k)) else removeList y (j::k));;

let rec cenas = function
    | [] -> (function e -> e)
    | x::y -> (function 
        | [] -> []
        | j::k -> if membro (x::y) j then cenas (x::y) k else j::cenas (x::y) k);; 

type 'a abp =
 | Leaf 
 | Node of ('a abp * 'a * 'a abp)

let rec insert = function
    | Leaf -> (function e -> Node(Leaf, e, Leaf))
    | Node(a,b,c) -> (function e -> if e == b then Node(a,b,c) else if e > b then Node(a, b, (insert c e)) else Node((insert a e), b ));;

let rec lookup = function
    | Leaf -> (function e -> false)
    | Node(a, b, c) -> (function e -> if e == b then true else if e > b then lookup c e else lookup a e);;

let rec elements = function 
    | V -> []
    | N (l, x, r) -> append ((append elements l x) elements r)




                                    