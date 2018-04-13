import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import java.util.*;
import java.io.*;  
import static java.lang.System.*;
import java.util.Scanner; 
public class Sys2ODEsRK2 extends InputRK2
{

	 public static void main(String args[]) throws Exception 
	 {
	 	InputRK2 irk = new InputRK2();
	 	ScriptEngineManager mgr = new ScriptEngineManager();
    	ScriptEngine engine = mgr.getEngineByName("JavaScript");
   

   	/*	FileInputStream in = new FileInputStream("ODE1.txt");
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
	 	System.out.println("First value of x(or most cases t)");
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
	 	System.out.println(" ");*/
	 	irk.getin();
	 	irk.x[0] = irk.a;
	 	irk.y[0] = irk.yINI;
	 	irk.z[0] = irk.zINI;
	 	irk.N=(irk.b-irk.a)/irk.h;
	 	for(int i=0;i<irk.N;i++)
	 	{
	 		irk.x[i+1]= irk.x[i] + irk.h;
	 		engine.put("y",irk.y[i]);
			engine.put("z",irk.z[i]);
	 		irk.Ky1 = 	(Double)engine.eval(irk.line);
	 		irk.Kz1 = 	(Double)engine.eval(irk.line2);
	 		//System.out.println(Ky1);
	 		engine.put("y",(irk.y[i]+(irk.Ky1*irk.h)));
			engine.put("z",(irk.z[i]+(irk.Kz1*irk.h)));
	 		irk.Ky2 = 	(Double)engine.eval(irk.line);
	 		irk.Kz2 = 	(Double)engine.eval(irk.line2);
	 		irk.y[i+1]= irk.y[i]+((irk.Ky1+irk.Ky2)*irk.h)/2;
	  		irk.z[i+1]= irk.z[i]+((irk.Kz1+irk.Kz2)*irk.h)/2;	
			

	  		System.out.print("x= ");
			System.out.println(irk.x[i]);
			System.out.print("y= ");
	 		System.out.println(irk.y[i]);
	 		System.out.print("z= ");
	 		System.out.println(irk.z[i]);
	 		System.out.println(" ");
	 		System.out.println(" ");
	 	}
	
	 }
}