import org.mariuszgromada.math.mxparser.*;

public class SimpsonsClass{
	public static double[][] Simpsons(String userFunction, double lowerBound, double upperBound, int numberOfIntervals){
		double[] xArray = new double[numberOfIntervals+1];
		double[] yArray = new double[numberOfIntervals+1];
		double stepSize = ((upperBound - lowerBound)/numberOfIntervals);
		double xCurrent = lowerBound;
		double simpsonsSum = 0;

		for (int ctr = 0; ctr < xArray.length; ctr++){
			xArray[ctr] = xCurrent;
			Argument x = new Argument("x", xArray[ctr]);
      		Expression userExpression = new Expression(userFunction, x);
			yArray[ctr] = userExpression.calculate(); //evaluate function here
			xCurrent = xCurrent + stepSize;
		}
		//calculate using trapezoid sum
		for (int ctr = 1; ctr < numberOfIntervals; ctr+=2){
			simpsonsSum = simpsonsSum + 4*(yArray[ctr]);
		}
		for (int ctr = 2; ctr < numberOfIntervals; ctr+=2){
			simpsonsSum = simpsonsSum + 2*(yArray[ctr]);
		}
		simpsonsSum = (simpsonsSum + yArray[0] + yArray[numberOfIntervals])*(stepSize/3);

		double[][] returnVariable = {{simpsonsSum},xArray,yArray};
		return returnVariable;
	}
}
