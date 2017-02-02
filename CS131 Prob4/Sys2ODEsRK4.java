/** Solves the 2 given ordinary differential equations defined in ODE1.java
  * and ODE2.java by applying the Runge-kutta method.
  */

public class Sys2ODEsRK4{
	public static void rungeKuttaSolver(double n, double h, Double[] x, Double[] y, Double[] t){
		double kx1, ky1, kx2, ky2, kx3, ky3, kx4, ky4, tm;
		
		for(int i=1; i<n+1; i++){
			t[i+1] = t[i] + h;
			tm = t[i] + h/2;
			kx1 = ODE1.f(t[i],x[i],y[i]);
			ky1 = ODE2.f(t[i],x[i],y[i]);
			
			kx2 = ODE1.f(tm, x[i]+kx1*h/2, y[i]+ky1*h/2);
			ky2 = ODE2.f(tm, x[i]+kx1*h/2, y[i]+ky1*h/2);
			
			kx3 = ODE1.f(tm, x[i]+kx2*h/2, y[i]+ky2*h/2);
			ky3 = ODE2.f(tm, x[i]+kx2*h/2, y[i]+ky2*h/2);
			
			kx4 = ODE1.f(t[i+1], x[i]+kx3*h, y[i]+ky3*h);
			ky4 = ODE2.f(t[i+1], x[i]+kx3*h, y[i]+ky3*h);
			
			x[i+1] = x[i] + (kx1 + 2*kx2 + 2*kx3 + kx4)*h/6;
			y[i+1] = y[i] + (ky1 + 2*ky2 + 2*ky3 + ky4)*h/6;
		}
	}
}