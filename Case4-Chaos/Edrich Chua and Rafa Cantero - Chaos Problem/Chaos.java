import java.util.*;

public class Chaos extends Sys3ODEsRK4 {
	public static void main(String[] args) {
		double x1 = 5, y1 = 5, z1 = 5, a = 0, b = 20, h = 0.1; // change g to 0.01 later
		Sys3ODEsRK4 Sys2ODEsRK4 = new Sys3ODEsRK4();
		ArrayList<ArrayList<Double>> returnVectors = new ArrayList<ArrayList<Double>>();
		returnVectors = Sys2ODEsRK4.Solve(a, b, h, x1, y1, z1);
		ArrayList<Double> t = returnVectors.get(0);
		ArrayList<Double> x = returnVectors.get(1);
		ArrayList<Double> y = returnVectors.get(2);
		ArrayList<Double> z = returnVectors.get(3);
		System.out.println(t.toString());
		System.out.println(x.toString());
		System.out.println(y.toString());
		System.out.println(z.toString());
	}
}