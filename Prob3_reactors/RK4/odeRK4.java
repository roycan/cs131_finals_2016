/**
**	Borja, Kim R.
**	CS 131 WFY-2
**	December 09, 2017
**
**	This program solves a system of five first order ODEs using RK4 method. This is slightly patterned after Caparoso, Patricia Ann's RK4.
**/

import java.io.*;
import static java.lang.System.*;
import java.util.List;
import java.util.ArrayList;


public class odeRK4
{
	public static void main(String[] args)
	{	
		//Get the inputs with the order x-values, h, y-value
		double[] inputs = InputRK4.inputs(); 

		double[] temp = new double[6];

		List<double[]> A = new ArrayList<double[]>();
				
		double h = inputs[1];
		temp[0] = inputs[0]; temp[1] = inputs[2]; temp[2] = inputs[3]; temp[3] = inputs[4]; temp[4] = inputs[5]; temp[5] = inputs[6];

		A.add(new double[] { inputs[0], inputs[2], inputs[3], inputs[4], inputs[5], inputs[6] });
		double t_old, c1_old=0, c2_old=0, c3_old=0, c4_old=0, c5_old=0, k1, k2, k3, k4;
		int row = 1;


		for (double i = h; i <= 100 ; i = i+h) {

			temp[0] = i;
			t_old = A.get(row-1)[0];
	
			//c1-------------------------------------------------------------------------------------------------------------------
			c1_old = A.get(row-1)[1];
	
			k1 = Function.ODE1(t_old, c1_old, c2_old, c3_old, c4_old, c5_old);
			k2 = Function.ODE1(t_old + (h/2), c1_old + k1*(h/2), c2_old + k1*(h/2), c3_old + k1*(h/2), c4_old + k1*(h/2), c5_old + k1*(h/2));
			k3 = Function.ODE1(t_old + (h/2), c1_old + k2*(h/2), c2_old + k2*(h/2), c3_old + k2*(h/2), c4_old + k2*(h/2), c5_old + k2*(h/2));
			k4 = Function.ODE1(t_old + (h/2), c1_old + k3*(h/2), c2_old + k3*(h/2), c3_old + k3*(h/2), c4_old + k3*(h/2), c5_old + k3*(h/2));

			temp[1] = c1_old + (h/6)*(k1 + (2*k2) + (2*k3) + k4);


			//c2-------------------------------------------------------------------------------------------------------------------
			c2_old = A.get(row-1)[2];
	
			k1 = Function.ODE2(t_old, c1_old, c2_old, c3_old, c4_old, c5_old);
			k2 = Function.ODE2(t_old + (h/2), c1_old + k1*(h/2), c2_old + k1*(h/2), c3_old + k1*(h/2), c4_old + k1*(h/2), c5_old + k1*(h/2));
			k3 = Function.ODE2(t_old + (h/2), c1_old + k2*(h/2), c2_old + k2*(h/2), c3_old + k2*(h/2), c4_old + k2*(h/2), c5_old + k2*(h/2));
			k4 = Function.ODE2(t_old + (h/2), c1_old + k3*(h/2), c2_old + k3*(h/2), c3_old + k3*(h/2), c4_old + k3*(h/2), c5_old + k3*(h/2));
			
			temp[2] = c2_old + (h/6)*(k1 + (2*k2) + (2*k3) + k4);


			//c3-------------------------------------------------------------------------------------------------------------------
			c3_old = A.get(row-1)[3];
	
			k1 = Function.ODE3(t_old, c1_old, c2_old, c3_old, c4_old, c5_old);
			k2 = Function.ODE3(t_old + (h/2), c1_old + k1*(h/2), c2_old + k1*(h/2), c3_old + k1*(h/2), c4_old + k1*(h/2), c5_old + k1*(h/2));
			k3 = Function.ODE3(t_old + (h/2), c1_old + k2*(h/2), c2_old + k2*(h/2), c3_old + k2*(h/2), c4_old + k2*(h/2), c5_old + k2*(h/2));
			k4 = Function.ODE3(t_old + (h/2), c1_old + k3*(h/2), c2_old + k3*(h/2), c3_old + k3*(h/2), c4_old + k3*(h/2), c5_old + k3*(h/2));
			
			temp[3] = c3_old + (h/6)*(k1 + (2*k2) + (2*k3) + k4);


			//c4-------------------------------------------------------------------------------------------------------------------
			c4_old = A.get(row-1)[4];
	
			k1 = Function.ODE4(t_old, c1_old, c2_old, c3_old, c4_old, c5_old);
			k2 = Function.ODE4(t_old + (h/2), c1_old + k1*(h/2), c2_old + k1*(h/2), c3_old + k1*(h/2), c4_old + k1*(h/2), c5_old + k1*(h/2));
			k3 = Function.ODE4(t_old + (h/2), c1_old + k2*(h/2), c2_old + k2*(h/2), c3_old + k2*(h/2), c4_old + k2*(h/2), c5_old + k2*(h/2));
			k4 = Function.ODE4(t_old + (h/2), c1_old + k3*(h/2), c2_old + k3*(h/2), c3_old + k3*(h/2), c4_old + k3*(h/2), c5_old + k3*(h/2));
			
			temp[4] = c4_old + (h/6)*(k1 + (2*k2) + (2*k3) + k4);


			//c5-------------------------------------------------------------------------------------------------------------------
			c5_old = A.get(row-1)[5];
	
			k1 = Function.ODE5(t_old, c1_old, c2_old, c3_old, c4_old, c5_old);
			k2 = Function.ODE5(t_old + (h/2), c1_old + k1*(h/2), c2_old + k1*(h/2), c3_old + k1*(h/2), c4_old + k1*(h/2), c5_old + k1*(h/2));
			k3 = Function.ODE5(t_old + (h/2), c1_old + k2*(h/2), c2_old + k2*(h/2), c3_old + k2*(h/2), c4_old + k2*(h/2), c5_old + k2*(h/2));
			k4 = Function.ODE5(t_old + (h/2), c1_old + k3*(h/2), c2_old + k3*(h/2), c3_old + k3*(h/2), c4_old + k3*(h/2), c5_old + k3*(h/2));
			
			temp[5] = c5_old + (h/6)*(k1 + (2*k2) + (2*k3) + k4);
			
			A.add(new double[] { temp[0], temp[1], temp[2], temp[3], temp[4], temp[5] });
			row++;
		}

		System.out.println(A.get(0)[0]);
		printArrayList(A);
		
	}








	
	public static void printArrayList(List<double[]> A){
		int listSize = A.size();
		int arraySize = 6;

		System.out.println("Size is " + listSize);

		
		

		try{
			FileWriter fw = new FileWriter("output.csv");
			PrintWriter pw = new PrintWriter(fw);

			pw.print("t,c1,c2,c3,c4,c5\n");

			for (int i = 0; i < listSize; i++) {
				for (int j = 0; j < arraySize; j++) {
					if (j==arraySize-1) {
						pw.print(A.get(i)[j]);
					} else {	
						pw.print(A.get(i)[j] + ",");
					}

				}
				pw.println();
			}
			pw.close();
		
		} catch (IOException e) {
			out.println("ERROR");
		}


		
		
		
		
	}

	



}
