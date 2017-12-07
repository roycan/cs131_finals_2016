package com.mccabiles.cs131mp2;


/*
* ==================================
* FUNCTION CLASS
*---------------------------------
* USAGE:
*		Change the f( t, y ) method to your ODE function in terms of t and y.
* ==================================
*/

public abstract class Function {
	
/*
 * As an example, this method calculates the angular displacement of a pendulum in terms of time t.
 * REPLACE the contents to fit your needs.
 */
	public static double f ( double t, double y ) {
		double g = 32.2;
		double l = 2;
		double c = Math.sqrt( g / l );
		double y0 = Math.PI / 4; 
		return -y0 * c * Math.sin( c * t );
	}
	
	
}
