import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import java.util.*;
import java.io.*;  
import static java.lang.System.*;
import java.util.Scanner; 
public class Sys2ODEsRK2
{

	 public static void main(String args[]) throws Exception 
	 {
	 	ScriptEngineManager mgr = new ScriptEngineManager();
    	ScriptEngine engine = mgr.getEngineByName("JavaScript");
   

   		FileInputStream in = new FileInputStream("ODE1.txt");
   		FileInputStream on = new FileInputStream("ODE2.txt");
   	 	BufferedReader buf = new BufferedReader(new InputStreamReader(in)); 
   	 	BufferedReader buf2 = new BufferedReader(new InputStreamReader(on)); 	
		String line = buf.readLine();
		String line2 = buf2.readLine();	
	 	Double a,b,h,yINI,zINI,Ky1,Ky2,Kz1,Kz2;
	 	Double[] x = new Double[256];
	 	Double[] y = new Double[256];
	 	Double[] z = new Double[256]; 
	 	Double N;
	 	Scanner scan = new Scanner(System.in);
	 	System.out.println("First value of x");
	 	a = scan.nextDouble();
	 	System.out.println("First value of y");
	 	yINI = scan.nextDouble();
	 	System.out.println("First value of z");
	 	zINI = scan.nextDouble();
	 	System.out.println("Last value of x");	 	
	 	b = scan.nextDouble();
	 	System.out.println("Size of increment");
	 	h = scan.nextDouble();
	 	System.out.println(" ");
	 	System.out.println(" ");
	 	x[0] = a;
	 	y[0] = yINI;
	 	z[0] = zINI;
	 	N=(b-a)/h;
	 	for(int i=0;i<N;i++)
	 	{
	 		x[i+1]= x[i] + h;
	 		engine.put("y",y[i]);
			engine.put("z",z[i]);
	 		Ky1 = 	(Double)engine.eval(line);
	 		Kz1 = 	(Double)engine.eval(line2);
	 		engine.put("y",(y[i]+(Ky1*h)));
			engine.put("z",(z[i]+(Kz1*h)));
	 		Ky2 = 	(Double)engine.eval(line);
	 		Kz2 = 	(Double)engine.eval(line2);
	 		y[i+1]= y[i]+((Ky1+Ky2)*h)/2;
	  		z[i+1]= z[i]+((Kz1+Kz2)*h)/2;	
			

	  		System.out.print("x= ");
			System.out.println(x[i]);
			System.out.print("y= ");
	 		System.out.println(y[i]);
	 		System.out.print("z= ");
	 		System.out.println(z[i]);
	 		System.out.println(" ");
	 		System.out.println(" ");
	 	}
	
	 }
}