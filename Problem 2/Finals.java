/**
 * CS 131 Finals
 * Faith Therese Pena
 * Gerard Arel Latoga
 * 
 *
 * To run:
 * $ javac -cp ":mxparser.jar" Finals.java RK4Class.java Graph.java
 * $ java -cp ":mxparser.jar" Finals
 * 
 * Results are stored in text.csv
 * 
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.awt.*;
import javax.swing.*;

public class Finals {
    public static void main(String[] args) {
        RK4Class rk = new RK4Class();
        Double a = 0d;
        Double b = 18d;
        Double h = 0.25d;
        Double x1 = Math.PI/2d;
        Double y1 = 0d;
        Double [][] answers = rk.RK4("y", "-(0.16/0.5)*y - (9.81/1.2)*sin(x)", a, b, h, x1, y1);      

        Double[] t_values = answers[0];
        Double[] x_values = answers[1];
        Double[] y_values = answers[2];
        
        Frame frm = new Graph(t_values, x_values);
        frm.show();

        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter("text.csv");
            bw = new BufferedWriter(fw);

            bw.write("t,x,y" + '\n');

            for (int i = 0; i < t_values.length; i++) {
                bw.write(t_values[i] + ", " + x_values[i] + ", " + y_values[i]+ '\n');
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                bw.close();
                fw.close();
            }
            catch (IOException f) {
            }
        }
    }
}

