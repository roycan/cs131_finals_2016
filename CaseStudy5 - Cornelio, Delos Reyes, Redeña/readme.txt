Machine Problem 2: Case Study 5
Solving this Case Study required Euler and RK4 for ODE's and Eigenvalues method.

Lea Katrina Cornelio
Roben delos Reyes
Dana Kathleen Redeña

Java version of authors: 1.8.0_60

Note:
This versions of Euler and RK4 are newly implemented (had trouble running said scripts from the current repo so we just implemented our own)
The script and class for Eigenvalues are built on top of Faith Peña's QR Factorization (from the repo), we used her code as a basis for the GUI too.
============================================================================
To run Euler:

1) Enter your function in method evaluate_f from Euler.java
2) Enter the derivative of your function in method evaluate_fp from Euler.java
3) Run command 'javac Euler.java'
4) Run command 'java Euler'
5) Check y and t values in Euler_output.txt

============================================================================
To run Runge-Kutta 4th Order:

1) Enter your function in method evaluate_f from RK4.java
2) Run command 'javac RK4.java'
3) Run command 'java RK4'
4) Check y and t values in RK4_output.txt

============================================================================
To run Eigenvalues:

1) Go to the Eigenvalues sub folder
2) Run command 'javac -cp './:jama.jar' *.java'
3) Run command 'java -cp './:jama.jar' main x y' (x = # of rows; y = # of columns)
4) Wait for the GUI to load then enter your matrix accordingly
5) Click 'Calculate' button
6) Eigenvalues will appear at the diagonal (also printed in the terminal)
