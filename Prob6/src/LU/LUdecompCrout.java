package LU;

import java.util.Arrays;


/**The function decomposes a matrix A into a lower triangular matrix L
and an upper triangular matrix U using Crout's method s.t. A = LU*/
public class LUdecompCrout {
	public static double[][][] LU(double[][] A){
		int R = A.length;
		int C = A[0].length;
		double[][] L = new double[R][C];
		double[][] U = new double[R][C];
		
		for (int i=0; i<R; i++) {
			L[i][0] = A[i][0];
			U[i][i] = 1;
		}
		
		for (int j=1; j<R; j++) {
			U[0][j] = A[0][j]/L[0][0];
		}
		
		for (int i=1; i<R; i++) {
			for (int j=1; j<=i; j++) {
				double[] temp1 = Arrays.copyOfRange(L[i], 0, j);
				double[] temp2 = new double[temp1.length];
				
				for (int k=0; k<=(j-1); k++) {
					temp2[k] = U[k][j];
				}
				
				L[i][j] = A[i][j]-Mult.Mult(temp1,temp2);
			}
			for (int j=i+1; j<R; j++){
				double[] temp1 = Arrays.copyOfRange(L[i], 0, i);
				double[] temp2 = new double[temp1.length];
				
				for (int k=0; k<=(i-1); k++) {
					temp2[k] = U[k][j];
				}
				U[i][j] = (A[i][j]-Mult.Mult(temp1,temp2))/L[i][i];
			}
		}
		
		double[][][] result = {L,U};
		return  result;
	}
}
