package com.mccabiles.cs131mp2;

import com.mccabiles.cs131mp2.Euler;

/*
 * ==================================
 * MAIN FUNCTION
 * This serves as a template or guide for the use of the library.
 * 
 * USAGE: 
 * 1. Define your ODE by implementing the ODEFunction interface and overriding its f( t, y ) method.
 * 2. Set initial values and instantiate your ODE object.
 * 3. Call the methods with the initial values as arguments.
 * 
 * METHOD CALLS:
 * 		> Euler.odeEuler( t, y, h, n, function )
 * 		> Heun.odeHeun( t, y, h, n, function )
 * 
 * 		ARGUMENTS:
 * 			t = initial value of t
 * 			y = initial value of y
 * 			h = step size
 * 			n = number of iterations
 * 			function = ODE object
 * ==================================
 */

class myODEFunction implements ODEFunction {
	
	@Override
	public double f(double t, double y) 
	{
		double g = 32.2;
		double l = 2;
		double c = Math.sqrt( g / l );
		double y0 = Math.PI / 4; 
		return -y0 * c * Math.sin( c * t );
	}
}


public class Main {
	
	public static void main(String[] args) {

	// Set the Initial Values here:
		double t0 = 0;
		double y0 = 1;
		double h = 0.1;
		int n = 4;
		myODEFunction function = new myODEFunction();
		
		System.out.println("Euler: ");
		Euler.odeEuler(t0, y0, h, n, function);
		
		System.out.println("Heun: ");
		Heun.odeHeun(t0, y0, h, n, function);
		
	}

}
