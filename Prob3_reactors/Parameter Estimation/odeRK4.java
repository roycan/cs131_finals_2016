/**
**	Caparoso, Patricia Ann G.
**	201318381
**	CS 131 THR
**	December 08, 2016
**
**	This program solves a first order ODE using RK4 method
**/
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class odeRK4
{

	public static void main(String[] args) throws Exception
	{
		//Get the inputs with the order x-values, h, y-value
		double[] inputs = InputRK4.inputs();

		//Get the inputs of measured concentration
/*		String[] inputs_cm = Inputcm.inputs();
		inputs_cm[0].replace("\n", "");
		int n_cm = Integer.parseInt(inputs_cm[0]);

		Double[][] cm = new Double[n_cm][2];
		Integer i=0, j=0;

		String input = inputs_cm[1];
		input = input.replace("[", "");
		input = input.replace("]", "");

		String[] in = input.split("; ");
		for (String num : in){
			System.out.println("Outer: " + num);
			String[] nums = num.split(" ");
			j=0;
			for (String numm : nums){
				System.out.println("Inner: " + numm);
				cm[i][j] = Double.parseDouble(numm);
				j++;
			}
			i++;
		}
*/

		BufferedReader reader = new BufferedReader(new FileReader(new File("cm.csv")));
		String input = "";
		String line = reader.readLine();
		int n_cm = 0;

		while (line != null) {
			n_cm += 1;
			input += line + "\n";
			line = reader.readLine();
		}

		List<List<Double>> cm = new ArrayList<List<Double>>();
		Integer i=0, j=0;

		String[] in = input.split("\n");
		for (String num : in){
			String[] nums = num.split(",");
			cm.add(new ArrayList<Double>());
			for (String numm : nums){
				cm.get(i).add(Double.parseDouble(numm));
			}
			i++;
		}

		//Start processing inputRK
		double a = inputs[0], b = inputs[1], h = inputs[2], yini = inputs[3];

		double temp=0.0;
		if (b<a) {
			temp=b;
			b=a;
			a=temp;
		}
		double n = (b-a)/h;

		List<Double> x = new ArrayList<Double>();
		List<Double> y = new ArrayList<Double>();
		List<List<Double>> k = new ArrayList<List<Double>>();

		x.add(a);
		y.add(yini);
		double xhalf, K1, K2, K3, K4, yK1, yK2, yK3;

		for (i = 0; i < n; i++){
			x.add(x.get(i)+h);
			K1 = Function.ODE(x.get(i), y.get(i));
			xhalf = x.get(i) + (h/2);
			yK1 = y.get(i)+K1*h/2;
			K2 = Function.ODE(xhalf, yK1);
			yK2 = y.get(i)+K2*h/2;
			K3 = Function.ODE(xhalf, yK2);
			yK3 = y.get(i)+K3*h;
			K4 = Function.ODE(x.get(i+1), yK3);
			y.add(y.get(i) + ((K1 + (2*K2) + (2*K3) + K4) * h)/6);

			k.add(new ArrayList<Double>());
			k.get(i).add(K1); k.get(i).add(K2); k.get(i).add(K3); k.get(i).add(K4);

		}

		List<List<Double>> cp_cm_2 = new ArrayList<List<Double>>(cm);

		for (i = 0; i < cp_cm_2.size(); i++) {
			int cm_t = cm.get(i).get(0).intValue();
			Double cm_val = cm.get(i).get(1);
			Double cp_val = y.get(cm_t);

			cp_cm_2.get(i).set(1, Math.pow((cp_val - cm_val),2));

		}



		System.out.println("x = "+ x);
		System.out.println("y = "+ y);
		System.out.println("k = ");

		for (i = 0; i < k.size() ; i++) {
			System.out.println(k.get(i));
		}

		System.out.println("Measurements = ");

		for (i = 0; i < cm.size() ; i++) {
			System.out.println(cm.get(i).get(0) + " " + cm.get(i).get(1));
		}

		System.out.println("SSR = ");
		Double min_cm = 0.0;
		for (i = 0; i < cm.size(); i++) {
			System.out.println(cp_cm_2.get(i).get(0) + " " + cp_cm_2.get(i).get(1));
			min_cm += cp_cm_2.get(i).get(1);
		}

		System.out.println("Minimize SSR = " + min_cm);

		try {
			BufferedWriter br = new BufferedWriter(new FileWriter("odeRK4.csv"));
			StringBuilder sb = new StringBuilder();

			sb.append("x\n");
			sb.append(x + "\n");

			sb.append("k\n");
			for (i = 0; i < k.size() ; i++) {
				sb.append(k.get(i) + "\n");
			}

			sb.append("cp\n");
			sb.append(y + "\n");

			sb.append("cm\n");
			for (i = 0; i < cm.size() ; i++) {
				sb.append(cm.get(i) + "\n");
			}


			sb.append("cp_cm_2\n");
			for (i = 0; i < cm.size() ; i++) {
				sb.append(cp_cm_2.get(i) + "\n");
			}

			br.write(sb.toString());
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
