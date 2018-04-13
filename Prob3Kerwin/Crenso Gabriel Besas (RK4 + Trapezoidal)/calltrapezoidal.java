import java.io.File;
/******************************************************************************
 *  Compilation:  javac TrapezoidalRule.java
 *  Execution:    java TrapezoidalRule a b N
 *  Example:    java TrapezoidalRule 40 93 100
 *  place your function in function2.java
 ******************************************************************************/


public class calltrapezoidal {
   public static void main(String[] args) { 
      double a = Double.parseDouble(args[0]);
      double b = Double.parseDouble(args[1]);
	  double N = Double.parseDouble(args[2]);
System.out.print(trapezoidal.f(a, b, N));
   }

}