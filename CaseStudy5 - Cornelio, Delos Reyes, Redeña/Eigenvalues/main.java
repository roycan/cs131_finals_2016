/* Faith Therese Pena
   CS131 THR
*/

import java.io.*;
import javax.swing.*;   
import java.util.Arrays;

/* Runs the QRFactorization function, MatrixInput, MatrixOutput, and the
creation of a .csv file containing the Q and R matrices */
public class main {

	public static int flag;
	public static void main(String[] args) {
		int row = Integer.parseInt(args[0]);
		int col = Integer.parseInt(args[1]);

		double[][] A = new double[row][col];

		SwingUtilities.invokeLater(new Runnable(){
	        public void run() {
	            MatrixInput.MatrixInput(row, col, A);
			}
		});

		while(main.flag == 0)
		{
			System.out.println("");
		}

		// double[][][] QRResult = QRFactorization.QR(A);

		double[][] QRResult = Eigenvalues.Eigen(A);

		MatrixOutput.MatrixOutput(row, col, QRResult);
		
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter("QR.csv"));
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < QRResult.length; i++) {
				if (i == 0) {sb.append("Q\n");}
				else {sb.append("R\n");}
				double[][] temp = QRResult;
				
				for(int j = 0; j < temp.length; j++){
					double[] temp1 = temp[j];
					
					for(int k = 0; k < temp1.length; k++){
						sb.append(temp1[k]);
						sb.append(",");
					}
					sb.append("\n");
				}
				sb.append("\n");
			}
			
			br.write(sb.toString());
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}