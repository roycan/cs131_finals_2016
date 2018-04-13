// Redeña, Cornelio, Delos Reyes | CS131 1718A

import Jama.Matrix;

public class Eigenvalues {
	public static double[][] Eigen_iter(double[][] A) {

		double[][][] QRResult = QRFactorization.QR(A);

		Matrix Q = new Matrix(QRResult[0]);
		Matrix R = new Matrix(QRResult[1]);
		//new A
		Matrix new_A = R.times(Q);

		double[][] answer = new_A.getArrayCopy();

		return answer;
	}

	public static double[][] Eigen(double[][] A){

		for (int i=0; i<40; i++){
			A = Eigen_iter(A);
		}

		return A;
	}
}