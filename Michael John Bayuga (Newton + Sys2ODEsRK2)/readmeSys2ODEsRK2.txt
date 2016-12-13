How to use?
First make a text file with file names ODE1 and ODE2
NOTE: In putting a fucnction, if  you have have exponential, it is better if you separate the multiplication unless it is very large.
NOTE 2: If your input is a constant, add .0 at the end to make it a Double
Put your dy/dx and dz/dx functions on the file respectively
Then the program will ask you the following:
1. The first value of x
2. First y or y(0) (y(x) when x is 0)
3. First z or z(0)  (z(x) when x is 0)
4. Last value for x
5. The size of increment

After that, all you have to do is to wait for the program to finish printing all the outputs per iteration.
(PS) The main function and input function has different/separate class files
Example
ODE1 has 0.00025*y*z - 0.7*y
ODE2 has 1.1*z - 0.0005*z*y
First value of x = 0
First value of y = 500
First value of z = 3000
Last value of x = 25
Size of increment  = 0.1

Output (since it will have a lot of iterations, I'll just give the answer for the first and last iteration)
First Iteration where x = 0.1 
y = 469.8759765625
z = 3268.38046875

Last iteration where x = 24.9
y = 7773.33905123544
z = 9337.928589892319