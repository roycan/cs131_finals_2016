import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.mariuszgromada.math.mxparser.*;
//Yao Faneallrich Li 201455331 12/5/2016 CS 131 THR
// added dependency on mxparser.jar, a library obtained from http://mathparser.org/


public class Sys2ODEsRK2 {
    private static final double DEFAULT_LOWER_BOUND = 0;
    private static final double DEFAULT_UPPER_BOUND = 0.1;
    private static final double DEFAULT_STEP_SIZE = 0.01;
  public static Double[][] calculate (String f, String g, Double y0, Double z0) {
      return calculate(f, g, y0, z0, DEFAULT_LOWER_BOUND, DEFAULT_UPPER_BOUND, DEFAULT_STEP_SIZE);

  }
  public static Double[][] calculate (String f, String g, Double yValue, Double zValue, Double lowerBound, Double upperBound, Double stepSize) {
      Double maxSteps = (upperBound-lowerBound)/stepSize;

  	Double[] tValueArray = new Double[maxSteps.intValue() + 1];
  	Double[] yValueArray = new Double[maxSteps.intValue() + 1];
  	Double[] zValueArray = new Double[maxSteps.intValue() + 1];

  	Double currentLowerBound = lowerBound;

  	tValueArray[0] = currentLowerBound;
    yValueArray[0] = yValue;
  	zValueArray[0] = zValue;

      for (int i = 1; i < maxSteps + 1; i++) {
        currentLowerBound += stepSize;
		
		Argument xCoefficient = new Argument("x", currentLowerBound);
		Argument yCoefficient = new Argument("y", yValue);
		Argument zCoefficient = new Argument("z", zValue);
		
		Expression functionKy1 = new Expression(f, xCoefficient, yCoefficient, zCoefficient);
		Expression functionKz1 = new Expression(g, xCoefficient, yCoefficient, zCoefficient);
		
		Double Ky1 = functionKy1.calculate();
		Double Kz1 = functionKz1.calculate();
		
		xCoefficient = new Argument("x", currentLowerBound + stepSize);
		yCoefficient = new Argument("y", yValue + Ky1 * stepSize);
		zCoefficient = new Argument("z", zValue + Kz1 * stepSize);
		
		Expression functionKy2 = new Expression(f, xCoefficient, yCoefficient, zCoefficient);
		Expression functionKz2 = new Expression(g, xCoefficient, yCoefficient, zCoefficient);
		
        yValue += (stepSize/2) * (Ky1+functionKy2.calculate());
        zValue += (stepSize/2) * (Kz1+functionKz2.calculate());

  	    tValueArray[i] = currentLowerBound;
        yValueArray[i] = yValue;
        zValueArray[i] = zValue;
      }

  	Double[][] result = {tValueArray, yValueArray, zValueArray};
  	return result;
  }

  public static String toCsvString (Double[] tValuesArray, Double[] yValuesArray, Double[] zValuesArray)
  {
    String returnString = new String();
    returnString += "iteration,x value,y value,z value\n";
    int length = Math.min(tValuesArray.length, Math.min(yValuesArray.length, zValuesArray.length));
    for (int i = 0; i < length; i++)
    {
      returnString += Double.toString(i) + ',' + Double.toString(tValuesArray[i]) + ',' + Double.toString(yValuesArray[i]) + ',' + Double.toString(zValuesArray[i]) + '\n';
    }
    return returnString;
  }

  public static void printCsv(Double[] tValues, Double[] yValues, Double[] zValues)
  {
    printCsv("Sys2ODEsRK2.csv", tValues, yValues, zValues);
  }

  public static void printCsv(String filename, Double[] tValuesArray, Double[] yValuesArray, Double[] zValuesArray)
  {
    FileWriter writer = null;
    try {
      writer = new FileWriter(filename);
      writer.append(toCsvString(tValuesArray, yValuesArray, zValuesArray));
    } catch(Exception e){
        e.printStackTrace();
    } finally {
        try{
            writer.flush();
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
  }

}
