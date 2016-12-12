import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//Yao Faneallrich Li 201455331 12/5/2016 CS 131 THR
//Function 2 f is the function class for the ODE function 
//Function 2_1 g is the second function class for the ODE Function
//y0 is our y(0)
//z0 is our z(0)
// a is the initial bound 
// b is the upper bound of the system 
// h is the stepsize 
public class Sys2ODEsRK2 {
  public static Vector<Vector<Double>> Sys2ODEsRK2 (Function2 f,Function2_1 g, double y0,double z0, double a, double b, double h) {
    double n = (b-a)/h;
	int i = 1;
	Vector<Vector<Double>> result = new Vector<Vector<Double>>();
	FileWriter pp = null;
	try{
	pp = new FileWriter("Sys2ODEsRK2.csv");

	pp.append("iteration");
	pp.append(',');
	pp.append("x value");
	pp.append(',');
	pp.append("y value");
	pp.append(',');
	pp.append("z value");
	pp.append('\n');
	Vector<Double> tvec = new Vector<Double>();
	Vector<Double> yvec = new Vector<Double>();
	Vector<Double> zvec = new Vector<Double>();
	double t = a;
	tvec.addElement(t);
	
    double y=y0;
	double z=z0;
	yvec.addElement(y);
	zvec.addElement(z);
    while (i <= n) {
	  pp.append(Double.toString(i));
	  pp.append(',');
	  pp.append(Double.toString(t));
	  pp.append(',');
	  pp.append(Double.toString(y));
	  pp.append(',');
	  pp.append(Double.toString(z));
	  pp.append('\n');
      t += h;
	  double Ky1 = f.compute(t,y,z);
	  double Kz1 = g.compute(t,y,z);
	  double Ky2 = f.compute(t+h,y+Ky1*h,z+Kz1*h);
	  double Kz2 = g.compute(t+h,y+Ky1*h,z+Kz1*h);
	  tvec.addElement(t);
      y += (h/2) * (Ky1+Ky2);
	  yvec.addElement(y);
	  z += (h/2) * (Kz1+Kz2);
	  zvec.addElement(z);
	  i++;
    }
	result.addElement(tvec);
	result.addElement(yvec);
	result.addElement(zvec);
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
    Function2 fun1 = new Function2 ();
	Function2_1 fun2 = new Function2_1();
	double a;
	double z;
	double b;
	double c;
	double steps;
	Scanner s = new Scanner(System.in);
	System.out.println("Input Initial Value of y");
    a = s.nextDouble();
	System.out.println("Input Initial Value of z");
    z = s.nextDouble();
	System.out.println("Input the lower bound");
	b = s.nextDouble();
	System.out.println("Input the upper bound");
	c = s.nextDouble();
	System.out.println("Input stepsize");
	steps = s.nextDouble();
    Vector<Vector<Double>> result = new Vector<Vector<Double>>();
    result = Sys2ODEsRK2 (fun1,fun2,a,z,b,c,steps);
    }
  }

interface Callable {
  public double compute (double time, double t, double c);
}

//class Cooling implements Callable {
//  public double compute (double time, double t, double c) {
//    return -0.07 * (t - 20);
//  }
//}