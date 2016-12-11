import java.io.*;
import java.util.Scanner;

/*
-This program is used to operate the derivative.java file
-derivative.f accepts the parameters double array x, double array y, double array dx, and int n
-This program prints the whole array of dx
*/

public class driverDerivative{
	public static void main (String args[]){
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Input size of x and y vectors: ");
		int n = Integer.parseInt(scan.nextLine());
		double x[] = new double[n+1];
		double y[] = new double[n+1];
		double dx[] = new double[n+1];
		for(int i = 1; i<n+1; i++){
			System.out.print("Input x" + (i) + ": ");
			x[i] = Double.parseDouble(scan.nextLine());
		}
		for(int j = 1; j<n+1; j++){
			System.out.print("Input y" + (j) + ": ");
			y[j] = Double.parseDouble(scan.nextLine());
		}
		
		derivative.f(x, y, dx, n);
		
		for(int i = 1; i<n+1; i++){
			System.out.print("dx" + (i) + " = " + dx[i] + "\n");
		}
	}
}