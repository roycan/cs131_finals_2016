/*
*
*	CS131 WFU-2
*	Borja, K., Branzuela, J.E., Chua, J.V., Gabriel, K.A.
*	12 December 2017
*	
*	Inverse.java
*	The java class computes the inverse of a matrix using the decomposed LU Matrices.
*	This also extends from Ms. Faith Therese Peña's LU library.
*	
*/

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


public class Inverse {

	double[][] matrixL = null;
	double[][] matrixU = null;
	double[][] mat = null;

	Inverse(){
		parseInput();
	}

	/* Simple function for printing matrices nicely */
	public void printMat(double mat[][]){
		int matSize = mat.length;
		for (int i=0; i<matSize; i++){
			for (int j=0; j<mat[i].length; j++){
				System.out.printf("%.5f", mat[i][j]);
				// System.out.print(mat[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	/*	Function to perform forward substitution
	*	input:	mat[][] - upper triangular matrix
	*			ans[][] - answer vector (2d array with 1 col)
	*	output:	double [][] - vector in (2d array with 1 col)
	*/
	public double[][] forwardsub(double mat[][], double ans[][]){
		int ansSize = ans.length;
		double[][] sol = new double[ansSize][1];

		for(int i=0; i<ansSize; i++){
			double curr = ans[i][0];
			for(int j=0; j<ansSize; j++){
				if(i==j){
					sol[i][0] = curr/mat[i][j];
				}
				else{
					curr-= mat[i][j]*sol[j][0];
				}
			}
		}
		return sol;
	}

	/*	Function to perform backward substitution
	*	input:	mat[][] - lower triangular matrix
	*			ans[][] - answer vector (2d array with 1 col)
	*	output:	double [][] - vector in (2d array with 1 col)
	*/
	public double[][] backsub(double mat[][], double ans[][]){
		int ansSize = ans.length;
		double[][] sol = new double[ansSize][1];

		for(int i=ansSize-1; i>=0; i--){
			double curr = ans[i][0];
			for(int j=ansSize-1; j>=0; j--){
				if(i==j){
					sol[i][0] = curr/mat[i][j];
				}
				else{
					curr-= mat[i][j]*sol[j][0];
				}
			}
		}
		return sol;
	}

	/*	Function to perform backward substitution
	*	input:	matL[][] - L matrix from LU decomposition
	*			matU[][] - U matrix from LU decomposition
	*/
	public double[][] LUinv(){
		int matSize = matrixL.length;
		double[][] matInverse = new double[matSize][matSize];

		for(int i=0; i<matSize; i++){
			// Initialise matI to identity vector
			double[][] matI = new double[matSize][1];
			matI[i][0] = 1;

			// Perform backward sub and forward sub to get the answer for col i
			double[][] tempR = backsub(matrixU,forwardsub(matrixL,matI));
			
			// Copy col i to matInverse matrix
			for(int j=0; j<matSize; j++){
				matInverse[j][i] = tempR[j][0];
			}
		}

		return matInverse;

	}

	public void parseInput(){
		BufferedReader input = null;
		int matSize=0;
		
		try{
			// Read input from LU.csv file (output from the LU solver)
			input = new BufferedReader(new FileReader("LU.csv"));
			String cLine = input.readLine();
			int currMat=0;
			int currRow = 0;
			while(cLine != null){

				if(cLine.equals("U")){
					currMat = 1;
					currRow = 0;
				}
				if (!(cLine.equals("") || cLine.equals("L") || cLine.equals("U"))) {
					String parseLine[] = cLine.split(",");

					if(matrixL == null && matrixU == null){
						matSize = parseLine.length;
						matrixL = new double[parseLine.length][parseLine.length];
						matrixU = new double[parseLine.length][parseLine.length];
					}


					for(int i=0; i<matSize; i++){
						try{
							if(currMat == 0){
								matrixL[currRow][i] = Double.parseDouble(parseLine[i]);
							}
							else{
								matrixU[currRow][i] = Double.parseDouble(parseLine[i]);
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
			System.out.println("Error in reading LU.csv file");
		}
		
	}

	/*	Function to print the output in a file named LU_Inverse.csv
	*	input:	mat[][] - inversed matrix
	*
	*/

	public void printToFile(double mat[][]){
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter("LU_Inverse.csv"));
			StringBuilder sb = new StringBuilder();

			sb.append("Inverse of Matrix\n");
			for(int i = 0; i < mat.length; i++) {
				for(int j = 0; j < mat[i].length; j++){
					sb.append(mat[i][j]);
					sb.append(",");
				}
				
				sb.append("\n");
			}
			
			br.write(sb.toString());
			br.close();
			printMat(mat);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*	Function solve the unknowns given Ax=B where A=LU
	*	output:	double[][] - matrix of 1 col containing the solution
	*
	*/
	public double[][] solve(){

		Scanner scanner;
		scanner = new Scanner(System.in);
		mat = new double[matrixL.length][1];

		/* Get matrix b (Ax = b) */
		for(int i=0; i<matrixL.length; i++){
			double temp;
			System.out.printf("Enter b[%d]: ",i);
			temp = scanner.nextDouble();
			mat[i][0] = temp;
		}

		/* Solve for te unknown */
		double tempy[][] = backsub(matrixU,forwardsub(matrixL, mat));
		System.out.println("Solution vector: ");
		printMat(tempy);
		return tempy;
	}

	public static void main(String[] args){

		Inverse inv = new Inverse();
		System.out.println("Matrix Inverse: ");
		inv.printToFile(inv.LUinv());

		inv.solve();

	}

}