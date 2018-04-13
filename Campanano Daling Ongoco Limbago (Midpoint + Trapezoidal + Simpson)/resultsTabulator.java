import java.io.*;

public class resultsTabulator{
	public static void Tabulate(String FILENAME, String equation, double a, double b, int[] numberOfIntervalsArray, int decimalPlaces) throws IOException,NumberFormatException{
		double result = 0;
		double dP = Math.pow(10,decimalPlaces);
		FILENAME = FILENAME + ".csv";
		PrintWriter filewriter = new PrintWriter(FILENAME);
		filewriter.write("Midpoint Results\n");
		filewriter.write("Step Size,Intervals,Result\n");
		for(int ctr = 0; ctr < numberOfIntervalsArray.length; ctr++){
			result = MidpointClass.Midpoint(equation,a,b,numberOfIntervalsArray[ctr])[0][0];
			result = Math.round(result*dP)/dP;
			filewriter.write(String.valueOf((b-a)/numberOfIntervalsArray[ctr]));
			filewriter.write(",");
			filewriter.write(String.valueOf(numberOfIntervalsArray[ctr]));
			filewriter.write(",");
			filewriter.write(""+result);
			filewriter.write("\n");
		}
		filewriter.write("\nTrapezoid Results\n");
		filewriter.write("Step Size,Intervals,Result\n");
		for(int ctr = 0; ctr < numberOfIntervalsArray.length; ctr++){
			result = TrapezoidClass.Trapezoid(equation,a,b,numberOfIntervalsArray[ctr])[0][0];
			result = Math.round(result*dP)/dP;
			filewriter.write(String.valueOf((b-a)/numberOfIntervalsArray[ctr]));
			filewriter.write(",");
			filewriter.write(String.valueOf(numberOfIntervalsArray[ctr]));
			filewriter.write(",");
			filewriter.write(""+result);
			filewriter.write("\n");
		}
		filewriter.write("\nSimpsons Results\n");
		filewriter.write("Step Size,Intervals,Result\n");
		for(int ctr = 0; ctr < numberOfIntervalsArray.length; ctr++){
			result = SimpsonsClass.Simpsons(equation,a,b,numberOfIntervalsArray[ctr])[0][0];
			result = Math.round(result*dP)/dP;
			filewriter.write(String.valueOf((b-a)/numberOfIntervalsArray[ctr]));
			filewriter.write(",");
			filewriter.write(String.valueOf(numberOfIntervalsArray[ctr]));
			filewriter.write(",");
			filewriter.write(""+result);
			filewriter.write("\n");
		}
		filewriter.close();
	}
}
