import java.util.*;
import java.util.Arrays;
//import java.util.Math;

public class TransientResponse{
	//Print a 2D matrix
	private static void printMatrix(double[][] thisMatrix){
		System.out.println(Arrays.deepToString(thisMatrix));
	}
	//Print an array
	private static void printArray(double[] thisArray){
		System.out.println(Arrays.toString(thisArray));
	}
	public static double[][] init( Double d, Double u, Double delta_x, Double k, Double c_in, Integer n, double delta_t ){
		double[][] result = new double[n][n];
		double[] temp = new double[n];

		double lambda = d*delta_t/Math.pow(delta_x,2);
		double alpha = (u*delta_t)/(2*delta_x);
		
		//Initialize first row
		result[0][0] = (2*delta_t*u/delta_x) + (2*delta_x*u*alpha/d) + (2*lambda) + (k*delta_t) + 1;
		result[0][1] = (-2*lambda);

		//Initialize last row
		result[n-1][n-2] = (-2*lambda);
		result[n-1][n-1] =  (2*lambda) + (k*delta_t) + 1;

		//Initialize 1... n-3 rows
		int col = 0;
		for(int row = 1; row <= n-2; row++){
			result[row][col] = (-lambda - alpha);
			result[row][col+1] = (2*lambda) + (k*delta_t) + 1 ;
			result[row][col+2] = -lambda + alpha;
			col++;
		}
		return result;

	}
	public static double[] rhs(Double delta_x, Double u, Double d, Double c_in, Integer n, Double delta_t, double[] initial ){
		double[] result = new double[n];
		double lambda = d*delta_t/Math.pow(delta_x,2);
		double alpha = (u*delta_t)/(2*delta_x);

		result[0] = initial[0] + ((lambda+alpha)*(2*delta_x*u/d)*c_in);
		for (int i = 1; i < n-1; i++ ){
			result[i] = initial[i];
		}
		
		return result;
	}

}