/******************************************************************************
 * This is the function file for RK4
 * place your function on the return value of this class method file
 * eg. return (- 1.2 * y + 7 * Math.exp(-0.3 * x));
 ******************************************************************************/

public class function {
	public static double f(double x, double y) {
      //return (- 1.2 * y + 7 * Math.exp(-0.3 * x));
	  return (-0.25/(300*0.003*900))*(0.0000000567*0.8*(Math.pow(y, 4)-Math.pow(298, 4))+(30*(y-298)));
   }
}