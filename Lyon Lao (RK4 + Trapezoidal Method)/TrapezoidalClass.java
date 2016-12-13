/**
 * Lyon Matthew U. Lao
 * 2013-23785
 */

import org.mariuszgromada.math.mxparser.*;

public class TrapezoidalClass
{
  public static Double[][] Trapezoidal(String Fun, Double A, Double B, Integer N)
  {
    /*
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
    */
    //convert a, b, and n into forms mxparser can understand
    Argument a = new Argument("a", A);
    Argument b = new Argument("b", B);
    Argument n = new Argument("n", N);

    //h = (b-a)/N
    Argument h = new Argument("h = (b - a) / n", a, b, n);

    //x = a:h:b
    Double[] X =  new Double[N + 1];
    Double curr_h = 0.0;
    for (int i = 0; i < X.length; i++)
    {
      X[i] = A + curr_h;
      curr_h += h.getArgumentValue();
    }

    //F = Fun(x)
    Double[] F = new Double[N + 1];
    for (int i = 0; i < F.length; i++)
    {
      Argument x = new Argument("x", X[i]);
      Expression e = new Expression(Fun, x, a, b, n, h);
      F[i] = e.calculate();
    }

    //I = h * (F(1) + F(N+1)) / 2 +h * sum(F(2:N))
    //note: offset -1 since indexing is not the same as octave
    //OFFSETTED -> I = h * (F(0) + F(N)) / 2 + h * sum(F(1:N-1))

    //I = h * (F(0) + F(N)) / 2 ...
    Double I = h.getArgumentValue() * (F[0] + F[N]) / 2;
    // ...+ h * sum(F(1:N-1))
    for (int i = 1; i < N; i++)
    {
      I += h.getArgumentValue() * F[i];
    }

    Double[][] ret = {{I}, X, F};
    return ret;
  }

  public static String toCsvString(Double I, Double[] X, Double[] F)
  {
    String ret_string = new String();
    ret_string += String.format("I = %G\n", I);
    ret_string += "X,F(x)\n";
    for (int i = 0; i < Math.min(X.length, F.length); i++)
    {
      ret_string += String.format("%.3f,%.3f\n", X[i], F[i]);
    }

    return ret_string;
  }
}
