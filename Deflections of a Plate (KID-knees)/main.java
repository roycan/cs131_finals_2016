import java.util.Arrays;


public class main {
	public static void main(String[] args) {
		double h = 0.5;
		double l = 2;
		int row = (int) 3;
		int column = (int) 3;
		int length = (column*row);
		int j = 1, k = 1;
		double q = 33.6/18.32;//constant placeholder

		Node[] u = new Node[length];
		double[] b = new double[length];
		double[][] A = new double[length][length];

		for(int i=0; i<length; i++) {
			u[i] = new Node(j, k);
			k++;
			if(k>column) {
				k = 1;
				j++;
			}
		}

		double[] bounds = {0, 0, 0, 0};
		// bounds[0] top
		// bounds[1] bottom
		// bounds[2] left
		// bounds[3] right

		for(int i=0; i<length; i++) {
			//Setup b
			b[i] = q * h * h;

			if(u[i].get_i() == 1) {
				b[i] -= bounds[0];
			}

			else if(u[i].get_i() == row) {
				b[i] -= bounds[1];
			}

			if(u[i].get_j() == 1) {
				b[i] -= bounds[2];
			}

			else if(u[i].get_j() == column) {
				b[i] -= bounds[3];
			}

			//Setup A
			for(j=0; j<length; j++) {
				if(i == j) {
					A[i][i] = -4;
				}

				else if ((u[i].get_i()+1 == u[j].get_i() || u[i].get_i()-1 == u[j].get_i()) && u[i].get_j() == u[j].get_j()) {
					A[i][j] = 1;
				}

				else if ((u[i].get_j()+1 == u[j].get_j() || u[i].get_j()-1 == u[j].get_j()) && u[i].get_i() == u[j].get_i()) {
					A[i][j] = 1;
				}

				else {
					A[i][j] = 0;
				}
			}
		}
		for(int i=0; i<length; i++) {
			System.out.println(Arrays.toString(A[i]));
		}

		System.out.println(Arrays.toString(b));

		double[][][] LU = LUdecompCrout.LU(A);

		double[] s = ForwardSub.FS(LU[0], b);
		double[] ans = BackwardSub.BS(LU[1], s);

		//for(int i=0; i<length; i++) {
		//	System.out.println(Arrays.toString(LU[0][i]));
		//}

		//for(int i=0; i<length; i++) {
		//	System.out.println(Arrays.toString(LU[1][i]));
		//}

		System.out.println(Arrays.toString(ans));

		for(int i=0; i<length; i++) {
			//Setup b
			b[i] = ans[i] * h * h;
		}

		s = ForwardSub.FS(LU[0], b);
		ans = BackwardSub.BS(LU[1], s);

		System.out.println(Arrays.toString(ans));
	}
}