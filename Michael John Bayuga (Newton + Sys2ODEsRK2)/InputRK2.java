import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import java.util.*;
import java.io.*;  
import static java.lang.System.*;
import java.util.Scanner; 
public class InputRK2
{

		String line;
		String line2;
	 	Double a,b,h,yINI,zINI,Ky1,Ky2,Kz1,Kz2;
	 	Double[] x = new Double[256];
	 	Double[] y = new Double[256];
	 	Double[] z = new Double[256]; 
	 	Double N;
	public void getin()throws Exception
	{
	 	ScriptEngineManager mgr = new ScriptEngineManager();
    	ScriptEngine engine = mgr.getEngineByName("JavaScript");
   

   		FileInputStream in = new FileInputStream("ODE1.txt");
   		FileInputStream on = new FileInputStream("ODE2.txt");
   	 	BufferedReader buf = new BufferedReader(new InputStreamReader(in)); 
   	 	BufferedReader buf2 = new BufferedReader(new InputStreamReader(on)); 	
		line = buf.readLine();
		line2 = buf2.readLine();	
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
	 	System.out.println(" ");
	}
}
