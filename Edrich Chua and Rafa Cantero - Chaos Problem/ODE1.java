public class ODE1 {
	public double Val(double t, double x, double y, double z) {
		double sigma = 10;
		return sigma*(y-x);
	}
}