/*
	Louise Gillian Bautista 2013-17833
	CS 131 THR 12/06/16

	-- From Sir Roy's SysODEsRK4 Octave Code:
	Sys2ODEsRK4 solves a system of two first order initial value ODEs using fourth order Runge-Kutta method.
	The independent variable is t, and the dependent variables are x and y.
	Input variables:
		ODE1	Name of a function file that calculates dx/dt.
		ODE2	Name of a function file that calculates dy/dt.
		a 		The first value of t.
		b 		The last value of t.
		h 		The size of an increment. 
		x1 		The initial value of x.
		y1 		The initial value of y.
	Output variables:
		t 		A vector with the t coordinate of the solution points.
		x 		A vector with the x coordinate of the solution points. 
		y 		A vector with the y coordinate of the solution points.

	--- Java implementation:
	t, x, y vectors are compiled in an ArrayList, and is returned by the Solve method.

	--- Sample Java Main method to use this class:
	
		double x1 = 3, y1 = 0.2, a = 0, b = 0.5, h = 0.25;
		Sys2ODEsRK4 Sys2ODEsRK4 = new Sys2ODEsRK4();
		ArrayList<ArrayList<Double>> returnVectors = new ArrayList<ArrayList<Double>>();
		returnVectors = Sys2ODEsRK4.Solve(a, b, h, x1, y1);
		ArrayList<Double> t = returnVectors.get(0);
		ArrayList<Double> x = returnVectors.get(1);
		ArrayList<Double> y = returnVectors.get(2);

	--- ODE1 and ODE2 Java File:
		public class ODE1 {
			public double Val(double t, double x, double y) {
				return Math.exp(1-t) * (y-x) + (0.5 * x);
			}
		}

		public class ODE2 {
			public double Val(double t, double x, double y) {
				return x - (y*y);
			}
		}

*/

import java.util.*;

public class Sys3ODEsRK4{

	ODE1 ODE1 = new ODE1();
	ODE2 ODE2 = new ODE2();
	ODE3 ODE3 = new ODE3();
	double Kx1, Ky1, Kz1, Kx2, Ky2, Kz2, Kx3, Ky3, Kz3, Kx4, Ky4, Kz4, tm;

	public ArrayList<ArrayList<Double>> Solve(double a, double b, double h, double x1, double y1, double z1){

		ArrayList<Double> t = new ArrayList<Double>();
		ArrayList<Double> x = new ArrayList<Double>();
		ArrayList<Double> y = new ArrayList<Double>();
		ArrayList<Double> z = new ArrayList<Double>();
		ArrayList<ArrayList<Double>> returnVectors = new ArrayList<ArrayList<Double>>();
		t.add(a); // t(0) = a
		x.add(x1); // x(0) = x1
		y.add(y1); // y(0) = y1
		z.add(z1); // z(0) = z1

		double n = (b-a)/h; 

		for(int i = 0; i < n; i++) {
			t.add(t.get(i) + h); 
			tm = t.get(i) + h/2; 
			Kx1 = ODE1.Val(t.get(i),x.get(i),y.get(i),z.get(i)); 
			// System.out.println("\tKx1: "+Kx1);
			Ky1 = ODE2.Val(t.get(i),x.get(i),y.get(i),z.get(i)); 
			// System.out.println("\tKy1: "+Ky1);
			Kz1 = ODE3.Val(t.get(i),x.get(i),y.get(i),z.get(i)); 
			// System.out.println("\tKz1: "+Kz1);

			Kx2 = ODE1.Val(tm, x.get(i)+(h/2)*Kx1, y.get(i)+(h/2)*Ky1, z.get(i)+(h/2)*Kz1); 
			// System.out.println("\tKx2: "+Kx2);
			Ky2 = ODE2.Val(tm, x.get(i)+(h/2)*Kx1, y.get(i)+(h/2)*Ky1, z.get(i)+(h/2)*Kz1);
			// System.out.println("\tKy2: "+Ky2);
			Kz2 = ODE3.Val(tm, x.get(i)+(h/2)*Kx1, y.get(i)+(h/2)*Ky1, z.get(i)+(h/2)*Kz1);
			// System.out.println("\tKz2: "+Kz2);

			Kx3 = ODE1.Val(tm, x.get(i)+(h/2)*Kx2, y.get(i)+(h/2)*Ky2, z.get(i)+(h/2)*Kz2);
			// System.out.println("\tKx3: "+Kx3);
			Ky3 = ODE2.Val(tm, x.get(i)+(h/2)*Kx2, y.get(i)+(h/2)*Ky2, z.get(i)+(h/2)*Kz2);
			// System.out.println("\tKy3: "+Ky3);
			Kz3 = ODE3.Val(tm, x.get(i)+(h/2)*Kx2, y.get(i)+(h/2)*Ky2, z.get(i)+(h/2)*Kz2);
			// System.out.println("\tKz3: "+Kz3);

			Kx4 = ODE1.Val(t.get(i+1), x.get(i)+h*Kx3, y.get(i)+h*Ky3, z.get(i)+h*Kz3);
			// System.out.println("\tKx4: "+Kx4);
			Ky4 = ODE2.Val(t.get(i+1), x.get(i)+h*Kx3, y.get(i)+h*Ky3, z.get(i)+h*Kz3);
			// System.out.println("\tKy4: "+Ky4);
			Kz4 = ODE3.Val(t.get(i+1), x.get(i)+h*Kx3, y.get(i)+h*Ky3, z.get(i)+h*Kz3);
			// System.out.println("\tKz4: "+Kz4);

			x.add( x.get(i) + (h/6)*(Kx1 + 2*Kx2 + 2*Kx3 + Kx4));
			y.add( y.get(i) + (h/6)*(Ky1 + 2*Ky2 + 2*Ky3 + Ky4));
			z.add( z.get(i) + (h/6)*(Kz1 + 2*Kz2 + 2*Kz3 + Kz4));
		}

		// compile the ArrayLists (vectors)
		returnVectors.add(t);
		returnVectors.add(x);
		returnVectors.add(y);
		returnVectors.add(z);

		return returnVectors;

	}
}