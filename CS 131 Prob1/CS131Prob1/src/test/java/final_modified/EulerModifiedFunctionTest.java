package final_modified;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
import org.mariuszgromada.math.mxparser.*;

/**
 * Created by angeloarenas on 21/02/2017.
 */
public class EulerModifiedFunctionTest {
    EulerModifiedFunction eulerModFunc = new EulerModifiedFunction();

    @Test
    public void testOdeModEulerSimpleExample() {
        double t0 = 0;
        double t1 = 1;
        double stepSize = 0.1;
        double y0 = 0;
        int n = ((int) Math.ceil((t1-t0)/stepSize)) + 1;
        Function ode = new Function("f(t,y)=t");

        double[] expected = new double[n];
        expected[0] =0;
        expected[1] = 0.005;
        expected[2] = 0.02;
        expected[3] = 0.045;
        expected[4] = 0.08;
        expected[5] = 0.125;
        expected[6] = 0.18;
        expected[7] = 0.245;
        expected[8] = 0.32;
        expected[9] = 0.405;
        expected[10] = 0.5;

        eulerModFunc.odeModEuler(ode, t0, t1, stepSize, y0);

        double[] actual = eulerModFunc.getyValues();

        assertArrayEquals("Ode modified must past simple example", expected, actual, 0.00001);
    }

    @Test
    public void testOdeModEulerAverageExample() {
        double t0 = 0;
        double t1 = 1;
        double stepSize = 0.1;
        double y0 = 0;
        int n = ((int) Math.ceil((t1-t0)/stepSize)) + 1;
        Function ode = new Function("f(t,y)=-t*e^(-y)");

        double[] expected = new double[n];
        expected[0] = 0.00000;
        expected[1] = -0.00501;
        expected[2] = -0.02020;
        expected[3] = -0.04604;
        expected[4] = -0.08338;
        expected[5] = -0.13353;
        expected[6] = -0.19845;
        expected[7] = -0.28104;
        expected[8] = -0.38566;
        expected[9] = -0.51919;
        expected[10] = -0.69315;

            eulerModFunc.odeModEuler(ode, t0, t1, stepSize, y0);

        double[] actual = eulerModFunc.getyValues();

        assertArrayEquals("Ode modified must past average example", expected, actual, 0.01);

    }

    @Test
    public void testOdeModEulerDifficultExample() {

        double t0 = 0;
        double t1 = 1;
        double stepSize = 0.1;
        double y0 = 0;
        int n = ((int) Math.ceil((t1-t0)/stepSize)) + 1;
        Function ode = new Function("f(t,y)=t");

        double[] expected = new double[n];
        expected[0] =0;
        expected[1] = 0.005;
        expected[2] = 0.02;
        expected[3] = 0.045;
        expected[4] = 0.08;
        expected[5] = 0.125;
        expected[6] = 0.18;
        expected[7] = 0.245;
        expected[8] = 0.32;
        expected[9] = 0.405;
        expected[10] = 0.5;

        eulerModFunc.odeModEuler(ode, t0, t1, stepSize, y0);

        double[] actual = eulerModFunc.getyValues();

        assertArrayEquals("Ode modified must past difficult example", expected, actual, 0.00001);
    }

}
