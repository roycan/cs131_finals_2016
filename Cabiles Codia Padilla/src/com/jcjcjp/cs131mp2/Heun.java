package com.jcjcjp.cs131mp2;

/*
* ==================================
* HEUN CLASS
*	Solves ODE using Heun's method.
* ---------------------------------
* USAGE:
*		> Heun.odeHeun( t0, y0, h, n, f )
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

public abstract class Heun {

	public static double[][] odeHeun( double t0, double y0, double hsize, int n, ODEFunction function )
	{
		double[][] values = new double[n][2];
		double f;
		double k0, k1;
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
		 * 		k0 = y0 + ( h * f(t0, y0) )
		 * 		k1 = f( t0 + h, k0 )
		 *		y = y0  + ( (h/2) * ( f( t0, y0 ) + k1 ) )
		 */
			
			f = function.f( old_t, old_y );
			k0 = old_y + ( hsize * f );
			k1 = function.f( old_t + hsize, k0);
			
			new_y = old_y + ( (hsize / 2) * ( f + k1 ) );
			new_t = old_t + hsize;
			
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
