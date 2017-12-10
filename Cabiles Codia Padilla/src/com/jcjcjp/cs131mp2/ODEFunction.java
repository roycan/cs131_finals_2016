package com.jcjcjp.cs131mp2;


/*
* ==================================
* FUNCTION INTERFACE
* ----------------------------------
* USAGE:
*		Extend this class then override the f(t,y) method with your own ODE.
* METHODS:
* 		ODEFunction.add( double[] a, double[] b )	// adds two vectors a and b
* 		ODEFunction.add( double[][] vectors ) 		// adds vectors in the array to each other
* 		ODEFunction.mult( double[] a, double c )	// multiplies vector a with a constant c
* ==================================
*/

public abstract class ODEFunction
{
	
	abstract double[] f( double t, double[] y );
	
	
	static double[] add( double[] a, double[] b )
	{
		double[] new_vector = new double[ a.length ];
		
		for( int i = 0; i < a.length; i++ )
			new_vector[i] = a[i] + b[i];
		
		return new_vector;
	}
	
	static double[] add( double[][] vectors )
	{
		double[] new_vector = new double[ vectors[0].length ];
		
		for( int i = 0; i < vectors.length; i++ )
			for( int j = 0; j < vectors[i].length ; j++ )
			new_vector[j] = vectors[i][j] + new_vector[j];
		
		return new_vector;
	}
	
	
	static double[] mult( double[] a, double c )
	{
		double[] new_vector = new double[ a.length ];
		for( int i = 0; i < a.length; i++ )
		{
			new_vector[i] = a[i] * c;
		}
		
		return new_vector;
	}
	
}
