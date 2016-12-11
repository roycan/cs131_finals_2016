package clare.le;

/**
 * Created by Clare on 12/8/2016.
 */

import org.mariuszgromada.math.mxparser.*;
import java.lang.*;
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

    public void odeModEuler(Function ode, double a, double b, double h, double yIni){

        int n = ((int) Math.ceil((b-a)/h)) + 1;
        double [] xPoints = new double[n];
        double [] yPoints = new double[n];
        xPoints[0] = a;
        yPoints[0] = yIni;
        double slopeEu;
        double yEu;
        double slopeEnd;
        for (int i =0; i<(n-1); i++){
            xPoints[i+1] = xPoints[i] + h;
            slopeEu = ode.calculate(xPoints[i], yPoints[i]);
            yEu = yPoints[i] + slopeEu*h;
            slopeEnd = ode.calculate(xPoints[i+1], yEu);
            yPoints[i+1] = yPoints[i] + ((slopeEu+slopeEnd)*(h/2.0));
        }

        setxValues(xPoints);
        setyValues(yPoints);

    }
}
