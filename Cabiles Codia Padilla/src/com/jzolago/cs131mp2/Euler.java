package com.jzolago.cs131mp2;

// ==================================
// EULER CLASS
// - Solves ODE using Euler's method.
// - Modify as you see fit.
// ---------------------------------
// USAGE:
//	call method Euler.odeEuler( t0, y0, h, n ) where:
//		t0 = initial t
//		y0 = initial y at t=t0
//		h = step size
//		n = number of iterations
// ---------------------------------
// RETURNS:
//	2D array of [t][y] values
//==================================
public abstract class Euler {
	
	public static double[][] odeEuler( double t0, double y0, double hsize, int iterations ) {
		
		double[][] values = new double[iterations][2];
		double f; 
		double new_y;
		double new_t;
		double old_t = t0;
		double old_y = y0;
		
		for( int i = 0; i < iterations; i++) {
			print( "y: " + Double.toString(old_y) );
			print( "t: " + Double.toString(old_t) );
			
			f = Function.f( old_t, old_y ) * hsize;
			
			new_y = old_y + ( f);
			new_t = old_t + hsize;
			
			values[i][0] = new_t;
			values[i][1] = new_y;
			
			old_y = new_y;
			old_t = new_t;
			
		}
		
		return values;
	}
	
	private static void print( String string ) {
		System.out.println( string );
	}
}
