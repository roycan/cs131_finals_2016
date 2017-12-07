package com.jzolago.cs131mp2;

import com.jzolago.cs131mp2.Euler;

public class Main{

	public static void main(String[] args) {
		
		double t0 = 0;
		double y0 = Math.PI / 4;
		double h = 0.05;
		int n = 33;
		
		Euler.odeEuler(t0, y0, h, n);
		
	}


}
