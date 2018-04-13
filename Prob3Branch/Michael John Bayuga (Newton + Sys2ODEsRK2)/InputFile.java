import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import java.util.*;
import java.io.*;  
import static java.lang.System.*;
import java.util.Scanner; 
public class InputFile
{
		Double Xest, Err,Xs,Xi,Decoy,tx,ax;
		int imax,i;
		String Fun,FunDer;
	public void getin () throws Exception{
		ScriptEngineManager mgr = new ScriptEngineManager();
    	ScriptEngine engine = mgr.getEngineByName("JavaScript");

		Scanner scan = new Scanner(System.in);
  		FileInputStream in = new FileInputStream("Function.txt");
   		FileInputStream on = new FileInputStream("Derivative.txt");
   	 	BufferedReader buf = new BufferedReader(new InputStreamReader(in)); 
   	 	BufferedReader buf2 = new BufferedReader(new InputStreamReader(on)); 
		Fun = buf.readLine();
		FunDer = buf2.readLine();	
		//Double Xest, Err,Xs,Xi,Decoy,tx,ax;
		//int imax,i;
		System.out.println("Nakuha na function niya para sa'yo!!!");
		System.out.println("Sa tingin mo, ano'ng solusyon sa problema niyo?(Initial Solution)");
		Xest=scan.nextDouble();
		System.out.println("Ilagay mo naman kung hanggang saan lang siya pwedeng magkamali!!(Maximum Error)");
		Err=scan.nextDouble();
		System.out.println("Last na!");
		System.out.println("Ilang beses niyo pauulit-ulitin ito na parang isang laro?(Number of Iterations)");
		imax=scan.nextInt();
	}
}