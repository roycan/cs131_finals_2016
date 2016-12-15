import org.mariuszgromada.math.mxparser.*;

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
      Double[][] upperResult = Sys2ODEsRK2.calculate(dT, dw, T0, upperBound, 0.0, 0.1, 0.01);
    }

    public static void main(String[] args)
    {
      Prob5 prob5 = new Prob5();
      prob5.setProblemParameters();

    }
}
