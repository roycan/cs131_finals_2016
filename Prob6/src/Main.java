/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class Main {
    public static void main(String[] args){
        double l = 0.1;
        double h = l / 5;
        double ts = 293;
        double hc = 40;
        double P = 0.016;
        double epsilon = 0.4;
        double k = 240;
        double Ac = 0.000016;
        double beta = hc * P / (k * Ac);
        
        double[] T = new double[6]; 
        T[0] = 473;
        T[5] = 293;
        
        //used to compute for a common term
        double extra = - (2 + Math.pow(h,2) * beta);
        double extra2 = Math.pow(h,2) * beta * ts;
        
        double[][] A = new double[4][4];
        
        A[0][0] = extra;
        A[1][1] = extra;
        A[2][2] = extra;
        A[3][3] = extra;
        A[0][1] = 1;
        A[1][0] = 1;
        A[1][2] = 1;
        A[2][1] = 1;
        A[2][3] = 1;
        A[3][2] = 1;
        
        double[] B = new double[4];
        B[0] = -(extra2 + T[0]);
        B[1] = -extra2;
        B[2] = -extra2;
        B[3] = -(extra2 + T[5]);
        
        double[][][] outputs = LU.LUdecompCrout.LU(A);
        
        double[][] L = outputs[0];
        double[][] U = outputs[1];
        
        double[] Y = new double[4];
        Y[0] = B[0]/L[0][0];
        
        for(int i = 1; i < 4; i++){
            Y[i] = (B[i] - LU.Mult.Mult(L[i], Y))/L[i][i];
        }
        
        System.out.println(java.util.Arrays.toString(Y));
        
        double[] x = new double[4];
        x[3] = Y[3]/U[3][3];
        
        for(int i = 2; i >= 0; i--){
            x[i] = (Y[i] - LU.Mult.Mult(x,U[i])) / U[i][i];
        }
        
        for(int i = 1; i < 5; i++){
            T[i] = x[i-1];
        }
        System.out.println(java.util.Arrays.toString(x));
        System.out.println(java.util.Arrays.toString(T));
        
        
    }
}
