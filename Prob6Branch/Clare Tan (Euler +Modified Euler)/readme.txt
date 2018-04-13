Hello and thank you for taking the time to read this!
My program computes for Euler and Modified Euler.  

To run it, you need the mxparser library. (download here: http://mathparser.org/)

Math equation input is based on mxparser conventions, so if you have any doubts about equation formatting or you're getting NaN outputs, you might want to check their format.
(Some quick tips: NO spaces, and it's 3*x, not 3x)

The GUI uses Java FX. This is included in Java 8 so running it shouldn't be a problem, but if you have earlier versions of Java I'm not sure if it will be there~ Just so you know.


There are 5 files, Controller.java, Main.java, EulerStyle.fxml, EulerFunction.java, and EulerModifiedFunction.java
All of them are in the same package, clare.le, so they can access each other.

EulerStyle.fxml is completely for the GUI design and looks more like html than anything else so you don't need to worry about it unless you have the urgent need to do integrations with a pink background.

Controller.java is mostly just for binding the actual integration functions to the GUI button that says integrate. And for getting the input.

Main.java kind of just opens the window. When running the whole project, run this.

EulerFunction and EulerModifiedFunction are where any math actually happens, and if you don't want the GUI you could get rid of everything but these two. 

-Clare Tan 2013-14912
cfsyjuco@gmail.com - contact me if anything breaks or if you want to rant about Java with me
