let rec mdc = function 
    x ->(function y -> 
        (if x = y then y else if x > y then mdc (x-y) y else mdc x (y-x))
);;