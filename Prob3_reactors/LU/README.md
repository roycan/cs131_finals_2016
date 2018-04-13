# Inverse Matrix (extension from LU Decomposition)

## Code Header

CS131 WFU-2

Borja, K.

Branzuela, J.E.

Chua, J.V.

Gabriel, K.A.

12 December 2017

## Inverse.java

The java class computes the inverse of a matrix using the decomposed LU Matrices.


### Credits

The LU decomposition library was made by Ms. Faith Therese Peña.


### Inverse Matrix

The Inverse.java file uses the output from the LU decomposition library. It reads the generated LU matrices from the `LU.csv` file and solves for the inverse of the orignal matrix by solving each column by repeated backward and forward substition.

### Usage
1. Compile everything:

`javac *.java`


2. Run the LU decomposition GUI to enter matrix A (Ax = b)

`java main x y`

x - number of columns

y - number of rows


This will output a LU.csv file which is an input file for the Inverse class.

3. Run the Inverse matrix class

`java Inverse`


This outputs a LU_Inverse.csv.


### Extension

- To solve the a system of equation using the input matrix A and the answer vector B, call the function `solve()` which will ask for the values of vector B.