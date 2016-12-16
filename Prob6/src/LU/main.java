package LU;

/* Faith Therese Pena
   CS131 THR
*/

import java.io.*;
import javax.swing.*;   
import java.util.Arrays;


/* Runs the LUDecompCrout function, MatrixInput, MatrixOutput, and the
creation of a .csv file containing the L and U matrices */
public class main {

	public static int flag;

	public static void main(String[] args) {
		main.flag = 0;
		
		int row = Integer.parseInt(args[0]);
	    int col = Integer.parseInt(args[1]);
	    int rowL, colL, rowU, colU;
	    double[][] A = new double[row][col];

		SwingUtilities.invokeLater(new Runnable(){
	        public void run() {
	            MatrixInput.MatrixInput(row, col, A);
			}

		});

		if (row < col) {
			rowL = row;
			colL = row;
			rowU = row;
			colU = col;
		}
		else if (row > col) {
			rowL = row;
			colL = col;
			rowU = col;
			colU = col;
		}
		else {
			rowL = row;
			colL = col;
			rowU = row;
			colU = col;
		}

		while(main.flag == 0)
		{
			System.out.println("");
		}

		double[][][] LUResult = LUdecompCrout.LU(A);

		MatrixOutput.MatrixOutput(rowL, colL, rowU, colU, LUResult[0], LUResult[1]);

		try {
			BufferedWriter br = new BufferedWriter(new FileWriter("LU.csv"));
			StringBuilder sb = new StringBuilder();

			
			for(int i = 0; i < LUResult.length; i++) {
				if (i == 0) {sb.append("L\n");}
				else {sb.append("U\n");}
				double[][] temp = LUResult[i];
				
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