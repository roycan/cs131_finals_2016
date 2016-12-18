import java.io.File;
/******************************************************************************
 *  Compilation:  javac TrapezoidalRule.java
 *  Execution:    java TrapezoidalRule a b N
 *  Example:    java TrapezoidalRule 40 93 100
 *  place your function in function2.java
 ******************************************************************************/

public class trapezoidal {
	
	 static double f(double a, double b, double N) {
      double h = (b - a) / N;              // step size
      double part1 = 0.5 * (function2.f(a) + function2.f(b)) * h;
	  double  part2 = 0;// area
      for (int i = 1; i < N; i++) {
         double x = a + h * i;
         part2 = part2 + function2.f(x);
      }

      return 2 * ( part1 + part2 * h );
   }
}
