/*
 * Created by Clare on 12/8/2016.
 */

import java.lang.*;
import org.mariuszgromada.math.mxparser.*;

public class EulerModifiedFunction {

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

    public void odeModEuler(Function ode, double firstNum, double secondNum, double stepSize, double yIni){

        int n = ((int) Math.ceil((secondNum-firstNum)/stepSize)) + 1;
        double [] xPoints = new double[n];
        double [] yPoints = new double[n];
        xPoints[0] = firstNum;
        yPoints[0] = yIni;
        double slopeEu;
        double yEu;
        double slopeEnd;

        for (int i =0; i<(n-1); i++){
            xPoints[i+1] = xPoints[i] + stepSize;
            slopeEu = ode.calculate(xPoints[i], yPoints[i]);
            yEu = yPoints[i] + slopeEu*stepSize;
            slopeEnd = ode.calculate(xPoints[i+1], yEu);
            yPoints[i+1] = yPoints[i] + ((slopeEu+slopeEnd)*(stepSize/2.0));
        }

        setxValues(xPoints);
        setyValues(yPoints);

    }
}
