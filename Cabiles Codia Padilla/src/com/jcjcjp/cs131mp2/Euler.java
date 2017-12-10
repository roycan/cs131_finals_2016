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
*		y0 = initial y vector at t=t0
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

	public static double[][] odeSystem( double t0, double[] y0, double h, int n, ODEFunction function )
	{
		double[][] values = new double[n][2];
		double[] f; 
		double[] new_y;
		double[] old_y = y0;
		double new_t;
		double old_t = t0;
		
		for ( int i = 0; i < n; i++ )
		{

			System.out.printf( "%d t: %.2f y: %.6f \n", i, old_t, old_y[0]);

			// This part implements the actual algorithm:
			//	y = y0 + ( h * f(t0, y0) )
			f = function.f( old_t, old_y );
			new_y = ODEFunction.add( old_y, ODEFunction.mult( f, h ) );
			new_t = old_t + h;
			
			values[i][0] = new_t;
			values[i][1] = new_y[0];
			
			old_t = new_t;
			old_y = new_y;
		}
		
		return values;
	}
	
}
