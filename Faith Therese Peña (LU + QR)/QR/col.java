/* Faith Therese Pena
   CS131 THR
*/

/* The function returns the nth column of a 2d matrix */
public class col {
	public static double[] col(double[][] R, int n) {
		double[] matrix = new double[R[n].length];
		
		for (int j = 0; j < matrix.length; j ++) {
			matrix[j] = R[j][n];
		}
		
		return matrix;
	}
}