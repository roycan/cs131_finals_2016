/**
 * Lyon Matthew U. Lao
 * 2013-23785
 */

import org.mariuszgromada.math.mxparser.*;

public class RK4Class
{
  //Classical RK4
  /*  Despite the fact that I could just call the general-case method with the constants used in classical,
      I kept this as-is so that you could see the direct translation of code, and it is likely
      also more familiar to others compared to the general-case which may look a bit messy */
  public static Double[][] RK4(String ODE1, String ODE2, Double A, Double B, Double H, Double X1, Double Y1)
  {
    /*
    Function Description:
      Solves a system of two first order initial value ODEs using fourth order Runge-Kutta method.
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
    */
    //convert a, b, and h into forms mxparser can understand
    Argument a = new Argument("a", A);
    Argument b = new Argument("b", B);
    Argument h = new Argument("h", H);

    //n = (b-a)/h
    Double N = (B - A) / H;

    //create a list for t, x, and y
    Argument[] t = new Argument[N.intValue() + 1];
    Argument[] x = new Argument[N.intValue() + 1];
    Argument[] y = new Argument[N.intValue() + 1];

    //t(1) = a; x(1) = x1; y(1) = y1;
    t[0] = new Argument("t", A);
    x[0] = new Argument("x", X1);
    y[0] = new Argument("y", Y1);

    //for i = 1:n
    for (int i = 0; i < N.intValue(); i++)
    {
      //t(i+1) = t(i) + h
      t[i+1] = new Argument("t = t + h", t[i], h);
      //tm = t(i) + h/2
      Argument tm = new Argument("t = t + h/2", t[i], h);
      //Kx1 = ODE1( t(i)    , x(i)             , y(i)             )
      Argument Kx1 = new Argument("Kx1 = " + ODE1, t[i], x[i], y[i]);
      //Ky1 = ODE2( t(i)    , x(i)             , y(i)             )
      Argument Ky1 = new Argument("Ky1 = " + ODE2, t[i], x[i], y[i]);
      //Kx2 = ODE1( tm      , x(i) + Kx1 * h/2 , y(i) + Ky1 * h/2 )
      Argument Kx2 = new Argument(
        "Kx2 = " + ODE1,
        tm,
        new Argument("x = x + Kx1 * h/2", x[i], Kx1, h),
        new Argument("y = y + Ky1 * h/2", y[i], Ky1, h)
        );
      //Ky2 = ODE2( tm      , x(i) + Kx1 * h/2 , y(i) + Ky1 * h/2 )
      Argument Ky2 = new Argument(
        "Ky2 = " + ODE2,
        tm,
        new Argument("x = x + Kx1 * h/2", x[i], Kx1, h),
        new Argument("y = y + Ky1 * h/2", y[i], Ky1, h)
        );
      //Kx3 = ODE1( tm      , x(i) + Kx2 * h/2 , y(i) + Ky2 * h/2 )
      Argument Kx3 = new Argument(
        "Kx3 = " + ODE1,
        tm,
        new Argument("x = x + Kx2 * h/2", x[i], Kx2, h),
        new Argument("y = y + Ky2 * h/2", y[i], Ky2, h)
        );
      //Ky3 = ODE2( tm      , x(i) + Kx2 * h/2 , y(i) + Ky2 * h/2 )
      Argument Ky3 = new Argument(
        "Ky3 = " + ODE2,
        tm,
        new Argument("x = x + Kx2 * h/2", x[i], Kx2, h),
        new Argument("y = y + Ky2 * h/2", y[i], Ky2, h)
        );
      //Kx4 = ODE1( t(i+1)  , x(i) + Kx3 * h   , y(i) + Ky3 * h   )
      Argument Kx4 = new Argument(
        "Kx4 = " + ODE1,
        t[i+1],
        new Argument("x = x + Kx3 * h", x[i], Kx3, h),
        new Argument("y = y + Ky3 * h", y[i], Ky3, h)
        );
      //Ky4 = ODE2( t(i+1)  , x(i) + Kx3 * h   , y(i) + Ky3 * h   )
      Argument Ky4 = new Argument(
        "Ky4 = " + ODE2,
        t[i+1],
        new Argument("x = x + Kx3 * h", x[i], Kx3, h),
        new Argument("y = y + Ky3 * h", y[i], Ky3, h)
        );
      //x(i+1) = x(i) + (Kx1 + 2 * Kx2 + 2 * Kx3 + Kx4) * h/6
      x[i+1] = new Argument("x = x + (Kx1 + 2*Kx2 + 2*Kx3 + Kx4) * h/6", x[i], Kx1, Kx2, Kx3, Kx4, h);
      //y(i+1) = y(i) + (Ky1 + 2 * Ky2 + 2 * Ky3 + Ky4) * h/6
      y[i+1] = new Argument("y = y + (Ky1 + 2*Ky2 + 2*Ky3 + Ky4) * h/6", y[i], Ky1, Ky2, Ky3, Ky4, h);
    }

    Double[] ret_t = new Double[N.intValue() + 1];
    Double[] ret_x = new Double[N.intValue() + 1];
    Double[] ret_y = new Double[N.intValue() + 1];

    for (int i = 0; i < N.intValue() + 1; i++)
    {
      ret_t[i] = t[i].getArgumentValue();
      ret_x[i] = x[i].getArgumentValue();
      ret_y[i] = y[i].getArgumentValue();
    }

    Double[][] ret = {ret_t, ret_x, ret_y};
    return ret;
  }

  //general-case RK4
  public static Double[][] RK4(String ODE1, String ODE2, Double Tinit, Double Tfinal, Double H, Double X1, Double Y1,
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
      ODE1    A string representing the function that calculates dx/dt
      ODE2    A string representing the function that calculates dy/dt
      Tinit   The first value of T
      Tfinal  The last value of T
      H       The size of an increment
      X1      The initial value of x
      Y1      The initial value of Y
      A, B, and C variables: constants for general-case RK4
      constants should follow this pattern:

      K1 = f(x, y)
      K2 = f(x + A2 * h, y + K1 * B2 * h)
      K3 = f(x + A3 * h, y + K1 * B31 * h + K2 * B32 * h)
      K4 = f(x + A4 * h, y + K1 * B41 * h + K2 * B42 * h + K3 * B43 * h)
      new_x = x + (C1 * K1 + C2 * K2 + C3 * K3 + C4 * K4) * h

      Note: the weights (C) will be normalized to sum up to 1
    Output Variables:
      ret[0]  An array with the t coordinate of the solution points
      ret[1]  An array with the x coordinate of the solution points
      ret[2]  An array with the y coordinate of the solution points
    Example Usage:
      Double[] A = new Double[]; Double[][] B = new Double[][]; Double[] C = new Double[];
      Double[][] return_values = RK4Class.RK4("y", "6*t", 0, 1, 0.5, 0, 0, A, B, C);
      tCoordinates = return_values[0];     // The coordinates of each t value at every h from A to B
      xCoordinates = return_values[1];     // The coordinates of the x(t) value for each of those t's
      yCoordinates = return_values[2];     // The coordinates of the y(t) value for each of those t's
    */
    Argument tI = new Argument("tI", Tinit);
    Argument tF = new Argument("tF", Tfinal);
    Argument h = new Argument("h", H);

    Argument a2 = new Argument("a2", A2);
    Argument a3 = new Argument("a3", A3);
    Argument a4 = new Argument("a4", A4);

    Argument b2 = new Argument("b2", B2);

    Argument b31 = new Argument("b31", B31);
    Argument b32 = new Argument("b32", B32);

    Argument b41 = new Argument("b41", B41);
    Argument b42 = new Argument("b42", B42);
    Argument b43 = new Argument("b43", B43);

    Double Csum = C1 + C2 + C3 + C4;
    Argument c1 = new Argument("c1", C1 / Csum);
    Argument c2 = new Argument("c2", C2 / Csum);
    Argument c3 = new Argument("c3", C3 / Csum);
    Argument c4 = new Argument("c4", C4 / Csum);

    Double N = (Tfinal - Tinit) / H;

    Argument[] t = new Argument[N.intValue() + 1];
    Argument[] x = new Argument[N.intValue() + 1];
    Argument[] y = new Argument[N.intValue() + 1];

    t[0] = new Argument("t", Tinit);
    x[0] = new Argument("x", X1);
    y[0] = new Argument("y", Y1);

    for (int i = 0; i < N.intValue(); i++)
    {
      t[i+1] = new Argument("t = t + h", t[i], h);
      Argument Kx1 = new Argument("Kx1 = " + ODE1, t[i], x[i], y[i]);
      Argument Ky1 = new Argument("Ky1 = " + ODE2, t[i], x[i], y[i]);
      Argument Kx2 = new Argument(
        "Kx2 = " + ODE1,
        new Argument("t = t + a2 * h", t[i], a2, h),
        new Argument("x = x + b2 * h * Kx1", x[i], b2, Kx1, h),
        new Argument("y = y + b2 * h * Ky1", y[i], b2, Ky1, h)
        );
      Argument Ky2 = new Argument(
        "Ky2 = " + ODE2,
        new Argument("t = t + a2 * h", t[i], a2, h),
        new Argument("x = x + b2 * h * Kx1", x[i], b2, Kx1, h),
        new Argument("y = y + b2 * h * Ky1", y[i], b2, Ky1, h)
        );
      Argument Kx3 = new Argument(
        "Kx3 = " + ODE1,
        new Argument("t = t + a3 * h", t[i], a3, h),
        new Argument("x = x + b31 * h * Kx1 + b32 * h * Kx2", x[i], b31, Kx1, b32, Kx2, h),
        new Argument("y = y + b31 * h * Ky1 + b32 * h * Ky2", y[i], b31, Ky1, b32, Ky2, h)
        );
      Argument Ky3 = new Argument(
        "Ky3 = " + ODE2,
        new Argument("t = t + a3 * h", t[i], a3, h),
        new Argument("x = x + b31 * h * Kx1 + b32 * h * Kx2", x[i], b31, Kx1, b32, Kx2, h),
        new Argument("y = y + b31 * h * Ky1 + b32 * h * Ky2", y[i], b31, Ky1, b32, Ky2, h)
        );
      Argument Kx4 = new Argument(
        "Kx4 = " + ODE1,
        new Argument("t = t + a4 * h", t[i], a4, h),
        new Argument("x = x + b41 * h * Kx1 + b42 * h * Kx2 + b43 * h * Kx3", x[i], b41, Kx1, b42, Kx2, b43, Kx3, h),
        new Argument("y = y + b41 * h * Ky1 + b42 * h * Ky2 + b43 * h * Ky3", y[i], b41, Ky1, b42, Ky2, b43, Ky3, h)
        );
      Argument Ky4 = new Argument(
        "Ky4 = " + ODE2,
        new Argument("t = t + a4 * h", t[i], a4, h),
        new Argument("x = x + b41 * h * Kx1 + b42 * h * Kx2 + b43 * h * Kx3", x[i], b41, Kx1, b42, Kx2, b43, Kx3, h),
        new Argument("y = y + b41 * h * Ky1 + b42 * h * Ky2 + b43 * h * Ky3", y[i], b41, Ky1, b42, Ky2, b43, Ky3, h)
        );
      x[i+1] = new Argument("x = x + (c1 * Kx1 + c2 * Kx2 + c3 * Kx3 + c4 * Kx4) * h", x[i], c1, Kx1, c2, Kx2, c3, Kx3, c4, Kx4, h);
      y[i+1] = new Argument("y = y + (c1 * Ky1 + c2 * Ky2 + c3 * Ky3 + c4 * Ky4) * h", y[i], c1, Ky1, c2, Ky2, c3, Ky3, c4, Ky4, h);
    }

    Double[] ret_t = new Double[N.intValue() + 1];
    Double[] ret_x = new Double[N.intValue() + 1];
    Double[] ret_y = new Double[N.intValue() + 1];

    for (int i = 0; i < N.intValue() + 1; i++)
    {
      ret_t[i] = t[i].getArgumentValue();
      ret_x[i] = x[i].getArgumentValue();
      ret_y[i] = y[i].getArgumentValue();
    }

    Double[][] ret = {ret_t, ret_x, ret_y};
    return ret;
  }

  //allows user to pass in arrays of constants instead, just to make things a bit neater, but it still does the same routine.
  public static Double[][] RK4(String ODE1, String ODE2, Double Tinit, Double Tfinal, Double H, Double X1, Double Y1,
    Double[] A,
    Double[][] B,
    Double[] C)
  {
    /*
    Function Description:
      Solves a system of two first order initial value ODEs isomg fourth order Runge-Kutto method.
    Input Variables:
      ODE1    A string representing the function that calculates dx/dt
      ODE2    A string representing the function that calculates dy/dt
      Tinit   The first value of T
      Tfinal  The last value of T
      H       The size of an increment
      X1      The initial value of x
      Y1      The initial value of Y
      A, B, and C variables: constants for general-case RK4
      constants should follow this pattern:

      K1 = f(x, y)
      K2 = f(x + A[0] * h, y + K1 * B[0][0] * h)
      K3 = f(x + A[1] * h, y + K1 * B[1][0] * h + K2 * B[1][1] * h)
      K4 = f(x + A[2] * h, y + K1 * B[2][0] * h + K2 * B[2][1] * h + K3 * B[2][2] * h)
      new_x = x + (C[0] * K1 + C[1] * K2 + C[2] * K3 + C[3] * K4) * h

      Note: the weights (C) will be normalized to sum up to 1
    Output Variables:
      ret[0]  An array with the t coordinate of the solution points
      ret[1]  An array with the x coordinate of the solution points
      ret[2]  An array with the y coordinate of the solution points
    Example Usage:
      Double[] A = new Double[]; Double[][] B = new Double[][]; Double[] C = new Double[];
      Double[][] return_values = RK4Class.RK4("y", "6*t", 0, 1, 0.5, 0, 0, A, B, C);
      tCoordinates = return_values[0];     // The coordinates of each t value at every h from A to B
      xCoordinates = return_values[1];     // The coordinates of the x(t) value for each of those t's
      yCoordinates = return_values[2];     // The coordinates of the y(t) value for each of those t's
    */
    return RK4(ODE1, ODE2, Tinit, Tfinal, H, X1, Y1,
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
