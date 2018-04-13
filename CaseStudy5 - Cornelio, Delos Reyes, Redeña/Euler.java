import java.util.*;
import java.io.*;
import java.lang.*; 

public class Euler{
	public static void main(String args[]) throws Exception{
		double t = 0; //initial t value
		double yold = 0; //initial y value
		double h = 0.1; //step size

		run_Euler(t, yold, h);
	}

	public static void run_Euler(double t, double yold, double h) throws Exception{
		double ynew;

		List<Double> t_int = new ArrayList<Double>();
		List<Double> y_int = new ArrayList<Double>();
		t_int.add(t);
		y_int.add(yold);

		t += h;

		while (t <= 10){ //set upper bound of t here
			ynew = evaluate_f(t) + (evaluate_fp(t) * h);

			t_int.add(t);
			y_int.add(ynew);
			yold = ynew;
			t += h;
		}

		printer(t_int, y_int);
	}

	public static double evaluate_f(double t){
		double f;

		f = (-1.8708 * Math.sin(2 * t)) + (2 * Math.sin(1.8708 * t)); //enter your function here
		
		return f;
	}

	public static double evaluate_fp(double t){
		double fp;

		fp = 3.7416 * (Math.cos(1.8708 * t) - Math.cos(2 * t)); //enter the derivative of your function here

		return fp;
	}

	public static void printer(List<Double> t_int, List<Double> y_int) throws Exception{
		FileWriter writer = new FileWriter("output_euler.txt"); 
		int counter = 0;

		writer.write("Euler\n\n");
		writer.write("t = [");

		for(Double str: t_int) {
		  writer.write(Double.toString(str));
		  counter++;

		  if (counter < t_int.size() - 1){
		  	writer.write(',');
		  }
		}
		
		writer.write("]\n\n");

		counter = 0;

		writer.write("y = [");

		for(Double str: y_int) {
		  writer.write(Double.toString(str));
		  counter++;

		  if (counter < y_int.size() - 1){
		  	writer.write(',');
		  }
		}

		writer.write("]");

		writer.close();
		
		//System.out.println(Arrays.toString(t_int.toArray()));
		//System.out.println(Arrays.toString(y_int.toArray()));
	}
}