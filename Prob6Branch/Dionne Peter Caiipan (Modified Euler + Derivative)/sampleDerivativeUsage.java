/*
This is a sample usage of the derivative function.
*/
import java.util.Scanner;
import java.io.*;

public class sampleDerivativeUsage {
    public static void main (String args[]) {
        Scanner in = new Scanner(System.in);
        float x[], y[];
        String enter;

        // place the x array here
        x = new float[] {1,2,3,4,5};
        // place the y array here
        y = new float[] {2,4,6,8,10};


        derivative D = new derivative(x,y);
        printOutputGraphPlots(x, D.dx);
        makeCSV(x, D.dx);
    }

    public static void printOutputGraphPlots(float[] x, float[] y) {
        System.out.println("X\tY");
        int n = x.length;

        for(int i = 0; i < n; i++) {
            System.out.printf("%.2f\t%.2f\n", x[i], y[i]);
        }
    }

    public static void makeCSV(float[] x, float[] y) {
        int n = x.length;
        try{
            PrintWriter writer = new PrintWriter("DerivativeOutput.csv", "UTF-8");
            writer.println("X,Y");
            for(int i = 0; i < n; i++) {
                writer.printf("%.2f,%.2f\n", x[i], y[i]);
            }
            writer.close();
        } catch (IOException e) {
           // do something
        }
    }
}
