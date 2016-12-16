package LU;

/* Faith Therese Pena
   CS131 THR
*/

/* The function multipies two arrays of the same length */
public class Mult {
	public static double Mult(double[] A, double[] B) {
		double answer = 0;
		
		for (int i = 0; i < A.length; i++) {
			answer += A[i]*B[i];
		}
		
		return answer;
	}
}