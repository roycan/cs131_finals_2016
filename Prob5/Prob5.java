import org.mariuszgromada.math.mxparser.*;
import java.io.PrintWriter;
import java.io.IOException;

public class Prob5
{
    public Double length;         // L, the length of the fin
    public Double T0;             // T(0)
    public Double TL;             // T(L)
    public Double surroundTemp;   // Ts, the temperature of the surrounding air

    public Double heatCoeff;      // hc, the convective heat coefficient
    public Double perimeter;      // P, the perimeter bounding the cross-sectional area of the fin
    public Double epsilon;        // epsilon
    public Double stefanBoltzmann;// sigma SB, the Stefan-Boltzmann constant
    public Double k;              // k
    public Double crossArea;      // Ac, the cross-sectional area of the fin

    public String dT;             // dT/dx
    public String dw;             // dw/dx

    public void setProblemParameters()
    {
        //problem parameters
        length = 1.0;
        T0 = 473.0;
        TL = 293.0;
        surroundTemp = 293.0;

        //parameters to use in eq. 17
        heatCoeff = 40.0;
        perimeter = 0.016;
        epsilon = 0.4;
        stefanBoltzmann = 5.67 * Math.pow(10, -8);
        k = 240.0;
        crossArea = 1.6 * Math.pow(10, -5);

        //eq 17, where y = T and z = w
        dT = "z";
        String convection_term = String.format("(%f * %f)/(%f * %f) * (y - %f)",
                                                heatCoeff, perimeter, k, crossArea, surroundTemp);
        String radiation_term = String.format("(%f * %f * %f)/(%f * %f) * ((y ^ 4) - (%f ^ 4))",
                                                epsilon, stefanBoltzmann, perimeter, k, crossArea, surroundTemp);
        dw = convection_term + "+" + radiation_term;
    }

    public void calculate(Double guess1, Double guess2)
    {
      Double lowerBound = guess1;
      Double upperBound = guess2;

      Double[][] lowerResult = Sys2ODEsRK2.calculate(dT, dw, T0, lowerBound, 0.0, 0.1, 0.01);
      Double[] lowerT = lowerResult[1];
      Double lowerTL = lowerT[lowerT.length - 1];

      Double[][] upperResult = Sys2ODEsRK2.calculate(dT, dw, T0, upperBound, 0.0, 0.1, 0.01);
      Double[] upperT = upperResult[1];
      Double upperTL = upperT[upperT.length - 1];

      Double slope = (upperTL - lowerTL)/(upperBound - lowerBound);

      System.out.println(lowerTL);
      System.out.println(upperTL);

      /* I assume that the line is linear, so I draw a line between the two points
       * Since secant tries to converge to 0 however, I have to subtract TL so that it will converge there instead. */
      Sec ng = new Sec(String.format("f(x) = %f * (x - %f) + %f - %f", slope, lowerBound, lowerTL, TL),
                        String.format("Xa = %f", lowerBound),
                        String.format("Xb = %f", upperBound),
                        String.format("err = %f", 0.000001),
                        String.format("imax = %d", 100000)
                        );
      Double newGuess = ng.Answer();
      Double[][] newResult = Sys2ODEsRK2.calculate(dT, dw, T0, newGuess, 0.0, 0.1, 0.01);
      Double[] newT = newResult[1];
      Double newTL = newT[newT.length - 1];

      Double[] xValues = newResult[0];

      String[] titles = {"x", "w(0) = -1000", "w(0) = -3500", String.format("w(0) = %f\n", newGuess)};
      Double[][] values = {xValues, lowerT, upperT, newT};
      printCSV("Prob5.csv", titles, values);
    }

    public static void printCSV(String filename, String[] titles, Double[][] values)
    {
      try
      {
        PrintWriter outputfile = new PrintWriter(filename, "UTF-8");
        for (int i = 0; i < titles.length; i++)
        {
          outputfile.print(titles[i]);
          outputfile.print(",");
        }
        outputfile.print("\n");
        for (int j = 0; j < values[0].length; j++)
        {
          for (int i = 0; i < values.length; i++)
          {
            outputfile.print(values[i][j]);
            outputfile.print(",");
          }
          outputfile.print("\n");
        }
        outputfile.close();
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
      prob5.calculate(-1000.0, -3500.0);
    }
}
