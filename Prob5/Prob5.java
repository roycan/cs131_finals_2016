import org.mariuszgromada.math.mxparser.*;
import java.io.PrintWriter;
import java.io.IOException;

public class Prob5
{

    private final Double TEMPERATURE_AT_BASE = 473.0;
    private final Double TEMPERATURE_AT_TIP = 293.0;
    private final Double SURROUNDING_TEMP = 293.0;

    private final Double HEAT_COEFF = 40.0;
    private final Double PERIMETER = 0.016;
    private final Double EPSILON = 0.4;
    private final Double STEFAN_BOLTZMANN = 5.67 * Math.pow(10, -8);
    private final Double THERMAL_CONDUCTIVITY = 240.0;
    private final Double CROSS_AREA = 1.6 * Math.pow(10, -5);

    private String myDerivativeT;             // dt/dx
    private String myDerivativeW;             // dw/dx

    private void setProblemParameters()
    {
        //eq 17, where y = T and z = w
        myDerivativeT = "z";
        String convection_term = String.format("(%f * %f)/(%f * %f) * (y - %f)",
                                                HEAT_COEFF, PERIMETER, THERMAL_CONDUCTIVITY, CROSS_AREA, SURROUNDING_TEMP);
        String radiation_term = String.format("(%f * %f * %f)/(%f * %f) * ((y ^ 4) - (%f ^ 4))",
                                                EPSILON, STEFAN_BOLTZMANN, PERIMETER, THERMAL_CONDUCTIVITY, CROSS_AREA, SURROUNDING_TEMP);
        myDerivativeW = convection_term + "+" + radiation_term;
    }

    public void calculateTemperature(Double firstGuess, Double secondGuess)
    {
        Double lowerBound = firstGuess;
        Double upperBound = secondGuess;

        Double[][] lowerResult = Sys2ODEsRK2.calculate(myDerivativeT, myDerivativeW, TEMPERATURE_AT_BASE, lowerBound);
        Double[] lowerResult_topRow = lowerResult[1];
        Double lowerResult_topLeft = lowerResult_topRow[lowerResult_topRow.length - 1];

        Double[][] upperResult = Sys2ODEsRK2.calculate(myDerivativeT, myDerivativeW, TEMPERATURE_AT_BASE, upperBound);
        Double[] upperResult_topRow = upperResult[1];
        Double upperResult_topLeft = upperResult_topRow[upperResult_topRow.length - 1];

        Double slope = (upperResult_topLeft - lowerResult_topLeft)/(upperBound - lowerBound);

        System.out.println(lowerResult_topLeft);
        System.out.println(upperResult_topLeft);

        /* I assume that the line is linear, so I draw a line between the two points
        * Since secant tries to converge to 0 however, I have to subtract TEMPERATURE_AT_TIP so that it will converge there instead. */
        Sec secMethod = new Sec(String.format("f(x) = %f * (x - %f) + %f - %f", slope, lowerBound, lowerResult_topLeft, TEMPERATURE_AT_TIP),
                                String.format("Xa = %f", lowerBound),
                                String.format("Xb = %f", upperBound),
                                String.format("err = %f", 0.000001),
                                String.format("imax = %d", 100000)
                                );

        Double newGuess = secMethod.getRoot();

        Double[][] newResult = Sys2ODEsRK2.calculate(myDerivativeT, myDerivativeW, TEMPERATURE_AT_BASE, newGuess);
        Double[] newResult_topRow = newResult[1];
        Double newResult_topLeft = newResult_topRow[newResult_topRow.length - 1];

        Double[] xValues = newResult[0];
        String[] csv_titles = {"x", "w(0) = -1000", "w(0) = -3500", String.format("w(0) = %f\n", newGuess)};
        Double[][] csv_values = {xValues, lowerResult_topRow, upperResult_topRow, newResult_topRow};

        printCSV("Prob5.csv", csv_titles, csv_values);
    }

    public static void printCSV(String filename, String[] csv_titles, Double[][] csv_values)
    {
      try
      {
        PrintWriter outputFile = new PrintWriter(filename, "UTF-8");
        for (int i = 0; i < csv_titles.length; i++)
        {
          outputFile.print(csv_titles[i]);
          outputFile.print(",");
        }
        outputFile.print("\n");
        for (int j = 0; j < csv_values[0].length; j++)
        {
          for (int i = 0; i < csv_values.length; i++)
          {
            outputFile.print(csv_values[i][j]);
            outputFile.print(",");
          }
          outputFile.print("\n");
        }
        outputFile.close();
      }
      catch (IOException e)
      {
        System.out.println("Error writing to file " + filename + "!");
      }
    }

    public static void main(String[] args)
    {
      Prob5 prob5 = new Prob5();
      prob5.setProblemParameters();
      prob5.calculateTemperature(-1000.0, -3500.0);
    }
}
