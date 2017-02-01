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
    public static void main( String[] args ) {
        RK4Class rk = new RK4Class();
        Double first_t = 0d;
        Double last_t = 18d;
        Double incrementSize = 0.25d;
        Double initial_x = Math.PI/2d;
        Double initial_y = 0d;
        Double [][] answers = rk.RK4( "y", "-(0.16/0.5)*y - (9.81/1.2)*sin(x)", first_t, last_t, incrementSize, initial_x, initial_y );      

        Double[] t_values = answers[0];
        Double[] x_values = answers[1];
        Double[] y_values = answers[2];
        
        Frame frm = new Graph( t_values, x_values );
        frm.show();

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            fileWriter = new FileWriter( "text.csv" );
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write( "t,x,y" + '\n' );

            for ( int i = 0; i < t_values.length; i++ ) {
                bufferedWriter.write( t_values[i] + ", " + x_values[i] + ", " + y_values[i]+ '\n' );
            }
        }
        catch( IOException e ) {
            e.printStackTrace();
        }
        finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            }
            catch ( IOException f ) {
            }
        }
    } // End of main
}

