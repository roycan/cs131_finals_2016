package com.jcjcjp.cs131mp2;

/*
* ==================================
* EULER CLASS
* 	Solves ODE using Euler's method.
* ----------------------------------
* USAGE:
*		> Euler.odeEuler( t0, y0, h, n, f )
*
* ARGUMENTS:
*		t0 = initial t
*		y0 = initial y at t=t0
*		h = step size
*		n = number of iterations
*		f = ODEFunction object
* 
* RETURNS:
*	2D array of [t][y] values
* ==================================
*/

public abstract class Euler
{
	
	public static double[][] odeEuler( double t0, double y0, double hsize, int n, ODEFunction function )
	{
		
		double[][] values = new double[n][2];
		double f; 
		double new_y;
		double new_t;
		double old_t = t0;
		double old_y = y0;
		
		for ( int i = 0; i < n; i++ )
		{
			
			print( "y: " + Double.toString(old_y) );
			print( "t: " + Double.toString(old_t) );
			print( "\n" );
			
		/*
		 * This part implements the actual algorithm: 
		 *		y = y0  + ( h * f( t0, y0) )
		 */
			f = function.f( old_t, old_y );		
			new_y = old_y + (f * hsize);
			new_t = old_t + hsize;
			
			values[i][0] = new_t;
			values[i][1] = new_y;
	
			old_y = new_y;
			old_t = new_t;
			
		}
		
		return values;
	}
	
	private static void print( String string )
	{
		System.out.println( string );
	}
}
