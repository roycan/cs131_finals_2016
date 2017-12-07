package com.mccabiles.cs131mp2;

import com.mccabiles.cs131mp2.Euler;

public class Main{

	public static void main(String[] args) {

		
	// Set the Initial Values here:
		double t0 = 0;
		double y0 = 1;
		double h = 0.1;
		int n = 4;
		
		System.out.println("Euler: ");
		Euler.odeEuler(t0, y0, h, n);
		
		System.out.println("Heun: ");
		Heun.odeHeun(t0, y0, h, n);
		
	}


}
