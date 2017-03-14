/*
 * Copyright (c) Department of Computer Science CS 131
 * Numerical Methods class. All rights reserved.
 */

/*
 * Created by Bautista, Besas, and Callado in 2016.
 */

package final_;

import org.mariuszgromada.math.mxparser.*;
import org.math.plot.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

/*
   -This code uses mxparser, jmathplot, and jmathio libraries.
   -The jar files are included in the folder
   -"jar xf <.jarfile>" to extract
   -This is the executable file
   */

public class driverEMF{

    public static void main(String args[]){

        Scanner scan = new Scanner(System.in);
        Function ode = new Function("ode(t, n) = -0.8*n^(3/2)+10*2000*(1-e^(-3*t))");
        EulerModifiedFunction answer = new EulerModifiedFunction();
        Plot2DPanel panel = new Plot2DPanel();
        JFrame frame = new JFrame("Graph n vs t");

        double firstNum = 0, secondNum = 0.5, stepSize = 0.002, yIni = 2000;
        double tValues[], nValues[];

        answer.odeModEuler(ode, firstNum, secondNum, stepSize, yIni);
        tValues = answer.getxValues();
        nValues = answer.getyValues();

        for(int i=0; i<250; i++){
            System.out.print("t[" + i + "] = " + tValues[i] + "	n[" + i + "] = " + nValues[i] + "\n");
        }

        panel.addLinePlot("Line", tValues, nValues);
        frame.setContentPane(panel);
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
