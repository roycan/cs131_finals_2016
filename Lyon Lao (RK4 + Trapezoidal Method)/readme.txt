---[ Before you use the program ]---
  * Please download mXparser from http://mathparser.org/, as this is a required dependency for reading the function strings. Nothing will work without it.

---[ How to run the program using the GUI ]---
  * There are provided batch files for both compiling, and running the program. Please be sure to have a copy of mxparser.jar in the same directory as the other java files to use these.
  * Alternatively, you could do this manually by first compiling with the command:
      javac -classpath ;mxparser.jar GUI.java TrapezoidalGrid.java TrapezoidalClass.java RK4Grid.java RK4Class.java
    To run after compiling, simple use the command:
      java -classpath ;mxparser.jar GUI
    where ;mxparser.jar can be replaced by the path of the actual file.

---[ How to use the classes outside the program ]---
  * You are most likely concerned with using the program yourself for whatever it is you're doing, so to do that, simply add the appropriate .java file to your project's directory. RK4Class.java handles solving of a system of 2 ODEs using RK4, and TrapezoidalClass.java handles integration using the Composite Trapezoidal Method. Also, be sure to include the mxparser.jar in your project as well!

  * To actually call these classes, call the static methods associated with them (RK4Class.RK4() and TrapezoidalClass.Trapezoidal()). These return a 2D array of Doubles, but the only reason for this is that Java cannot return multiple things, much to my dismay. Really, this 2D array just contains 1D arrays, which contain the actual stuff you want.

  * Comments regarding the parameters and output variables used in the functions can be found in their .java files, so you know how to use them. However, I'll also list them down here.

    * Trapezoidal Method:
      public static Double[][] Trapezoidal(String Fun, Double A, Double B, Integer N)
        Function Description:
          Numerically integrates a function using the Composite Trapezoidal Method
        Input Variables:
          Fun     A string representing the function to be integrated
          A       Lower limit of integration
          B       Upper limit of integration
          N       The number of subintervals
        Output Variables:
          ret[0][0]  Value of the integral
          ret[1]  An array with the x coordinate of the solution points
          ret[2]  An array with the y coordinate of the solution points
        Example Usage:
          // returns the integral of x^2 from x = 0 to x = 100, with 20 intervals
          Double[][] return_values = TrapezoidalClass.Trapezoidal("x^2", 0, 100, 20);
          integralValue = return_values[0][0]; // The solution to the integral of x^2
          xCoordinates = return_values[1];     // The coordinates of each x value in those 20 intervals
          yCoordinates = return_values[2];     // The coordinates of the f(x) value for each of those x's

      * Runge-Kutta 4 Method:
        public static Double[][] RK4(String ODE1, String ODE2, Double A, Double B, Double H, Double X1, Double Y1)
          Function Description:
            Solves a system of two first order initial value ODEs using fourth order Runge-Kutta method.
            It should be noted that for using this to solve, say, u'' = 6*t, you would have:
            u   = x
            u'  = x'   = y    = ODE1
            y'  = u''  = 6*t  = ODE2
          Input Variables:
            ODE1    A string representing the function that calculates dx/dt
            ODE2    A string representing the function that calculates dy/dt
            A       The first value of T
            B       The last value of T
            H       The size of an increment
            X1      The initial value of x
            Y1      The initial value of Y
          Output Variables:
            ret[0]  An array with the t coordinate of the solution points
            ret[1]  An array with the x coordinate of the solution points
            ret[2]  An array with the y coordinate of the solution points
          Example Usage:
            // solves for u = 6*t, for 0 < t < 1, h = 0.5, and with x(0) = 0 and y(0) = 0
            Double[][] return_values = RK4Class.RK4("y", "6*t", 0, 1, 0.5, 0, 0);
            tCoordinates = return_values[0];     // The coordinates of each t value at every h from A to B
            xCoordinates = return_values[1];     // The coordinates of the x(t) value for each of those t's
            yCoordinates = return_values[2];     // The coordinates of the y(t) value for each of those t's
          Non-Classical RK4:
            The class also supports Non-Classical RK4, if ever you should need it, by supplying your own coefficients. The cleanest way to do this is to pass in extra arrays for A, B and C. The constants should follow this pattern:
              K1 = f(x, y)
              K2 = f(x + A[0] * h, y + K1 * B[0][0] * h)
              K3 = f(x + A[1] * h, y + K1 * B[1][0] * h + K2 * B[1][1] * h)
              K4 = f(x + A[2] * h, y + K1 * B[2][0] * h + K2 * B[2][1] * h + K3 * B[2][2] * h)
              new_x = x + (C[0] * K1 + C[1] * K2 + C[2] * K3 + C[3] * K4) * h
          Example Usage of Non-Classical RK4:
            Double[] A = new Double[]; Double[][] B = new Double[][]; Double[] C = new Double[];
            Double[][] return_values = RK4Class.RK4("y", "6*t", 0, 1, 0.5, 0, 0, A, B, C);
            tCoordinates = return_values[0];     // The coordinates of each t value at every h from A to B
            xCoordinates = return_values[1];     // The coordinates of the x(t) value for each of those t's
            yCoordinates = return_values[2];     // The coordinates of the y(t) value for each of those t's
