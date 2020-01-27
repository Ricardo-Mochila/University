let rec count = function
    | [] -> 0
    | h::t -> count t +1;;

let rec soma = 