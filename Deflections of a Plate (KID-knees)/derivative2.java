public class derivative2 {
	public static double[] dx2(double[] x, double[] y) {
		int n = x.length;
		double[] dx = new double[n-2];
		double h;

		for(int i=0; i<n-2; i++) {
			h = (x[i+2] - x[i]) / 2;
			dx[i] = (y[i+2] - 2*y[i+1] + y[i]) / (h * h);
		}

		return dx;
	}
}