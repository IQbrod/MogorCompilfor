let x = 
   let x = 1 + 2 in x
in let rec succ x = x + 1 
in let rec double x = 2 + x 
in print_int (succ (double x))
