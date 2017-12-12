/*
-read driverRK4 for instructions
-returns the value of the function after substitution
*/

public class ODE1{
	public static double f(double t, double x, double y, double a, double b, double c, double d){
		return ((a*x) - (b*x*y));
	}
}