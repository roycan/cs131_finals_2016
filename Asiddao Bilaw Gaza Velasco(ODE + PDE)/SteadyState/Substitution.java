import java.util.*;
import java.util.Arrays;

public class Substitution{
	//Find y in Ly = b
	public static double[] forward(double[][] matrix, double[] b){
		int n = b.length;
		double[] y = new double[n];
		double[][] temp = new double[n][n+1];

		for(int row = 0; row < n; row++){
			System.arraycopy(matrix[row], 0, temp[row], 0, matrix.length);
			temp[row][n] = b[row];
		}
		for(int row = 0; row < n; row++){
			y[row] = temp[row][temp[row].length-1];
			for(int col = 0; col < row; col++){
				y[row] = y[row] - (temp[row][col]*y[col]);
			}
			y[row] = y[row]/temp[row][row];
		}

		return y;
	}

	//Find x in Ux = y
	public static double[] backward(double[][]matrix, double[] y){
		int n = y.length;
		double[] x = new double[n];
		double[][] temp = new double[n][n+1];

		for(int row = 0; row < n; row++){
			System.arraycopy(matrix[row], 0, temp[row], 0, matrix.length);
			temp[row][n] = y[row];
		}

		for (int row = n-1; row >= 0; row--){
			x[row] = temp[row][temp[row].length-1];
			for(int col = row+1; col < n; col ++){
				x[row] = x[row] - (temp[row][col]*x[col]);
			}
			x[row] = x[row]/temp[row][row];
		}
		return x;
	}

}