package com.jcjcjp.cs131mp2;

import com.jcjcjp.cs131mp2.Euler;

/*
 * ==================================
 * MAIN FUNCTION
 * This serves as a template or guide for the use of the library.
 * ---------------------------------
 * USAGE: 
 * 1. Define your ODE by extending the ODEFunction class.
 * 2. Override the f( t, y ) method with the function you will use.
 * 3. Instantiate your ODE object.
 * 4. Call the methods with the initial values and the ODE object as arguments.
 * ---------------------------------
 * METHOD CALLS:
 * 		> Euler.odeEuler( t, y, h, n, function )
 * 		> Heun.odeHeun( t, y, h, n, function )
 * 
 * 		ARGUMENTS:
 * 			t = initial value of t
 * 			y = initial values of y vector
 * 			h = step size
 * 			n = number of iterations
 * 			function = ODE object
 * ==================================
 */
	
class myODEFunction extends ODEFunction
{
	// Define your function here:
	@Override
	public double[] f(double t, double[] y) 
	{
		double[] new_y = new double[2];
		double g = 32.2;
		double l = 2;
		
		new_y[0] = y[1];
		new_y[1] = (-g/l) * Math.sin( y[0] );
		return new_y;
	}
}


public class Main
{
	
	public static void main(String[] args) {

	// Set the initial conditions:
		double t0 = 0;
		
		double[] y0 = new double[2]; // Our initial condition is y = [ pi/4, 0 ]
		y0[0] = Math.PI /4;
		y0[1] = 0;
		
		double h = 0.05;
		int n = 33;
		
	// Instantiate your ODE:
		myODEFunction function = new myODEFunction();
	
	// Call the methods:	
		System.out.println("Euler: ");
		Euler.odeSystem(t0, y0, h, n, function);
		
		System.out.println("Heun: ");
		Heun.odeSystem(t0, y0, h, n, function);
		
		System.out.println("\n RK4: ");
		RK4.odeSystem(t0, y0, 0.01, 100, function);
		
	}

}
