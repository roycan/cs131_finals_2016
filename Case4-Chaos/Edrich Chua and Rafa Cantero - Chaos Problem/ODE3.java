public class ODE3 {
	public double Val(double t, double x, double y, double z) {
		double b = 2.6666667;
		return -b*z + x*y;
	}
}