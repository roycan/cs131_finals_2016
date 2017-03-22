/*
 * Copyright (c) 2007 Free Software Foundation, Inc. All rights reserved.
 */

import java.io.File;
import javax.swing.*;
import org.math.plot.*;
import rootFind.NewtonRoot;

/*
   -This code uses jmathplot and jmathio libraries.
   -The jar files are included in the folder
   -"jar xf <.jarfile>" to extract
   */

public class EulerImplicit {
    public static void main(String[] args) {
        double firstNum = 0, lastNum = 0.5, stepSize = 0.002, eulerN;
        int i, newtonN;
        eulerN = (lastNum - firstNum)/stepSize; // number of iterations for euler
        newtonN = 250; // number of iterations for newton

        double[] n = new double[251];
        double[] t = new double[251];

        n[1] = 2000; t[1] = firstNum;

        for(i = 1; i < eulerN; i++) {
            t[i + 1] = t[i] + stepSize;

            Double fun_component1 = -(10 * 2000 * ( 1-Math.exp(-3*(t[i+1]))) * stepSize) - n[i];
            Double fun_component2 = 0.8 * stepSize;

            String fun = "x + x^(3/2) * " + fun_component2 + " + " + fun_component1;
            String funDer = "1 + x^(1/2) * (3/2) * " + fun_component2;

            n[i+1] = NewtonRoot.doNewtonRoot(fun, funDer, n[i], 0.001, newtonN);
        }

        for (i = 1; i < eulerN+1; i++) {
            System.out.print("n[" + i + "]\t" + n[i] + "\n");
            System.out.print("t[" + i + "]\t" + t[i] + "\n");
            System.out.print("***************************\n");
        }

        Plot2DPanel panel = new Plot2DPanel();
        panel.addLinePlot("Line", t, n);

        JFrame frame = new JFrame("Graph n vs t");
        frame.setContentPane(panel);
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
