let par = function a -> a mod 2 = 0;;

let rec pares = function 
    |[] -> []
    |h::t -> if(par(h)) then h :: pares(t) else pares(t);;

let rec append = function 
    |[] -> (function l -> l)
    |x::y -> function l -> x::(append y l);;

let rec member = function 
    | [] -> (function e -> false)
    | h::t -> (function e -> e == h || member t e);;

let rec countElements = function
    | [] -> 0
    | h::t -> (countElements t)+1;;

(* NAO FUNCIONA 

let rec remove = function 
    | [] -> (function e -> e)
    | h::t -> (function 
        |[] -> []
        |j::k -> if( j==h) then (remove t k) else (remove t j::k ) || j::(remove t k));; *)

