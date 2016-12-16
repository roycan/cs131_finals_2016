import org.mariuszgromada.math.mxparser.*;

public class RK4Class
{
  //Classical RK4
  /*  Despite the fact that I could just call the general-case method with the constants used in classical,
      I kept this as-is so that you could see the direct translation of code, and it is likely
      also more familiar to others compared to the general-case which may look a bit messy */
  public static Double[][] RK4(String ODE1, String ODE2, Double a, Double b, Double h, Double x1, Double y1)
  {
    /*
    Function Description:
      Solves a system of two first order initial value ODEs using fourth order Runge-Kutta method.
    Input Variables:
      ODE1    A string representing the function that calculates dx/dt
      ODE2    A string representing the function that calculates dy/dt
      a       The first value of t
      b       The last value of t
      h       The size of an increment
      x1      The initial value of x
      y1      The initial value of y
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

    //n = (b-a)/h
    Double n = (b - a) / h;

    //create a list for t, x, and y
    Double[] t = new Double[n.intValue() + 1];
    Double[] x = new Double[n.intValue() + 1];
    Double[] y = new Double[n.intValue() + 1];

    //t(1) = a; x(1) = x1; y(1) = y1;
    t[0] = a;
    x[0] = x1;
    y[0] = y1;

    //for i = 1:n
    for (int i = 0; i < n.intValue(); i++)
    {
      //t(i+1) = t(i) + h
      t[i+1] = t[i] + h;
      //Kx1 = ODE1( t(i)    , x(i)             , y(i)             )
      Double Kx1 = (new Expression(
        ODE1,
        new Argument("t", t[i]),
        new Argument("x", x[i]),
        new Argument("y", y[i])
      )).calculate();
      //Ky1 = ODE2( t(i)    , x(i)             , y(i)             )
      Double Ky1 = (new Expression(
        ODE2,
        new Argument("t", t[i]),
        new Argument("x", x[i]),
        new Argument("y", y[i])
      )).calculate();
      //Kx2 = ODE1( tm      , x(i) + Kx1 * h/2 , y(i) + Ky1 * h/2 )
      Double Kx2 = (new Expression(
        ODE1,
        new Argument("t", t[i] + h/2),
        new Argument("x", x[i] + Kx1 * h/2),
        new Argument("y", y[i] + Ky1 * h/2)
        )).calculate();
      //Ky2 = ODE2( tm      , x(i) + Kx1 * h/2 , y(i) + Ky1 * h/2 )
      Double Ky2 = (new Expression(
        ODE2,
        new Argument("t", t[i] + h/2),
        new Argument("x", x[i] + Kx1 * h/2),
        new Argument("y", y[i] + Ky1 * h/2)
        )).calculate();
      //Kx3 = ODE1( tm      , x(i) + Kx2 * h/2 , y(i) + Ky2 * h/2 )
      Double Kx3 = (new Expression(
        ODE1,
        new Argument("t", t[i] + h/2),
        new Argument("x", x[i] + Kx2 * h/2),
        new Argument("y", y[i] + Ky2 * h/2)
        )).calculate();
      //Ky3 = ODE2( tm      , x(i) + Kx2 * h/2 , y(i) + Ky2 * h/2 )
      Double Ky3 = (new Expression(
        ODE2,
        new Argument("t", t[i] + h/2),
        new Argument("x", x[i] + Kx2 * h/2),
        new Argument("y", y[i] + Ky2 * h/2)
        )).calculate();
      //Kx4 = ODE1( t(i+1)  , x(i) + Kx3 * h   , y(i) + Ky3 * h   )
      Double Kx4 = (new Expression(
        ODE1,
        new Argument("t", t[i+1]),
        new Argument("x", x[i] + Kx3 * h),
        new Argument("y", y[i] + Ky3 * h)
        )).calculate();
      //Ky4 = ODE2( t(i+1)  , x(i) + Kx3 * h   , y(i) + Ky3 * h   )
      Double Ky4 = (new Expression(
        ODE2,
        new Argument("t", t[i+1]),
        new Argument("x", x[i] + Kx3 * h),
        new Argument("y", y[i] + Ky3 * h)
        )).calculate();
      //x(i+1) = x(i) + (Kx1 + 2 * Kx2 + 2 * Kx3 + Kx4) * h/6
      x[i+1] = x[i] + (Kx1 + 2*Kx2 + 2*Kx3 + Kx4) * h/6;
      //y(i+1) = y(i) + (Ky1 + 2 * Ky2 + 2 * Ky3 + Ky4) * h/6
      y[i+1] = y[i] + (Ky1 + 2*Ky2 + 2*Ky3 + Ky4) * h/6;
    }

    Double[][] ret = {t, x, y};
    return ret;
  }

  //general-case RK4
  public static Double[][] RK4(String ODE1, String ODE2, Double a, Double b, Double h, Double x1, Double y1,
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
      a       The first value of t
      b       The last value of t
      h       The size of an increment
      x1      The initial value of x
      y1      The initial value of y
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

    Double Csum = C1 + C2 + C3 + C4;

    Double n = (b - a) / h;

    Double[] t = new Double[n.intValue() + 1];
    Double[] x = new Double[n.intValue() + 1];
    Double[] y = new Double[n.intValue() + 1];

    t[0] = a;
    x[0] = x1;
    y[0] = y1;

    for (int i = 0; i < n.intValue(); i++)
    {
      t[i+1] = t[i] + h;
      Double Kx1 = (new Expression(
        ODE1,
        new Argument("t", t[i]),
        new Argument("x", x[i]),
        new Argument("y", y[i])
        )).calculate();
      Double Ky1 = (new Expression(
        ODE2,
        new Argument("t", t[i]),
        new Argument("x", x[i]),
        new Argument("y", y[i])
        )).calculate();
      Double Kx2 = (new Expression(
        ODE1,
        new Argument("t", t[i] + A2 * h),
        new Argument("x", x[i] + B2 * h * Kx1),
        new Argument("y", y[i] + B2 * h * Ky1)
        )).calculate();
      Double Ky2 = (new Expression(
        ODE2,
        new Argument("t", t[i] + A2 * h),
        new Argument("x", x[i] + B2 * h * Kx1),
        new Argument("y", y[i] + B2 * h * Ky1)
        )).calculate();
      Double Kx3 = (new Expression(
        ODE1,
        new Argument("t", t[i] + A3 * h),
        new Argument("x", x[i] + B31 * h * Kx1 + B32 * h * Kx2),
        new Argument("y", y[i] + B31 * h * Ky1 + B32 * h * Ky2)
        )).calculate();
      Double Ky3 = (new Expression(
        ODE2,
        new Argument("t", t[i] + A3 * h),
        new Argument("x", x[i] + B31 * h * Kx1 + B32 * h * Kx2),
        new Argument("y", y[i] + B31 * h * Ky1 + B32 * h * Ky2)
        )).calculate();
      Double Kx4 = (new Expression(
        ODE1,
        new Argument("t", t[i] + A4 * h),
        new Argument("x", x[i] + B41 * h * Kx1 + B42 * h * Kx2 + B43 * h * Kx3),
        new Argument("y", y[i] + B41 * h * Ky1 + B42 * h * Ky2 + B43 * h * Ky3)
        )).calculate();
      Double Ky4 = (new Expression(
        ODE2,
        new Argument("t", t[i] + A4 * h),
        new Argument("x", x[i] + B41 * h * Kx1 + B42 * h * Kx2 + B43 * h * Kx3),
        new Argument("y", y[i] + B41 * h * Ky1 + B42 * h * Ky2 + B43 * h * Ky3)
        )).calculate();
      x[i+1] = x[i] + (C1 * Kx1 + C2 * Kx2 + C3 * Kx3 + C4 * Kx4) / Csum * h;
      y[i+1] = y[i] + (C1 * Ky1 + C2 * Ky2 + C3 * Ky3 + C4 * Ky4) / Csum * h;
    }

    Double[][] ret = {t, x, y};
    return ret;
  }

  //allows user to pass in arrays of constants instead, just to make things a bit neater, but it still does the same routine.
  public static Double[][] RK4(String ODE1, String ODE2, Double a, Double b, Double h, Double x1, Double y1,
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
      a       The first value of t
      b       The last value of t
      h       The size of an increment
      x1      The initial value of x
      y1      The initial value of y
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
    return RK4(ODE1, ODE2, a, b, h, x1, y1,
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
