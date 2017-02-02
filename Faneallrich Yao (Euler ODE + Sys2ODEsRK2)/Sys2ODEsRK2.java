import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//Yao Faneallrich Li 201455331 12/5/2016 CS 131 THR
//f is the function for the ODE function
//g is the second function for the ODE Function
//y0 is our y(0)
//z0 is our z(0)
// a is the initial bound
// b is the upper bound of the system
// h is the stepsize

// modified by Lyon Lao on 12/13/2016
// added dependency on mxparser.jar, a library obtained from http://mathparser.org/
import org.mariuszgromada.math.mxparser.*;

public class Sys2ODEsRK2 {
  public static Double[][] calculate (String f, String g, Double y0, Double z0, Double a, Double b, Double h) {
      Double n = (b-a)/h;

  	Double[] T = new Double[n.intValue() + 1];
  	Double[] Y = new Double[n.intValue() + 1];
  	Double[] Z = new Double[n.intValue() + 1];

  	Double t = a;
      Double y = y0;
      Double z = z0;

  	T[0] = t;
      Y[0] = y;
  	Z[0] = z;

      for (int i = 1; i < n + 1; i++) {
        t += h;
        Double Ky1 = (new Expression(f, new Argument("x", t), new Argument("y", y), new Argument("z", z))).calculate();
  	     Double Kz1 = (new Expression(g, new Argument("x", t), new Argument("y", y), new Argument("z", z))).calculate();
        Double Ky2 = (new Expression(f, new Argument("x", t + h), new Argument("y", y + Ky1 * h), new Argument("z", z + Kz1 * h))).calculate();
        Double Kz2 = (new Expression(g, new Argument("x", t + h), new Argument("y", y + Ky1 * h), new Argument("z", z + Kz1 * h))).calculate();
        y += (h/2) * (Ky1+Ky2);
  	  z += (h/2) * (Kz1+Kz2);

  	  T[i] = t;
        Y[i] = y;
        Z[i] = z;
      }

  	Double[][] result = {T, Y, Z};
  	return result;
  }

  public static String toCsvString (Double[] t, Double[] y, Double[] z)
  {
    String retstr = new String();
    retstr += "iteration,x value,y value,z value\n";
    for (int i = 0; i < Math.min(t.length, Math.min(y.length, z.length)); i++)
    {
      retstr += Double.toString(i) + ',' + Double.toString(t[i]) + ',' + Double.toString(y[i]) + ',' + Double.toString(z[i]) + '\n';
    }
    return retstr;
  }

  public static void printCsv(Double[] t, Double[] y, Double[] z)
  {
    printCsv("Sys2ODEsRK2.csv", t, y, z);
  }

  public static void printCsv(String filename, Double[] t, Double[] y, Double[] z)
  {
    FileWriter pp = null;
    try {
      pp = new FileWriter(filename);

      pp.append(toCsvString(t, y, z));
    } catch(Exception e){
        e.printStackTrace();
    } finally {
        try{
            pp.flush();
            pp.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
  }

  public static void main (String[] args) {
    String fun1 = "x - y + z";
  	String fun2 = "x + y + z";
  	Double a;
  	Double z;
  	Double b;
  	Double c;
  	Double steps;
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
      Double[][] result = calculate (fun1,fun2,a,z,b,c,steps);
      System.out.println(Arrays.deepToString(result));
      printCsv(result[0], result[1], result[2]);
  }
}
