package com.jcjcjp.cs131mp2;

/*
* ==================================
* RK4 CLASS
*	Solves ODE using Runge-Kutta method.
* ---------------------------------
* USAGE:
*		> Heun.odeSystem( t0, y0, h, n, f )
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
public class RK4 {
	
	public static double[][] odeSystem( double t0, double[] y0, double h, int n, ODEFunction function )
	{
		double[][] values = new double[n][2];
		double[] k1, k2, k3, k4;
		double[] new_y;
		double new_t;
		double old_t = t0;
		double[] old_y = y0;
		
		for ( int i = 0; i < n; i++ )
		{

			System.out.printf( "%d t: %.2f y: %.6f \n", i, old_t, old_y[0]);
			
		/*
		 * This part implements the actual algorithm: 
		 * 		k1 = f( t0, y0 )
		 * 		k2 = f( t0 + h/2, y0 + h(k1/2) )
		 * 		k3 = f( t0 + h/2, y0 + h(k2/2) )
		 * 		k4 = f( t0 + h, y0 + hk3 )
		 * 		y = y0 + h/6 ( k1 + 2k2 + 2k3 + k4 )
		 */
			
			k1 = function.f( old_t, old_y );
			k2 = function.f( old_t + (h/2), ODEFunction.add( old_y, ODEFunction.mult(k1, h/2)) );
			k3 = function.f( old_t + (h/2), ODEFunction.add( old_y, ODEFunction.mult(k2, h/2)) );
			k4 = function.f( old_t + h, ODEFunction.add( old_y, ODEFunction.mult(k1, h)) );
			
			k2 = ODEFunction.mult( k2, 2 );
			k3 = ODEFunction.mult( k3, 2 );
			
			double[][] k = { k1, k2, k3, k4 };
			new_y = ODEFunction.add(
						old_y ,
						ODEFunction.mult( 
							ODEFunction.add(k),
							h/6 ));
			
			new_t = old_t + h;
			
			old_y = new_y;
			old_t = new_t;
			
		}
		
		return values;
	}
}
