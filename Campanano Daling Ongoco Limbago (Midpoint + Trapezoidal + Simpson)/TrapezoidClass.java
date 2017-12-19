import org.mariuszgromada.math.mxparser.*;

public class TrapezoidClass{
	public static double[][] Trapezoid(String userFunction, double lowerBound, double upperBound, int numberOfIntervals){
		double[] xArray = new double[numberOfIntervals+1];
		double[] yArray = new double[numberOfIntervals+1];
		double stepSize = ((upperBound - lowerBound)/numberOfIntervals);
		double xCurrent = lowerBound;
		double trapezoidSum = 0;

		for (int ctr = 0; ctr < xArray.length; ctr++){
			xArray[ctr] = xCurrent;
			Argument x = new Argument("x", xArray[ctr]);
      		Expression userExpression = new Expression(userFunction, x);
			yArray[ctr] = userExpression.calculate();
			xCurrent = xCurrent + stepSize;
		}

		for (int ctr = 1; ctr < numberOfIntervals; ctr++){
			trapezoidSum = trapezoidSum + 2*(yArray[ctr]);
		}
		trapezoidSum = (trapezoidSum + yArray[0] + yArray[numberOfIntervals])*(stepSize/2);

		double[][] returnVariable = {{trapezoidSum},xArray,yArray};
		return returnVariable;
	}
}
