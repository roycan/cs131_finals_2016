/*
stores values in array x and array y
-place your function in ODE1.java in f method
-place your function in ODE2.java in f method
*/

public class Sys2ODEsRK4{
	public static void f(double n, double h, Double[] x, Double[] y, Double[] t){
		double Kx1, Ky1, Kx2, Ky2, Kx3, Ky3, Kx4, Ky4, tm;
		
		for(int i=1; i<n+1; i++){
			t[i+1] = t[i] + h;
			tm = t[i] + h/2;
			Kx1 = ODE1.f(t[i],x[i],y[i]);
			Ky1 = ODE2.f(t[i],x[i],y[i]);
			
			Kx2 = ODE1.f(tm, x[i]+Kx1*h/2, y[i]+Ky1*h/2);
			Ky2 = ODE2.f(tm, x[i]+Kx1*h/2, y[i]+Ky1*h/2);
			
			Kx3 = ODE1.f(tm, x[i]+Kx2*h/2, y[i]+Ky2*h/2);
			Ky3 = ODE2.f(tm, x[i]+Kx2*h/2, y[i]+Ky2*h/2);
			
			Kx4 = ODE1.f(t[i+1], x[i]+Kx3*h, y[i]+Ky3*h);
			Ky4 = ODE2.f(t[i+1], x[i]+Kx3*h, y[i]+Ky3*h);
			
			x[i+1] = x[i] + (Kx1 + 2*Kx2 + 2*Kx3 + Kx4)*h/6;
			y[i+1] = y[i] + (Ky1 + 2*Ky2 + 2*Ky3 + Ky4)*h/6;
		}
	}
}
