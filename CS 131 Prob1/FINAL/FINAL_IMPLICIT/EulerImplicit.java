/*
* Copyright (c) 2007 Free Software Foundation, Inc. All
rights reserved.
*/

import java.io.File;
import javax.swing.*;
import org.math.plot.*;
import rootFind.NewtonRoot;

public class EulerImplicit {
   public static void main(String[] args) {
		double myX0 = 0, myXN = 0.5, myStepSize = 0.002, myEulerIters;
		int myTIdx, myNewtonIters;
		myEulerIters = (myXN - myX0)/myStepSize;
		myNewtonIters = 250;

		double[] myN = new double[251]; //dependent variable
    double[] myT = new double[251]; //independent variable

		myN[1] = 2000; myT[1] = myX0;

    for(myTIdx = 1; myTIdx < myEulerIters ; myTIdx++ ) {
    	  myT[myTIdx + 1] = myT[myTIdx] + myStepSize;

        Double myFuncComponent1 = -(10 * myN[1] * ( 1-Math.exp(-3*(myT[myTIdx+1]))) * myStepSize) - myN[myTIdx];
        Double myFuncComponent2 = 0.8 * myStepSize;

        String myFunction = "x + x^(3/2) * " + myFuncComponent2 + " + " + myFuncComponent1;
        String myFuncDerivative = "1 + x^(1/2) * (3/2) * " + myFuncComponent2;

        myN[myTIdx+1] = NewtonRoot.NewtonRoot(myFunction, myFuncDerivative, myN[myTIdx], 0.001, myNewtonIters);
		}


		for (myTIdx = 1; myTIdx< myEulerIters+1; myTIdx++) {
  		System.out.print("n[" + myTIdx + "]\t" + myN[myTIdx] + "\n") 	;
  		System.out.print("t[" + myTIdx + "]\t" + myT[myTIdx] + "\n") 	;
  		System.out.print("***************************\n");
		}

		Plot2DPanel panel = new Plot2DPanel();
		panel.addLinePlot("Line", myT, myN);

		JFrame frame= new JFrame("Graph n vs t");
		frame.setContentPane(panel);
		frame.setSize(500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	} // End of for( main )
} // End of for( EulerImplicit )
