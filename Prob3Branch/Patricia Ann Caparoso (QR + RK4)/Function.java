
public class Function {	

	public static double ODE(double x, double y){
		double dydx = (-1.2*y) + (7*Math.exp(-0.3* x));
		return dydx;
	}
}
