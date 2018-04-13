/*
-read driverRK4 for instructions
-returns the value of the function after substitution
*/

public class ODE1{
	public static double f(double t, double x, double y){
		return ((0.00025*x*y)-(.7*x));
	}
}