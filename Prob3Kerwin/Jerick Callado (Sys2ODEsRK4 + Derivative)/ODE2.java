/*
-read driverRK4 for instructions
-returns the value of the function after substitution
*/

public class ODE2{
	public static double f(double t, double x, double y){
		return ((1.7*y)-(0.0005*x*y));
	}
}