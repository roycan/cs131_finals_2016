import java.io.File;
/******************************************************************************
 *  Compilation:  javac RK4.java
 *  Compilation:  javac RK4.java
 *  Execution:    java RK4 a b h yIni
 *  Execution:    java RK4 0 1.5 0.5 3
 *  place your function in function.java
 ******************************************************************************/

public class RK4 {
   // sample client program
   public static void f(double a, double b, double h, double yIni) { 
   
   double n = (b-a)/h;
	 
	  double[] x = new double[1000];
	  double[] y = new double[1000];
	  
	  x[1] = a;
	  y[1] = yIni;
	  

	  double k1, xhalf, yk1, k2, yk2, k3, yk3, k4;
	  
	  for(int i = 1; i < n+1 ; i++ ) {
   // Statements
    //System.out.print("HELLO = " + i + "\n");
	x[i+1] = x[i] + h;
	k1 = function.f(x[i], y[i]);
	xhalf = x[i] + h /2;
	yk1 = y[i] + k1*h/2;
	k2 = function.f(xhalf, yk1);
	yk2 = y[i] + k2*h/2;
	k3 = function.f(xhalf, yk2);
	yk3 = y[i] + k3*h;
	k4 = function.f(x[i+1], yk3);
	y[i+1] = y[i] + ((k1 + 2*k2 + 2*k3 +k4) * h/6);
	System.out.print(xhalf + "," + k1 + "," + k2 + "," + k3 + "," + k4 + "\n");
	}
	//System.out.print("n = " + n + "\n");
	//System.out.print("x[i]\t");
	for (int i = 1; i< n+2; i++) {
		System.out.print("x[" + i + "]\t" + x[i] + "\n") 	;
		System.out.print("y[" + i + "]\t" + y[i] + "\n") 	;
		System.out.print("***************************\n");
	}
	


   }

}
	  