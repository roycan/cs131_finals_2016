import java.io.*;
import java.util.*;

public class main {
	//Print a 2D matrix
	private static void printMatrix(double[][] thisMatrix){
		System.out.println(Arrays.deepToString(thisMatrix));
	}
	//Print an array
	private static void printArray(double[] thisArray){
		System.out.println(Arrays.toString(thisArray));
	}

	public static void main(String args[]){

		double d = Double.parseDouble(args[0]);
		double u = Double.parseDouble(args[1]);
		double delta_x = Double.parseDouble(args[2]);

		double k = Double.parseDouble(args[3]);
		double c_in = Double.parseDouble(args[4]);
		int n = Integer.parseInt(args[5]) + 1;
		double delta_t = Double.parseDouble(args[6]);

		//Initial value of 
		double[] initial = new double[n];
		initial[0] = Double.parseDouble(args[7]); initial[1] = Double.parseDouble(args[8]);
		initial[2] = Double.parseDouble(args[9]); initial[3] = Double.parseDouble(args[10]); initial[4] = Double.parseDouble(args[11]);
		
		double[][] A = new double[n][n];
		double[] b = new double[n];
		double[] coeff = new double[n];
		double[] y = new double[n];

		//Create an array of the step sizes for plotting
		double[] stepArray = new double[n];
		for(int i = 1; i < n; i++){
			stepArray[i] = stepArray[i-1] + delta_x;
		}
		printArray(stepArray);

		//Initialize A and b from Ax = b based on the PDE
		A = TransientResponse.init(d, u, delta_x, k, c_in, n, delta_t);
		b = TransientResponse.rhs(delta_x, u, d, c_in, n, delta_t, initial );

		double[][][] LUResult = LUdecompCrout.LU(A);

		//Forward substitution Ly = b
		y = Substitution.forward(LUResult[0], b);

		//Backward substitution U*coeff = y
		coeff = Substitution.backward(LUResult[1], y);

		printArray(coeff);
	}
}