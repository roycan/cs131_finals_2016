import Jama.Matrix;

/* The function factors a matrix [A] into an orthogonal matrix 
   [Q] and an upper triangular matrix [R] */
public class QRFactorization {
	public static double[][][] QR(double[][] A) {
		int n = A.length;
		Matrix R = new Matrix(A);
		Matrix I = Matrix.identity(n, n);
		Matrix Q = I;
		
		for (int j=0; j<=(n-2); j++) {
			double[] c = col.col(R.getArray(), j);
			
			for (int k=0; k<=(j-1); k++) {
				c[k] = 0;
			}
			
			double[] e = new double[n];
			
			if (c[j]>0) {
				e[j] = 1;
			}
			else {
				e[j] = -1;
			}
			
			double clength = Math.sqrt(Mult.Mult(c, c));
			Matrix cmax = new Matrix(c, c.length);
			Matrix emax = new Matrix(e, e.length);
			Matrix v = cmax.plus(emax.times(clength));
			Matrix H = I.minus((v.times(v.transpose())).times(2/(((v.transpose()).times(v)).getArray()[0][0])));
			Q = Q.times(H);
			R = H.times(R);
		}
		
		double[][][] answer = {Q.getArrayCopy(), R.getArrayCopy()};
		return answer;
	}
}
