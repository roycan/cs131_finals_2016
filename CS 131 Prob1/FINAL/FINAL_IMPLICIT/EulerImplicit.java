import java.io.File;
import org.math.plot.*;
import javax.swing.*;

/*
-This code uses jmathplot and jmathio libraries.
-The jar files are included in the folder 
-"jar xf <.jarfile>" to extract
*/

public class EulerImplicit {
   // sample client program
   public static void main(String[] args) { 
		double a = 0, b = 0.5, h = 0.002, num, denom, xnew=0, x, N;
		int i,j, jN; 
		N = (b - a)/h; // number of iterations for euler
		jN = 250; // number of iterations for newton
		
		double[] n = new double[251];
	    double[] t = new double[251];
		double[] numarr = new double[250];
	    double[] denomarr = new double[250];
		
		n[1] = 2000; t[1] = a;
		// IMPLICIT EULER LOOP
		for(i = 1; i < N ; i++ ) {
	    	  t[i + 1] = t[i] + h;
			  x = n[i];
                          
                        Double fun_component1 = -(10 * 2000 * ( 1-Math.exp(-3*(t[i+1]))) * h) - x;
                        Double fun_component2 = 0.8 * h;
           
                        String fun = "x + x^(3/2) * " + fun_component2 + " + " + fun_component1;
                        String funDer = "1 + x^(1/2) * (3/2) * " + fun_component2;
                          // NEWTON LOOP
                        double answer;
                        answer = NewtonRoot.NewtonRoot(fun, funDer, x, 0.001, jN);
                        n[i+1] = answer;
		
		}
	

		for (i = 1; i< N+1; i++) {
		System.out.print("n[" + i + "]\t" + n[i] + "\n") 	;
		System.out.print("t[" + i + "]\t" + t[i] + "\n") 	;
		System.out.print("***************************\n");
		}
		
		Plot2DPanel panel = new Plot2DPanel();
		panel.addLinePlot("Line", t, n);
		
		JFrame frame= new JFrame("Graph n vs t");
		frame.setContentPane(panel);
		frame.setSize(500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}