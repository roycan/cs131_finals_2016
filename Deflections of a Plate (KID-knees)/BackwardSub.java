public class BackwardSub {
	public static double[] BackwardSub(double[][] A, double[] b){
		int D = A.length;
		double[] x = new double[D][D];

		for (int i=D-1; i>-1; i--) {
			double temp = b[i];
			for (int j=i; j<-1; j--) {
				if (i == j) {
					x[i] = temp / A[i][i];
				}
				else {temp -= x[j] * A[i][j];}
			}
		}

		return x;
	}
}