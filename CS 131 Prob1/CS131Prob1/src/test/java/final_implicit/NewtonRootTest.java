package final_implicit;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by angeloarenas on 21/02/2017.
 */

public class NewtonRootTest {
    @Test
    public void testNewtonSimpleExample() {
        double solEst = 1;
        double maxErr = Math.pow(10,-15);
        int iterMax = 1000;
        String func = "(x^5) - 5";
        String funcDer = "5 * x^4";

        double expected = 1.379729661461215;
        double actual = NewtonRoot.doNewtonRoot(func, funcDer, solEst, maxErr, iterMax);

        assertEquals("Root finder must past simple example", expected, actual, maxErr);
    }
    
    @Test
    public void testNewtonAverageExample() {
        double solEst = 1;
        double maxErr = Math.pow(10,-15);
        int iterMax = 1000;
        String func = "2*x + sin(e^x)*(e^x)";
        String funcDer = "2 + sin(e^x)*(e^x) + (e^x)*(e^x)*cos(e^x)";

        double expected = 1.726637659686488;
        double actual = NewtonRoot.doNewtonRoot(func, funcDer, solEst, maxErr, iterMax);

        assertEquals("Root finder must past average example", expected, actual, maxErr);
    }
}