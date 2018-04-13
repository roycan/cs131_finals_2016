# Eigenvalues (Extension from QR)

## Code Header

CS131 WFU-2

Borja, K.

Branzuela, J.E.

Chua, J.V.

Gabriel, K.A.


12 December 2017


##Eigenvals.java

The java class computes for the eigenvalues using the QR algorithm.


### Credits
The QR decomposition library was made by Ms. Faith Therese Peña.


### Eigenvalues
The Eigenvals.java file uses the output from the QR decomposition library. It reads the generated QR matrices from the `QR.csv` file and solves for the eigenvalues using the QR algorithm.

### Usage
1. Compile everything:

`javac -cp './:jama.jar' *.java`

2. Run the QR decomposition GUI to enter matrix A

`java -cp './:jama.jar' main x y`

x - number of columns

y - number of rows


This will output a QR.csv file which is an input file for the Eigenvalues class.

3. Run the Inverse matrix class

`java -cp './:jama.jar' Eigenvals`

This outputs a Eigenvalues.csv containing the computed values.