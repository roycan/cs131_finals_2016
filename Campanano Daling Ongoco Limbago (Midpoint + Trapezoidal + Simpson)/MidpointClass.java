import org.mariuszgromada.math.mxparser.*;

public class MidpointClass{
	public static double[][] Midpoint(String userFunction, double lowerBound, double upperBound, int numberOfIntervals){
		double[] xArray = new double[numberOfIntervals];
		double[] yArray = new double[numberOfIntervals];
		double stepSize = ((upperBound - lowerBound)/numberOfIntervals);
		double xCurrent = lowerBound;
		double midpointSum = 0;

		for (int ctr = 0; ctr < xArray.length; ctr++){
			xArray[ctr] = xCurrent + (stepSize/2);
			Argument x = new Argument("x", xArray[ctr]);
      		Expression userExpression = new Expression(userFunction, x);
			yArray[ctr] = userExpression.calculate(); //evaluate function here
			xCurrent = xCurrent + stepSize;
		}
		for (int ctr = 0; ctr < yArray.length; ctr++){
			midpointSum = midpointSum + yArray[ctr];
		}
		midpointSum = midpointSum * stepSize;

		double[][] returnVariable = {{midpointSum},xArray,yArray};
		return returnVariable;
	}
}
