import java.util.Arrays;

public class BackwardSub {
	public static double[] BS(double[][] A, double[] b){
		int D = A.length;
		double[] x = new double[D];

		for (int i=D-1; i>-1; i--) {
			double temp = b[i];
			for (int j=D-1; j>i-1; j--) {
				if (i == j) {
					x[i] = temp / A[i][i];
				}
				else {temp -= x[j] * A[i][j];}
			}
		}
		
		return x;
	}
}
