import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//Yao Faneallrich Li 201455331 12/5/2016 CS 131 THR
//y0 is our initial value for y
// a is the initial value 
// b is the upper bound of the value 
// h is the stepsize
public class odeEULER {
  public static Vector<Vector<Double>> odeEULER (Function f, double y0, double a, double b, double h) {
    double n = (b-a)/h;
	int i = 1;
	Vector<Vector<Double>> result = new Vector<Vector<Double>>();
	
	FileWriter pp = null;
	try{
	pp = new FileWriter("odeEULER.csv");

	pp.append("iteration");
	pp.append(',');
	pp.append("x value");
	pp.append(',');
	pp.append("y value");
	pp.append('\n');
	Vector<Double> tvec = new Vector<Double>();
	Vector<Double> yvec = new Vector<Double>();
	double t = a;
	tvec.addElement(t);
	
    double y=y0;
	yvec.addElement(y);
    while (i <= n) {
	  pp.append(Double.toString(i));
	  pp.append(',');
	  pp.append(Double.toString(t));
	  pp.append(',');
	  pp.append(Double.toString(y));
	  pp.append('\n');
      t += h;
	  tvec.addElement(t);
      y += h * f.compute (t,y);
	  yvec.addElement(y);
	  i++;
    }
	result.addElement(tvec);
	result.addElement(yvec);
	}catch(Exception e){
		e.printStackTrace();
	
	} finally {
		try{
			pp.flush();
			pp.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	return result;
  }	


  public static void main (String[] args) {
    Function fun = new Function ();
	double a;
	double b;
	double c;
	double steps;
	Scanner s = new Scanner(System.in);
	System.out.println("Input Initial Value");
    a = s.nextDouble();
	System.out.println("Input the lower bound");
	b = s.nextDouble();
	System.out.println("Input the upper bound");
	c = s.nextDouble();
	System.out.println("Input stepsize");
	steps = s.nextDouble();
    Vector<Vector<Double>> result = new Vector<Vector<Double>>();
    result = odeEULER (fun, a, b, c, steps);
    }
  }

// interface used so we can plug in alternative functions to Euler
interface Callable {
  public double compute (double time, double t);
}

 //class to implement the newton cooling equation
//class Function implements Callable {
// public double compute (double time, double t) {
//   return -0.07 * (t - 20);
// }
//}
