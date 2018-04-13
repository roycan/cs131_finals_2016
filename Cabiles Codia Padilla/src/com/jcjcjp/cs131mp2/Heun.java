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
*		y0 = initial y vector at t=t0
*		h = step size
*		n = number of iterations
*		f = ODEFunction object
*
* RETURNS:
*	2D array of [t][y] values
* ==================================
*/

public abstract class Heun {

	public static double[][] odeSystem( double t0, double[] y0, double h, int n, ODEFunction function )
	{
		double[][] values = new double[n][2];
		double[] f;
		double[] k0, k1;
		double[] new_y;
		double new_t;
		double old_t = t0;
		double[] old_y = y0;
		
		for ( int i = 0; i < n; i++ )
		{

			System.out.printf( "%d t: %.2f y: %.6f \n", i, old_t, old_y[0]);
			
		/*
		 * This part implements the actual algorithm: 
		 * 		f = f(t0, y0)
		 * 		k0 = y0 + ( h * f )
		 * 		k1 = f( t0 + h, k0 )
		 *		y = y0  + ( (h/2) * ( f + k1 ) )
		 */
			
			f = function.f( old_t, old_y );
			k0 = ODEFunction.add( old_y, ODEFunction.mult(f, h) );
			k1 = function.f( old_t + h, k0);
			new_y = ODEFunction.add(
						old_y ,
						ODEFunction.mult( 
							ODEFunction.add(f, k1),
							h/2 ));
			
			new_t = old_t + h;
			
			old_y = new_y;
			old_t = new_t;
			
		}
		
		return values;
	}
	
}
