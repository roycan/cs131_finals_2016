/*
 * Copyright (c) 2014 Nokia Solutions and Networks. All rights reserved.
 */

/**
 * Created by Clare on 12/7/2016.
 */

package final_;

import org.mariuszgromada.math.mxparser.*;
import java.lang.*;

public class EulerFunction {
    double [] xValues;
    double [] yValues;

    public void setxValues(double [] answer){
        xValues = answer;
    }

    public void setyValues(double [] answer){
        yValues = answer;
    }

    public double [] getxValues(){
        return xValues;
    }

    public double [] getyValues(){
        return yValues;
    }

    public void odeEuler(Function ode, double a, double b, double h, double yIni){

        int n = ((int) Math.ceil((b-a)/h)) + 1;
        double [] xPoints = new double[n];
        double [] yPoints = new double[n];
        
        xPoints[0] = a;
        yPoints[0] = yIni;

        for (int i =0; i<(n-1); i++){
            xPoints[i+1] = xPoints[i] + h;
            yPoints[i+1] = yPoints[i] + (ode.calculate(xPoints[i], yPoints[i])*h);
        }

        setxValues(xPoints);
        setyValues(yPoints);

    }

}
