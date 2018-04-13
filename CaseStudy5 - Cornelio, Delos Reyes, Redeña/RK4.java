import java.util.*;
import java.io.*;
import java.lang.*; 

public class RK4{
	public static void main(String args[]) throws Exception{
		double t = 0; //initial t value
		double yold = 0; //initial y value
		double h = 0.1; //step size

		run_RK4(t, yold, h);
	}

	public static void run_RK4(double t, double yold, double h) throws Exception{
		double k1, k2, k3, k4, ynew;

		List<Double> t_int = new ArrayList<Double>();
		List<Double> y_int = new ArrayList<Double>();
		t_int.add(t);
		y_int.add(yold);

		t += h;

		while (t <= 10){ //set upper bound of t here
			k1 = evaluate_f(t);
			k2 = evaluate_f(t + (h / 2));
			k3 = evaluate_f(t + (h / 2));
			k4 = evaluate_f(t + h);

			ynew = yold + ((h / 6) * (k1 + (2 * k2) + (2 * k3) + k4));
			y_int.add(ynew);
			t_int.add(t);

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

	public static void printer(List<Double> t_int, List<Double> y_int) throws Exception{
		FileWriter writer = new FileWriter("output_rk4.txt"); 
		int counter = 0;

		writer.write("RK4\n\n");
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