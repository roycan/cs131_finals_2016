import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import java.util.*;
import java.io.*;  
import static java.lang.System.*;
import java.util.Scanner; 
public class NewtonRoot
{ 	
	public static void main(String args[]) throws Exception 
	{
		ScriptEngineManager mgr = new ScriptEngineManager();
    	ScriptEngine engine = mgr.getEngineByName("JavaScript");
   
		Scanner scan = new Scanner(System.in);
		String Fun,FunDer;
		Double Xest, Err,Xs,Xi,Decoy,tx,ax;
		int imax,i;
		System.out.println("Ano ba'ng function niya para sa'yo!!!");
		System.out.println("Wag kang bitter, puro x lang dapat nandito!!!");
		Fun = scan.nextLine();
		System.out.println("Derivative naman!");
		FunDer = scan.nextLine();
		System.out.println("Sa tingin mo, ano'ng solusyon sa problema niyo?");
		Xest=scan.nextDouble();
		System.out.println("Ilagay mo naman kung hanggang saan lang siya pwedeng magkamali!!");
		Err=scan.nextDouble();
		System.out.println("Last na!");
		System.out.println("Ilang beses niyo pauulit-ulitin ito na parang isang laro?");
		imax=scan.nextInt();
		for (i=0; i<imax;i++)
		{			
	
			engine.put("x",Xest);
			Xi = Xest - (Double)engine.eval(Fun)/ (Double)engine.eval(FunDer);
			if (Math.abs((Xi-Xest)/Xest) <Err)
			{
				Xs=Xi;
				System.out.println(Xs);
				System.out.println("Uyyyy!!! Ok na sila <3");
				i=0;
				break;
			}
			System.out.println(Xi);
			Xest=Xi;	

		}
		if(i==imax)
		{
			System.out.println("Ilang beses mo nang pinagbigyan pero hindi pa rin nasolusyunan!");
			System.out.println("Magbreak na kayo!!!");
		}
		//System.out.println;(Fun);

	}


}