import org.mariuszgromada.math.mxparser.*;

public class RK4Class
{
  //Classical RK4
  /*  Despite the fact that I could just call the general-case method with the constants used in classical,
      I kept this as-is so that you could see the direct translation of code, and it is likely
      also more familiar to others compared to the general-case which may look a bit messy */
  public static Double[][] RK4(String myODE1, String myODE2, Double myFirstValueOfT, Double myLastValueOfT, Double mySizeOfIncrement, Double myInitialValueOfX, Double myInitialValueOfY)
  {
    /*
    Function Description:
      Solves a system of two first order initial value ODEs using fourth order Runge-Kutta method.
    Input Variables:
      myODE1    A string representing the function that calculates dx/dt
      myODE2    A string representing the function that calculates dy/dt
      myFirstValueOfT       The first value of t
      myLastValueOfT       The last value of t
      mySizeOfIncrement       The size of an increment
      myInitialValueOfX      The initial value of x
      myInitialValueOfY      The initial value of y
    Output Variables:
      solutionscoordinatesList[0]  An array with the t coordinate of the solution points
      solutionscoordinatesList[1]  An array with the x coordinate of the solution points
      solutionscoordinatesList[2]  An array with the y coordinate of the solution points
    Example Usage:
      // solves for u = 6*t, for 0 < t < 1, mySizeOfIncrement = 0.5, and with x(0) = 0 and y(0) = 0
      Double[][] return_values = RK4Class.RK4("y", "6*t", 0, 1, 0.5, 0, 0);
      tCoordinates = return_values[0];     // The coordinates of each t value at every mySizeOfIncrement from A to B
      xCoordinates = return_values[1];     // The coordinates of the x(t) value for each of those t's
      yCoordinates = return_values[2];     // The coordinates of the y(t) value for each of those t's
    */
    //convert myFirstValueOfT, myLastValueOfT, and mySizeOfIncrement into forms mxparser can understand

    //n = (myLastValueOfT - myFirstValueOfT)/mySizeOfIncrement
    Double n = (myLastValueOfT - myFirstValueOfT) / mySizeOfIncrement;

    //create a list for t, x, and y
    Double[] t = new Double[n.intValue() + 1];
    Double[] x = new Double[n.intValue() + 1];
    Double[] y = new Double[n.intValue() + 1];

    //t(1) = a; x(1) = myInitialValueOfX; y(1) = myInitialValueOfY;
    t[0] = myFirstValueOfT;
    x[0] = myInitialValueOfX;
    y[0] = myInitialValueOfY;

    //for i = 1:n
    for (int i = 0; i < n.intValue(); i++)
    {
      //t(i+1) = t(i) + mySizeOfIncrement
      t[i+1] = t[i] + mySizeOfIncrement;
      //KmyInitialValueOfX = myODE1( t(i)    , x(i)             , y(i)             )
      Double KmyInitialValueOfX = (new Expression(
        myODE1,
        new Argument("t", t[i]),
        new Argument("x", x[i]),
        new Argument("y", y[i])
      )).calculate();
      //KmyInitialValueOfY = myODE2( t(i)    , x(i)             , y(i)             )
      Double KmyInitialValueOfY = (new Expression(
        myODE2,
        new Argument("t", t[i]),
        new Argument("x", x[i]),
        new Argument("y", y[i])
      )).calculate();
      //Kx2 = myODE1( tm      , x(i) + KmyInitialValueOfX * mySizeOfIncrement/2 , y(i) + KmyInitialValueOfY * mySizeOfIncrement/2 )
      Double Kx2 = (new Expression(
        myODE1,
        new Argument("t", t[i] + mySizeOfIncrement/2),
        new Argument("x", x[i] + KmyInitialValueOfX * mySizeOfIncrement/2),
        new Argument("y", y[i] + KmyInitialValueOfY * mySizeOfIncrement/2)
        )).calculate();
      //Ky2 = myODE2( tm      , x(i) + KmyInitialValueOfX * mySizeOfIncrement/2 , y(i) + KmyInitialValueOfY * mySizeOfIncrement/2 )
      Double Ky2 = (new Expression(
        myODE2,
        new Argument("t", t[i] + mySizeOfIncrement/2),
        new Argument("x", x[i] + KmyInitialValueOfX * mySizeOfIncrement/2),
        new Argument("y", y[i] + KmyInitialValueOfY * mySizeOfIncrement/2)
        )).calculate();
      //Kx3 = myODE1( tm      , x(i) + Kx2 * mySizeOfIncrement/2 , y(i) + Ky2 * mySizeOfIncrement/2 )
      Double Kx3 = (new Expression(
        myODE1,
        new Argument("t", t[i] + mySizeOfIncrement/2),
        new Argument("x", x[i] + Kx2 * mySizeOfIncrement/2),
        new Argument("y", y[i] + Ky2 * mySizeOfIncrement/2)
        )).calculate();
      //Ky3 = myODE2( tm      , x(i) + Kx2 * mySizeOfIncrement/2 , y(i) + Ky2 * mySizeOfIncrement/2 )
      Double Ky3 = (new Expression(
        myODE2,
        new Argument("t", t[i] + mySizeOfIncrement/2),
        new Argument("x", x[i] + Kx2 * mySizeOfIncrement/2),
        new Argument("y", y[i] + Ky2 * mySizeOfIncrement/2)
        )).calculate();
      //Kx4 = myODE1( t(i+1)  , x(i) + Kx3 * mySizeOfIncrement   , y(i) + Ky3 * mySizeOfIncrement   )
      Double Kx4 = (new Expression(
        myODE1,
        new Argument("t", t[i+1]),
        new Argument("x", x[i] + Kx3 * mySizeOfIncrement),
        new Argument("y", y[i] + Ky3 * mySizeOfIncrement)
        )).calculate();
      //Ky4 = myODE2( t(i+1)  , x(i) + Kx3 * mySizeOfIncrement   , y(i) + Ky3 * mySizeOfIncrement   )
      Double Ky4 = (new Expression(
        myODE2,
        new Argument("t", t[i+1]),
        new Argument("x", x[i] + Kx3 * mySizeOfIncrement),
        new Argument("y", y[i] + Ky3 * mySizeOfIncrement)
        )).calculate();
      //x(i+1) = x(i) + (KmyInitialValueOfX + 2 * Kx2 + 2 * Kx3 + Kx4) * mySizeOfIncrement/6
      x[i+1] = x[i] + (KmyInitialValueOfX + 2*Kx2 + 2*Kx3 + Kx4) * mySizeOfIncrement/6;
      //y(i+1) = y(i) + (KmyInitialValueOfY + 2 * Ky2 + 2 * Ky3 + Ky4) * mySizeOfIncrement/6
      y[i+1] = y[i] + (KmyInitialValueOfY + 2*Ky2 + 2*Ky3 + Ky4) * mySizeOfIncrement/6;
    }// End of for (int i = 0; i < n.intValue(); i++)

    Double[][] solutionscoordinatesList = {t, x, y};
    return solutionscoordinatesList;
  }

  //general-case RK4
  public static Double[][] RK4(String myODE1, String myODE2, Double myFirstValueOfT, Double myLastValueOfT, Double mySizeOfIncrement, Double myInitialValueOfX, Double myInitialValueOfY,
    Double A2, Double A3, Double A4,
    Double B2,
    Double B31, Double B32,
    Double B41, Double B42, Double B43,
    Double C1, Double C2, Double C3, Double C4)
  {
    /*
    Function Description:
      Solves a system of two first order initial value ODEs isomg fourth order Runge-Kutto method.
    Input Variables:
      myODE1    A string representing the function that calculates dx/dt
      myODE2    A string representing the function that calculates dy/dt
      myFirstValueOfT       The first value of t
      myLastValueOfT       The last value of t
      mySizeOfIncrement       The size of an increment
      myInitialValueOfX      The initial value of x
      myInitialValueOfY      The initial value of y
      A, B, and C variables: constants for general-case RK4
      constants should follow this pattern:

      K1 = f(x, y)
      K2 = f(x + A2 * mySizeOfIncrement, y + K1 * B2 * mySizeOfIncrement)
      K3 = f(x + A3 * mySizeOfIncrement, y + K1 * B31 * mySizeOfIncrement + K2 * B32 * mySizeOfIncrement)
      K4 = f(x + A4 * mySizeOfIncrement, y + K1 * B41 * mySizeOfIncrement + K2 * B42 * mySizeOfIncrement + K3 * B43 * mySizeOfIncrement)
      new_x = x + (C1 * K1 + C2 * K2 + C3 * K3 + C4 * K4) * mySizeOfIncrement

      Note: the weights (C) will be normalized to sum up to 1
    Output Variables:
      solutionscoordinatesList[0]  An array with the t coordinate of the solution points
      solutionscoordinatesList[1]  An array with the x coordinate of the solution points
      solutionscoordinatesList[2]  An array with the y coordinate of the solution points
    Example Usage:
      Double[] A = new Double[]; Double[][] B = new Double[][]; Double[] C = new Double[];
      Double[][] return_values = RK4Class.RK4("y", "6*t", 0, 1, 0.5, 0, 0, A, B, C);
      tCoordinates = return_values[0];     // The coordinates of each t value at every h from A to B
      xCoordinates = return_values[1];     // The coordinates of the x(t) value for each of those t's
      yCoordinates = return_values[2];     // The coordinates of the y(t) value for each of those t's
    */

    Double Csum = C1 + C2 + C3 + C4;

    Double n = (myLastValueOfT - myFirstValueOfT) / mySizeOfIncrement;

    Double[] t = new Double[n.intValue() + 1];
    Double[] x = new Double[n.intValue() + 1];
    Double[] y = new Double[n.intValue() + 1];

    t[0] = myFirstValueOfT;
    x[0] = myInitialValueOfX;
    y[0] = myInitialValueOfY;

    for (int i = 0; i < n.intValue(); i++)
    {
      t[i+1] = t[i] + mySizeOfIncrement;
      Double KmyInitialValueOfX = (new Expression(
        myODE1,
        new Argument("t", t[i]),
        new Argument("x", x[i]),
        new Argument("y", y[i])
        )).calculate();
      Double KmyInitialValueOfY = (new Expression(
        myODE2,
        new Argument("t", t[i]),
        new Argument("x", x[i]),
        new Argument("y", y[i])
        )).calculate();
      Double Kx2 = (new Expression(
        myODE1,
        new Argument("t", t[i] + A2 * mySizeOfIncrement),
        new Argument("x", x[i] + B2 * mySizeOfIncrement * KmyInitialValueOfX),
        new Argument("y", y[i] + B2 * mySizeOfIncrement * KmyInitialValueOfY)
        )).calculate();
      Double Ky2 = (new Expression(
        myODE2,
        new Argument("t", t[i] + A2 * mySizeOfIncrement),
        new Argument("x", x[i] + B2 * mySizeOfIncrement * KmyInitialValueOfX),
        new Argument("y", y[i] + B2 * mySizeOfIncrement * KmyInitialValueOfY)
        )).calculate();
      Double Kx3 = (new Expression(
        myODE1,
        new Argument("t", t[i] + A3 * mySizeOfIncrement),
        new Argument("x", x[i] + B31 * mySizeOfIncrement * KmyInitialValueOfX + B32 * mySizeOfIncrement * Kx2),
        new Argument("y", y[i] + B31 * mySizeOfIncrement * KmyInitialValueOfY + B32 * mySizeOfIncrement * Ky2)
        )).calculate();
      Double Ky3 = (new Expression(
        myODE2,
        new Argument("t", t[i] + A3 * mySizeOfIncrement),
        new Argument("x", x[i] + B31 * mySizeOfIncrement * KmyInitialValueOfX + B32 * mySizeOfIncrement * Kx2),
        new Argument("y", y[i] + B31 * mySizeOfIncrement * KmyInitialValueOfY + B32 * mySizeOfIncrement * Ky2)
        )).calculate();
      Double Kx4 = (new Expression(
        myODE1,
        new Argument("t", t[i] + A4 * mySizeOfIncrement),
        new Argument("x", x[i] + B41 * mySizeOfIncrement * KmyInitialValueOfX + B42 * mySizeOfIncrement * Kx2 + B43 * mySizeOfIncrement * Kx3),
        new Argument("y", y[i] + B41 * mySizeOfIncrement * KmyInitialValueOfY + B42 * mySizeOfIncrement * Ky2 + B43 * mySizeOfIncrement * Ky3)
        )).calculate();
      Double Ky4 = (new Expression(
        myODE2,
        new Argument("t", t[i] + A4 * mySizeOfIncrement),
        new Argument("x", x[i] + B41 * mySizeOfIncrement * KmyInitialValueOfX + B42 * mySizeOfIncrement * Kx2 + B43 * mySizeOfIncrement * Kx3),
        new Argument("y", y[i] + B41 * mySizeOfIncrement * KmyInitialValueOfY + B42 * mySizeOfIncrement * Ky2 + B43 * mySizeOfIncrement * Ky3)
        )).calculate();
      x[i+1] = x[i] + (C1 * KmyInitialValueOfX + C2 * Kx2 + C3 * Kx3 + C4 * Kx4) / Csum * mySizeOfIncrement;
      y[i+1] = y[i] + (C1 * KmyInitialValueOfY + C2 * Ky2 + C3 * Ky3 + C4 * Ky4) / Csum * mySizeOfIncrement;
    }

    Double[][] solutionscoordinatesList = {t, x, y};
    return solutionscoordinatesList;
  }

  //allows user to pass in arrays of constants instead, just to make things a bit neater, but it still does the same routine.
  public static Double[][] RK4(String myODE1, String myODE2, Double myFirstValueOfT, Double myLastValueOfT, Double mySizeOfIncrement, Double myInitialValueOfX, Double myInitialValueOfY,
    Double[] A,
    Double[][] B,
    Double[] C)
  {
    /*
    Function Description:
      Solves a system of two first order initial value ODEs isomg fourth order Runge-Kutto method.
    Input Variables:
      myODE1    A string representing the function that calculates dx/dt
      myODE2    A string representing the function that calculates dy/dt
      myFirstValueOfT      The first value of t
      myLastValueOfT       The last value of t
      mySizeOfIncrement       The size of an increment
      myInitialValueOfX      The initial value of x
      myInitialValueOfY      The initial value of y
      A, B, and C variables: constants for general-case RK4
      constants should follow this pattern:

      K1 = f(x, y)
      K2 = f(x + A[0] * mySizeOfIncrement, y + K1 * B[0][0] * mySizeOfIncrement)
      K3 = f(x + A[1] * mySizeOfIncrement, y + K1 * B[1][0] * mySizeOfIncrement + K2 * B[1][1] * mySizeOfIncrement)
      K4 = f(x + A[2] * mySizeOfIncrement, y + K1 * B[2][0] * mySizeOfIncrement + K2 * B[2][1] * mySizeOfIncrement + K3 * B[2][2] * mySizeOfIncrement)
      new_x = x + (C[0] * K1 + C[1] * K2 + C[2] * K3 + C[3] * K4) * mySizeOfIncrement

      Note: the weights (C) will be normalized to sum up to 1
    Output Variables:
      solutionscoordinatesList[0]  An array with the t coordinate of the solution points
      solutionscoordinatesList[1]  An array with the x coordinate of the solution points
      solutionscoordinatesList[2]  An array with the y coordinate of the solution points
    Example Usage:
      Double[] A = new Double[]; Double[][] B = new Double[][]; Double[] C = new Double[];
      Double[][] return_values = RK4Class.RK4("y", "6*t", 0, 1, 0.5, 0, 0, A, B, C);
      tCoordinates = return_values[0];     // The coordinates of each t value at every mySizeOfIncrement from A to B
      xCoordinates = return_values[1];     // The coordinates of the x(t) value for each of those t's
      yCoordinates = return_values[2];     // The coordinates of the y(t) value for each of those t's
    */
    return RK4(myODE1, myODE2, myFirstValueOfT, myLastValueOfT, mySizeOfIncrement, myInitialValueOfX, myInitialValueOfY,
      A[0], A[1], A[2],
      B[0][0],
      B[1][0], B[1][1],
      B[2][0], B[2][1], B[2][2],
      C[0], C[1], C[2], C[3]
    );
  }

  public static String toCsvString(Double[] T, Double[] X, Double[] Y)
  {
    String ret_string = new String();
    ret_string += "T,X,Y\n";
    for (int i = 0; i < Math.min(T.length, Math.min(X.length, Y.length)); i++)
    {
      ret_string += String.format("%s,%s,%s\n", T[i].toString(), X[i].toString(), Y[i].toString());
    }

    return ret_string;
  }
}