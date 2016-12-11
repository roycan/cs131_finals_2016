import java.io.File;
/******************************************************************************
 *  Compilation:  javac RK4.java
 *  Compilation:  javac RK4.java
 *  Execution:    java RK4 a b h yIni
 *  Execution:    java RK4 0 1.5 0.5 3
 *  place your function in function.java
 ******************************************************************************/

public class callRK4 {
   // sample client program
   public static void main(String[] args) { 
      double a = Double.parseDouble(args[0]);
	  double b = Double.parseDouble(args[1]);
      double h = Double.parseDouble(args[2]);
	  double yIni = Double.parseDouble(args[3]);
	  //System.out.print("ODE=" + ODE + "\n");
	  //System.out.print("a=" + a + "\n");
	  //System.out.print("b=" + b+ "\n");
	  //System.out.print("h=" + h + "\n");
	  //System.out.print("yIni=" + yIni + "\n");
	  
	  RK4.f(a,b,h,yIni);

   }

}