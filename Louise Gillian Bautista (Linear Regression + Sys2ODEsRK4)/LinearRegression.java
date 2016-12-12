/*
	Louise Gillian Bautista 2013-17833
	CS 131 THR 12/06/16

	-- From Sir Roy's Linear Regression Octave Code:
	Linear Regression calculates the coefficients a1 and a0 of the linear equation y = a1*x + a0 that best fit n data points.
	Input variables:
		x 		A vector with the coordinates x of the data points.
		y 		A vector with the coordinates y of the data points.
	Output variables:
		a1		The coefficient a1.
		a0		The coefficient a0.

	--- Java implementation:
	x and y are declared as ArrayList<Double> 
	a1 and a0 are stored in a Java array.

	--- Sample Java Main method to use this class:

		LinearRegression LinearRegression = new LinearRegression();
		Double[] coefficients = new Double[2];
		Double[] x = new Double[] {0.0, 10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0};
		Double[] y = new Double[] {0.94, 0.96, 1.0, 1.05, 1.07, 1.09, 1.14, 1.17, 1.21, 1.24, 1.28};
		coefficients = LinearRegression.Solve(x, y);
		Double a1 = coefficients[1];
		Double a0 = coefficients[0];
*/

import java.util.*;

public class LinearRegression{

	public Double[] Solve(Double[] x, Double[] y){

		Double[] coefficients = new Double[2];

		int nx = x.length;
		int ny = y.length;

		double Sx = 0, Sy = 0, Sxy = 0, Sxx = 0;

		System.out.println(nx);
		System.out.println(ny);

		if (nx != ny){
			System.out.println("Error. The number of elements in x must be the same as in y.");
		}
		else {
			// get sum of x vectors
			for (Double x_element : x){
				Sx += x_element;
			}
			// get sum of y vectors
			for (Double y_element: y){
				Sy += y_element;
			}
			// get sum of of x vector * y vector
			for (int i=0; i < nx; i++){
				Sxy += x[i] * y[i];
			}
			// get sum of x vector * x vector
			for (Double x_element : x){
				Sxx += x_element * x_element;
			}

			coefficients[0] = (Sxx * Sy - Sxy * Sx)/(nx * Sxx - Sx*Sx);
			coefficients[1] = (nx * Sxy - Sx*Sy)/(nx * Sxx - Sx * Sx);			
		}
		return coefficients;

	}
}