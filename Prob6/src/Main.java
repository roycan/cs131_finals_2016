/*
 * Copyright (c) 2014 Nokia Solutions and Networks. All rights reserved.
 */

/**
 * 
 * @author hp
 */

public class Main {
	public static final double l = 0.1;
	public static final double h = l / 5;
	public static final double ts = 293;
	public static final double hc = 40;
	public static final double P = 0.016;
	public static final double k = 240;
	public static final double Ac = 0.000016;
	public static final double beta = hc * P / (k * Ac);
	/** used to compute for a common term */
	public static final double initDiagonalA = -(2 + Math.pow(h, 2) * beta);
	public static final double initMatrixB = Math.pow(h, 2) * beta * ts;

	public static void main(String[] args) {
		double[] myMatrixT = new double[6];
		double[][] myMatrixA = new double[4][4];
		double[] myMatrixB = new double[4];
		double[] myMatrixY = new double[4];
		double[] myMatrixX = new double[4];
		double[][][] matrixLUOutput = new double[2][4][4];
		double[][] myLowerTriangle = new double[4][4];
		double[][] myUpperTriangle = new double[4][4];

		myMatrixT[0] = 473;
		myMatrixT[5] = 293;

		myMatrixA[0][0] = initDiagonalA;
		myMatrixA[1][1] = initDiagonalA;
		myMatrixA[2][2] = initDiagonalA;
		myMatrixA[3][3] = initDiagonalA;
		myMatrixA[0][1] = 1;
		myMatrixA[1][0] = 1;
		myMatrixA[1][2] = 1;
		myMatrixA[2][1] = 1;
		myMatrixA[2][3] = 1;
		myMatrixA[3][2] = 1;

		myMatrixB[0] = -(initMatrixB + myMatrixT[0]);
		myMatrixB[1] = -initMatrixB;
		myMatrixB[2] = -initMatrixB;
		myMatrixB[3] = -(initMatrixB + myMatrixT[5]);

		matrixLUOutput = LU.LUdecompCrout.LU(myMatrixA);
		myLowerTriangle = matrixLUOutput[0];
		myUpperTriangle = matrixLUOutput[1];

		myMatrixY[0] = myMatrixB[0] / myLowerTriangle[0][0];

		for (int i = 1; i < 4; i++) {
			myMatrixY[i] = (myMatrixB[i] - LU.Mult.Mult(myLowerTriangle[i], myMatrixY)) / myLowerTriangle[i][i];
		}

		System.out.println(java.util.Arrays.toString(myMatrixY));

		myMatrixX[3] = myMatrixY[3] / myUpperTriangle[3][3];

		for (int i = 2; i >= 0; i--) {
			myMatrixX[i] = (myMatrixY[i] - LU.Mult.Mult(myMatrixX, myUpperTriangle[i])) / myUpperTriangle[i][i];
		}

		for (int i = 1; i < 5; i++) {
			myMatrixT[i] = myMatrixX[i - 1];
		}
		System.out.println(java.util.Arrays.toString(myMatrixX));
		System.out.println(java.util.Arrays.toString(myMatrixT));

	}
}
