package final_implicit;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by angeloarenas on 21/02/2017.
 */

public class NewtonRootTest {
    @Test
    public void testNewtonPolyExample() {
        double solEst = 1;
        double maxErr = Math.pow(10,-6);
        int iterMax = 1000;
        String func = "x^11 - 11";
        String funcDer = "11 * x^10";

        double expected = 1.2435752279123709;
        double actual = NewtonRoot.doNewtonRoot(func, funcDer, solEst, maxErr, iterMax);

        assertEquals("Root finder must pass given problem", expected, actual, maxErr);
    }
    
    @Test
    public void testNewtonLogExample() {
        double solEst = 1;
        double maxErr = Math.pow(10,-6);
        int iterMax = 1000;
        String func = "x^2 - ln(x+e)";
        String funcDer = "2*x - 1/(x+e)";

        double expected = 1.1647372994847358;
        double actual = NewtonRoot.doNewtonRoot(func, funcDer, solEst, maxErr, iterMax);

        assertEquals("Root finder must pass given problem", expected, actual, maxErr);
    }
    
    @Test
    public void testNewtonTrigoExample() {
        double solEst = 1;
        double maxErr = Math.pow(10,-6);
        int iterMax = 1000;
        String func = "3*(e^(5*x))*sin(-7*x)";
        String funcDer = "3*5*(e^(5*x))*sin(-7*x) - 7*3*(e^(5*x))*cos(-7*x)";

        double expected = 0.8975979010256554;
        double actual = NewtonRoot.doNewtonRoot(func, funcDer, solEst, maxErr, iterMax);

        assertEquals("Root finder must pass given problem", expected, actual, maxErr);
    }
    
    @Test
    public void testNewtonGivenExample() {
        double solEst = 1;
        double maxErr = Math.pow(10,-9);
        int iterMax = 10;
        double firstNum = 0;
        double stepSize = 0.002;
        double n1 = 2000;
        double t2 = firstNum + stepSize;
        double funComponent1 = -(10 * 2000 * ( 1-Math.exp(-3*(t2))) * stepSize) - n1;
        double funComponent2 = 0.8 * stepSize;
        String func = "x + (x^(3/2)) * " + funComponent2 + " + " + funComponent1;
        String funcDer = "1 + (x^(1/2)) * (3/2) * " + funComponent2;

        double expected = 1870.774364300735669531;
        double actual = NewtonRoot.doNewtonRoot(func, funcDer, solEst, maxErr, iterMax);

        assertEquals("Root finder must pass given problem", expected, actual, maxErr);
    }
}