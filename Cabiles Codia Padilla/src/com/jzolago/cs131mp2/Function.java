package com.jzolago.cs131mp2;

public abstract class Function {

	public static double f ( double t, double y ) {
		double g = 32.2;
		double l = 2;
		double c = Math.sqrt( g / l );
		double y0 = Math.PI / 4; 
		return -y0 * c * Math.sin( c * t );
	}
	
	
}
