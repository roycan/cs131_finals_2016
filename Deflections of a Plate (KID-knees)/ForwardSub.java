import java.util.Arrays;

/* This function solves for 'x' given and 'A' & 'b' using Forward substition.
Assumes that 'A' is and lower triangular matrix*/
public class ForwardSub {
	public static double[] FS(double[][] A, double[] b){
		int D = A.length;
		double[] x = new double[D];

		for (int i=0; i<D; i++) {
			double temp = b[i];
			for (int j=0; j<i+1; j++) {
				if (i == j) {
					x[i] = temp / A[i][i];
				}
				else {temp -= x[j] * A[i][j];}
			}
		}
		
		return x;
	}
}
