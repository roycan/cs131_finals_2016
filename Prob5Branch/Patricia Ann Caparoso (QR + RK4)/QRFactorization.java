/**
**	Caparoso, Patricia Ann G.
**	201318381
**	CS 131 THR
**	December 08, 2016
**
**	This program factors matrix A using the QR method
**/

public class QRFactorization
{

	public static void main(String[] args)
	{	
		String[] inputs = InputQR.inputs();
		inputs[0].replace("\n", "");
		int n = Integer.parseInt(inputs[0]);
		
		Double[][] A = new Double[n][n];
		Double[][] I = new Double[n][n];
		Double[][] H = new Double[n][n];
		Double[][] c = new Double[n][n];
		Double[][] v = new Double[n][n];
		Integer[] e = new Integer[n];
		Integer i=0, j=0;

		String input = inputs[1];
		input = input.replace("[", "");
		input = input.replace("]", "");

		String[] in = input.split("; ");
		for (String num : in){
			String[] nums = num.split(" ");
			j=0;
			for (String numm : nums){
				A[i][j] = Double.parseDouble(numm);
				j++;
			}
			i++;
		}

		for (i=0; i<n; i++){
			for (j=0; j<n; j++){
				if (i==j) I[i][j] = 1.0;
				else I[i][j] = 0.0;
			}
		}

		Double[][] R = A.clone();
		Double[][] Q = I.clone();
		Double clength = 0.0;

		for (i=0; i<n-1; i++){
			c = getC(R,i);
			e = getE(c, i);
			clength = Math.sqrt(MMult(trans(c, n), c, n)[0][0]);
			v = getV(c,clength,e);
			H = getHouseHolder(I, v);
			Q = MMult(Q,H,n);
			R = MMult(H,R,n);

			System.out.print("Q: ");
			prettyprint(Q);
			System.out.print("R: ");
			prettyprint(R);
		}
	}

	public static Double[][] getHouseHolder(Double[][] A, Double[][] v){
		Double[][] result = A.clone();
		Double[][] mat = MMult(trans(v, A.length), v, A.length);
		Double[][] mat2 = MMult(v, trans(v, A.length), A.length);
		for (int i=0; i<A.length; i++){
			for (int j=0; j<A.length ; j++){
				if (mat[i][j] == 0) mat[i][j] = 0.0;
				else mat[i][j] = 2/mat[i][j];
			}
		}
		for (int i=0; i<A.length; i++){
			for (int j=0; j<A.length ; j++){
				mat2[i][j] *= mat[0][0];
			}
		}
		for (int i=0; i<A.length; i++){
			for (int j=0; j<A.length ; j++){
				result[i][j] -= mat2[i][j];
			}
		}
		return result;
	}

	public static Double[][] getV(Double[][] A, Double num, Integer[] B){
		Double[][] result = A.clone();
		for (int i=0; i<A.length; i++){
			for (int j=0; j<A.length ; j++){
				if (j!=0) result[i][j] = 0.0;
				else result[i][j] += num*B[i];
			}
		}
		return result;
	}

	public static Double[][] MMult(Double[][] A, Double[][] B, int n){
		Double[][] result = new Double[n][n];
		for (int i=0; i<n; i++){
			for (int j=0; j<n ; j++){
				result[i][j] = (double)0;
				for (int k=0; k<n; k++){
					result[i][j] += A[i][k]*B[k][j];
				}
			}
		}
		return result;
	}

	public static Double[][] trans(Double[][] A, int n){
		Double[][] result = new Double[n][n];
		for (int i=0; i<n; i++){
			for (int j=0; j<n; j++){
				result[i][j] = A[j][i];
			}
		}
		return result;
	}

	public static void prettyprint(Double[][] A){
		String res = new String();
		for (Double[] a:A){
			for (Double aa:a){
				res += Double.toString(aa) + " ";
			}
			res += "\n";
		}
		System.out.println(res);
	}

	public static Double[][] getC(Double[][] R, int k){
		Double[][] c = new Double[R.length][R.length];
		Integer i=0, j=0;
		for (Double[] cc : R){
			j=0;
			for (Double ccc : cc){
				if (ccc == cc[k]) c[i][j] = ccc;
				else c[i][j] = 0.0;
				j++;
			}
			i++;
		}
		return c;
	}

	public static Integer[] getE(Double[][] c, int k){
		Integer[] e = new Integer[c.length];
		for (int i=0; i<c.length; i++){
			e[i] = 0;
		}
		if (c[k][0] < 0) e[k] = -1;
		else e[k] = 1;
		return e;
	}
}
