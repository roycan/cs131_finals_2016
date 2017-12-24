import java.util.*;
import java.util.Arrays;

public class SteadyState{
	public static double[][] init( Double d, Double u, Double delta_x, Double k, Double c_in, Integer n ){
		double[][] result = new double[n][n];
		double[] temp = new double[n];

		//Initialize first row
		result[0][0] = ( (2*d) / (u*delta_x) ) + ( k*delta_x/u ) + 2 + ( delta_x*u/d );
		result[0][1] = - ( (2*d) / (u*delta_x) );

		//Initialize last row
		result[n-1][n-2] = - ( (2*d) / (u*delta_x) );
		result[n-1][n-1] =  ( (2*d) / (u*delta_x) ) + ( (k*delta_x)/u );

		//Initialize 1... n-3 rows
		int col = 0;
		for(int row = 1; row <= n-2; row++){
			result[row][col] = -( (d/(u*delta_x)) + (0.5) ) ;
			result[row][col+1] = ( (2*d) / (u*delta_x) ) + ( (k*delta_x)/u );
			result[row][col+2] = -( (d/(u*delta_x)) - (0.5) );
			col++;
		}

		return result;

	}
	public static double[] rhs(Double delta_x, Double u, Double d, Double c_in, Integer n){
		double[] result = new double[n];
		result[0] = (2 + (delta_x*u)/d) * c_in;
		return result;
	}

}