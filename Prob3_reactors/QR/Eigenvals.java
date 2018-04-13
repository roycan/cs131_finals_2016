import java.io.*;
import Jama.Matrix;

public class Eigenvals{

	Matrix matrixA = null;
	Matrix matrixQ = null;
	Matrix matrixR = null;
	double threshold;

	/* Constructor with custom threshold */
	Eigenvals(double thresh){
		threshold = thresh;
	}
	Eigenvals(){
		threshold = 0.0000000001;
	}

	/* Simple function for printing matrices nicely */
	public void printMat(double mat[][]){
		int matSize = mat.length;
		for (int i=0; i<matSize; i++){
			for (int j=0; j<mat[i].length; j++){
				System.out.printf("%.8f", mat[i][j]);
				// System.out.print(mat[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	/*	Function to check if the lower triangle is zero (within the threshold)
	*	input:	mat[][] - lower triangular matrix
	*	output:	boolean - true if the lower triangular matrix is zeroed out
	*					- false otherwise
	*/
	public boolean check(double mat[][]){
		int row = mat.length;
		int col = 0;
		boolean status = true;
		if(row > 0){
			col = mat[0].length;
			

			for (int i=0; i<row; i++){
				for (int j=0; j<col; j++){
					if(j<i && !((mat[i][j] <= threshold) && (mat[i][j]>= -threshold))){
						status = false;
						break;
					}
				}
			}
		}
		else{
			status = false;
		}
		return status;

	}


	/*	Function to parse the QR.csv file and perform the QR algorithm to get the
	*	eigenvalues
	*/
	public void run(){
		BufferedReader input = null;
		double[][] tempQ = null;
		double[][] tempR = null;
		try{
			// Read input from QR.csv file (output from the QR solver)
			input = new BufferedReader(new FileReader("QR.csv"));
			String cLine = input.readLine();

			int currMat= 0;
			int currRow = 0;
			
			int matSize = 0;
			while(cLine != null){
				if(cLine.equals("R")){
					currMat = 1;
					currRow = 0;
				}
				if (!(cLine.equals("") || cLine.equals("Q") || cLine.equals("R"))) {
					String parseLine[] = cLine.split(",");

					if(tempQ == null && tempR == null){
						matSize = parseLine.length;
						tempQ = new double[parseLine.length][parseLine.length];
						tempR = new double[parseLine.length][parseLine.length];
					}

					for(int i=0; i<matSize; i++){
						try{
							if(currMat == 0){
								tempQ[currRow][i] = Double.parseDouble(parseLine[i]);
							}
							else{
								tempR[currRow][i] = Double.parseDouble(parseLine[i]);
							}
						}
						catch(Exception e){}
					}
					currRow++;
				}
				cLine = input.readLine();
			}

		}
		catch(Exception e){
			System.out.println("Error in reading QR.csv file");
			return;
		}

		try{
			matrixQ = new Matrix(tempQ);
			matrixR = new Matrix(tempR);
			matrixA = matrixQ.times(matrixR);

			while(check(matrixA.getArrayCopy()) != true){
				matrixA = matrixQ.transpose().times(matrixA.times(matrixQ));
				double[][][] results = QRFactorization.QR(matrixA.getArrayCopy());
				matrixQ = new Matrix(results[0]);
				matrixR = new Matrix(results[1]);
			}
		}
		catch(Exception e){
			System.out.println("Error in getting Eigenvalues");
		}
		
	}

	/*	Function to print the matrix in a file named Eigenvalues.csv
	*	input: mat[][] - matrix to be printed in the file, cols are comma separated
	*					rows are line break separated
	*/
	public void printToFile(double mat[][]){
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter("Eigenvalues.csv"));
			StringBuilder sb = new StringBuilder();

			sb.append("Eigenvalues\n");
			for(int i = 0; i < mat.length; i++) {
				for(int j = 0; j < mat[i].length; j++){
					sb.append(mat[i][j]);
					sb.append(",");
				}
				
				sb.append("\n");
			}
			
			br.write(sb.toString());
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*	Function to print the matrix in a file named Eigenvalues.csv
	*	output: double[][] - matrix of 1 col (vector) containing the eigenvalues
	*/
	public double[][] getEigenvals(){
		run();
		double[][] matA = matrixA.getArrayCopy();
		int row = matA.length;
		double[][] eigenvalues = new double[row][1];
		for(int i=0; i<row; i++){
			eigenvalues[i][0] = matA[i][i];
		}
		printToFile(eigenvalues);
		printMat(eigenvalues);
		return eigenvalues;

	}

	public static void main(String[] args){
		Eigenvals eigen = new Eigenvals();
		eigen.getEigenvals();
	}
}	
