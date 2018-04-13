Louie Federico A. Buera
2012-39187
December 8, 2016

Assigned Scripts:

SecantRoot -> SecantRoot.java
NewtonRoot -> NewtonRoot.java

Each of the above scripts have been converted to java with their own java files.
Both require a "Function" object that has a method calculating f(x) as input.
For this purpose, an interface "Function" with a single method "f" has been defined that accepts an input x as a double and returns a double as f(x).

Both classes SecantRoot and NewtonRoot implement the given script using "Function" objects as input.
For convinience, I also defined a version of the method where the functions are inputted as string expressions for f(x) and/or f'(x).

This, however requires the use of the external the package mxparser.jar, which needs to be added to the class library for the java files to compile.

This package is also used by SecantRootGUI.java and NewtonRootGUI.java for their string expression evaluation inside the GUI they provide.
These two Classes both have a main method that, when run, opens a gui utilizing the root-finding methods previously defined.

A netbeans form file has been included for editing either GUI class in netbeans. It is not required to run or compile the other Classes.