import org.mariuszgromada.math.mxparser.*;

public class RichardsonClass{
	public static double[] Richardson(String equation, double a, double b, double stepSize){
		double firstRes, secondRes, stepSize2, approxTrueVal;
		int numberOfIntervals = (int)((b-a)/stepSize);
		int numberOfIntervals2;
		double[] returnValues = new double[3];
		firstRes = MidpointClass.Midpoint(equation,a,b,(numberOfIntervals))[0][0];
		numberOfIntervals2 = 2*numberOfIntervals;
		secondRes = MidpointClass.Midpoint(equation,a,b,(numberOfIntervals2))[0][0];
		approxTrueVal = secondRes + ((secondRes-firstRes)/3);
		returnValues[0] = approxTrueVal;

		firstRes = TrapezoidClass.Trapezoid(equation,a,b,(numberOfIntervals))[0][0];
		numberOfIntervals2 = 2*numberOfIntervals;
		secondRes = TrapezoidClass.Trapezoid(equation,a,b,(numberOfIntervals2))[0][0];
		approxTrueVal = secondRes + ((secondRes-firstRes)/3);
		returnValues[1] = approxTrueVal;

		firstRes = SimpsonsClass.Simpsons(equation,a,b,(numberOfIntervals))[0][0];
		numberOfIntervals2 = 2*numberOfIntervals;
		secondRes = SimpsonsClass.Simpsons(equation,a,b,(numberOfIntervals2))[0][0];
		approxTrueVal = secondRes + ((secondRes-firstRes)/3);
		returnValues[2] = approxTrueVal;

		return returnValues;
	}
}
