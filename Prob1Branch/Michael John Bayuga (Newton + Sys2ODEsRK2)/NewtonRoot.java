import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import java.util.*;
import java.io.*;  
import static java.lang.System.*;
import java.util.Scanner; 
public class NewtonRoot extends InputFile
{ 	
	public static void main(String args[]) throws Exception 
	{
		InputFile inf= new InputFile();
		int i;
	ScriptEngineManager mgr = new ScriptEngineManager();
    	ScriptEngine engine = mgr.getEngineByName("JavaScript");
		inf.getin();
		if(inf.Xest==0)
		{
			inf.Xest=0.0001;
		}
		for (i=0; i<inf.imax;i++)
		{			
	
			engine.put("x",inf.Xest);
			inf.Xi = inf.Xest - (Double)engine.eval(inf.Fun)/ (Double)engine.eval(inf.FunDer);
			if (Math.abs((inf.Xi-inf.Xest)/inf.Xest) <=inf.Err)
			{
				inf.Xs=inf.Xi;
				System.out.println(inf.Xs);
				System.out.println("Uyyyy!!! Ok na sila <3");
				i=0;
				break;
			}
			System.out.println(inf.Xi);
			inf.Xest=inf.Xi;	

		}
		if(i==inf.imax)
		{
			System.out.println("Ilang beses mo nang pinagbigyan pero hindi pa rin nasolusyunan!");
			System.out.println("Magbreak na kayo!!!");
		}
		//System.out.println;(Fun);

	}


}