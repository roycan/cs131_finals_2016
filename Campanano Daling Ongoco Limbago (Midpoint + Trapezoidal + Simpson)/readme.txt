CS 131 MP2 readme/documentation

***This project was done by Paul Emil Ongoco, Josephus Jasper Limbago, Gerald Roy Campananano, Daine Daling
in partial fulfillment of the requirements for CS 131 1S20172018***

Motivation:
	The libraries were written to supply numerical methods for evaluation of definite integrals in the CS131 git repository
	These methods are namely Midpoint, Trapezoidal, and composite Simpson's rule
	Additionally, Richardson's extrapolation for one input stepsize is also provided

The program:
	The Java program evaluates definite integrals of one variable using Midpoint, Trapezoidal, and Simpson's methods
	!Midpoint approximates integral using rectangles as in Riemann Sums
	!Trapezoidal approximates integral using trapezoids instead of rectangles
	!Simpson's approximates integral by composing quadratic interpolants
	!Richardson's extrapolation uses a stepsize A and calculates both F(A) and F(A/2) with the above methods
	and interpolates accurate approximation of integral without using more stepsizes (less calculations)
	
	The calculations for each method follow their mathematical definitions and nothing more special
	double precision float variables were used to provide maximum accuracy
	***More info on accuracies of the methods can be found in multiple scientific computing sources***

Program requirements:
	1.Standard Java libraries (io,Swing,etc)
	2.jar command for executable
	3.mxparser (mathparser.org)

Running the GUI:
1. run MPGUI.jar (java -jar MPGUI.jar on folder)
2. Single stepsize integration [Evaluates one integral over one stepsize and displays the result in the GUI]
	a. Input equation in x (e.g. 2*x), following mxparser semantics
	b. Input left boundary for integral
	c. Input right boundary for integral
	d. Input stepsize for integral (e.g. if interval is across [0,10], stepsize of 1 yields 10 segments)
	e. Input number of decimal places for formatting (e.g. 2 converts 10.002 into 10.00)
	f. Press the Integrate button
	g. Output for all three methods displayed

3. Multiple stepsize integration [Evaluates one integral over multiple stepsizes and tabulates results in CSV file]
	a. Input all fields above except for stepsize field, instead input into multiple stepsizes field
	separate multiple stepsizes with spaces (1 2 5 10) to avoid NumberFormatException (is caught by program anyway)
	b. Input filename (DO NOT append .csv as the program writer already does this)
	c. Press Write to filename button
	d. File is created in same folder as source files

4. Richardson extrapolation [Evaluates one integral over two stepsizes, one input and half of that input]*
	a. Input all fields as in 2. except for stepsize. Use adjacent textbox instead for stepsize input
	b. Press Use Richardson button
	c. Output for all three methods displayed

Using the method Classes:

	Input Params:
	
	Midpoint, Trapezoidal, and Simpson's all have the following input parameters:
	(String userFunction, double lowerBound, double upperBound, int numberOfIntervals)
	!The variable names of the methods were clearly defined as to be readable
	!The numberOfIntervals parameter was originally designed to be double stepSize, however it is much easier (computationally)
	to input an integer number of intervals and subdivide the bounds into stepsizes than it is to handle
	a floating point stepsize and generate an integer number of intervals, which is what motivated the decision to use the former
	!For Richardson's, however, the parameter is instead stepSize, but the function calculates the numberOfIntervals internally anyway

	Output Values:
	!Midpoint, Trapezoidal, and Simpson's all have the following return value:
	{{Sum},xArray,yArray} which are the calculated integral in an array (itself the only value)
	the array of x-values (of the function)
	and array of the y-values (of the function) respectively
	!Richardson's returns an array of 3 doubles, one for each method above
