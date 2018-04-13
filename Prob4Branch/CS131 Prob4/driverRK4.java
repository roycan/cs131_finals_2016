/** Displays (in the terminal) and stores (in Sys2ODEsRK4.csv) the results of performing
  * the Runge-Kutta method in the 2 given ordinary differential equations defined in ODE1.java
  * and ODE2.java by calling Sys2ODEsRK4.java . Also, provides these results to Prob4GUI.java. 
  */	

import java.io.FileWriter;
import java.io.IOException;

public class driverRK4{
    private static Double theInitialTime, theFinalTime, theStepSize, theInitialX, theInitialY;
    private static Double[] theXValues, theYValues, theTimeValues;
     
    /**
     * Outputs the results of using the Runge-Kutta method on the given ordinary differential 
     * equations defined in ODE1.java and ODE2.java by calling the Runge-Kutta Solver found in
     * Sys2ODEsRK4.java. The results will be displayed on the terminal and at the same time, 
     * written and saved in the Sys2ODEsRK4.csv file.
     */ 

	public static void main(){

        double N = (theFinalTime-theInitialTime)/theStepSize;
		int theNumTimeIntervals = (int) N;
		
		theXValues = new Double[theNumTimeIntervals+2];
		theYValues = new Double[theNumTimeIntervals+2];
		theTimeValues = new Double[theNumTimeIntervals+2];
		
		theTimeValues[1] = theInitialTime;
		theTimeValues[theNumTimeIntervals] = theFinalTime;
		theXValues[1] = theInitialX;
		theYValues[1] = theInitialY;
		
		Sys2ODEsRK4.rungeKuttaSolver(theNumTimeIntervals, theStepSize, theXValues, theYValues, theTimeValues);
		FileWriter fw = null;

		try{
			fw = new FileWriter("Sys2ODEsRK4.csv");

			fw.append("iteration");
			fw.append(',');
			fw.append("x value");
			fw.append(',');
			fw.append("y value");
			fw.append('\n');
			for (int i=1;i<=theNumTimeIntervals+1;i++){
				fw.append(Double.toString(i));
				fw.append(',');
				fw.append(Double.toString(theTimeValues[i]));
				fw.append(',');
				fw.append(Double.toString(theXValues[i]));
				fw.append(',');
				fw.append(Double.toString(theYValues[i]));
				fw.append('\n');
				System.out.print(i);
				System.out.print(" t" + i + ": " + theTimeValues[i]);
				System.out.print(" x" + i + ": " + theXValues[i]);
				System.out.print(" y" + i + ": " + theYValues[i]);
				System.out.print("\n");
			}
		} catch(Exception e){
			e.printStackTrace();
		
		} finally {
			try{
				fw.flush();
				fw.close();
			} catch (IOException e){
				e.printStackTrace();
			}
		}
    }

    /* Sets the initial values for the parameters x, y, theInitialTime, theFinalTime and theStepSize based on the user's inputs */
    public void setXYTs(Double theInitialTime, Double theFinalTime, Double theStepSize, Double theInitialX, Double theInitialY){
        driverRK4.theInitialTime = theInitialTime;
        driverRK4.theFinalTime = theFinalTime;
        driverRK4.theStepSize = theStepSize;
        driverRK4.theInitialX = theInitialX;
        driverRK4.theInitialY = theInitialY;
        main();
    }
    
    /** Gets the array containing all the values of x obtained from 
      * each of the iterations made 
      */
    public Double[] getXValues(){
        return driverRK4.theXValues;
    }

    /** Gets the array containing all the values of y obtained from 
      *	each of the iterations made 
      */
    public Double[] getYValues(){
        return driverRK4.theYValues;
    }

    /** Gets the array containing all the time values calculated 
      *	given the initial time, final time and step size 
      */
    public Double[] getTimeValues(){
        return driverRK4.theTimeValues;
    }
}