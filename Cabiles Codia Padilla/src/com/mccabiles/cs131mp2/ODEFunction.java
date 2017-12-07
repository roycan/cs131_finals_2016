package com.mccabiles.cs131mp2;


/*
* ==================================
* FUNCTION CLASS
* ----------------------------------
* USAGE:
*		Instantiate your class that implements the f(t,y) method with your ODE.
* ==================================
*/

public interface ODEFunction {
	
/*
 * As an example, this method calculates the angular displacement of a pendulum in terms of time t.
 * REPLACE the contents to fit your needs.
 */
	public  double f ( double t, double y );
	
}
