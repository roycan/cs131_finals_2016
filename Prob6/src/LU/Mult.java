/*
 * Copyright (c) 2017 CS 131 IPSE NKIA. All rights reserved.
 */
package LU;

/**
 * Contains the array multiplication function
 * 
 * @author Faith Therese Pena - CS131 THR
 */
public class Mult {

	/**
	 * Returns the sum-product of two arrays A and B of the same length
	 * 
	 * @param A
	 *            First factor array
	 * @param B
	 *            Second factor array
	 */
	public static double Mult(double[] A, double[] B) {
		double sumProduct = 0;

		for (int i = 0; i < A.length; i++) {
			sumProduct += A[i] * B[i];
		}

		return sumProduct;
	}
}
