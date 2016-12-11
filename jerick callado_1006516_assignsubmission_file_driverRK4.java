import java.io.*;
import java.util.Scanner;

/*
-This is used to operate the Sys2ODEsRK4.java file
-place your function in ODE1.java in f method
-place your function in ODE2.java in f method
-Sys2ODEsRK4.f accepts int n, double h, double array x, double array y and double array t
*/

public class driverRK4{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		

		double a, b, h;
		System.out.print("Input first value of t: ");
		a = Double.parseDouble(scan.nextLine());
		System.out.print("Input last value of t: ");
		b = Double.parseDouble(scan.nextLine());
		System.out.print("Input size of increment of t: ");
		h = Double.parseDouble(scan.nextLine());
		double N = (b-a)/h;
		int n = (int) N;
		
		double x[] = new double[n+2];
		double y[] = new double[n+2];
		double t[] = new double[n+2];
		
		t[1] = a;
		t[n] = b;
		
		System.out.print("Input initial value of x: ");
		x[1] = Double.parseDouble(scan.nextLine());
		System.out.print("Input initial value of y: ");
		y[1] = Double.parseDouble(scan.nextLine());
		
		Sys2ODEsRK4.f(n, h, x, y, t);
		for (int i=1;i<=n+1;i++){
			System.out.print(i);
			System.out.print(" x" + i + ": " + x[i]);
			System.out.print(" y" + i + ": " + y[i]);
			System.out.print("\n");
		}
		System.out.print("x(n+1): " + x[n+1] + "\n");
		System.out.print("y(n+1): " + y[n+1] + "\n");
	}
}