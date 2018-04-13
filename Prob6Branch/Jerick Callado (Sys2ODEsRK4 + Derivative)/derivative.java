/*
read driverDerivative.java for instructions
*/

public class derivative {
	public static void f(double[] x, double[] y, double[] dx, int n){
		dx[1] = (y[2]-y[1])/(x[2]-x[1]);
		for(int i = 2; i<=n-1; i++){
			dx[i] = (y[i+1] - y[i-1])/(x[i+1]-x[i-1]);
		}
		dx[n] = (y[n]-y[n-1])/(x[n]-x[n-1]);
	}
}