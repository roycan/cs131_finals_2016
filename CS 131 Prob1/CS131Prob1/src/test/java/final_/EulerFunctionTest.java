
package final_;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
import org.mariuszgromada.math.mxparser.*;

/**
 * Created by jerryleegeronimo on 21/02/2017.
 */
public class EulerFunctionTest {
    EulerFunction eulerFunc = new EulerFunction();

    @Test
    public void testOdeEulerSimpleExample() {
        double t0 = 0;
        double t1 = 1;
        double stepSize = 0.01;
        double y0 = 0;
        int n = ((int) Math.ceil((t1-t0)/stepSize)) + 1;
        Function ode = new Function("f(t,y)=t");

        double[] expected = new double[11];
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

        eulerFunc.odeEuler(ode, t0, t1, stepSize, y0);

        double[] result = eulerFunc.getyValues();
        double[] actual = new double[11];
        for (int i = 0; i <= 10; i ++) {
            actual[i] =  result[10*i];
        }

        assertArrayEquals("Ode modified must past simple example", expected, actual, 0.01);
    }

    @Test
    public void testOdeEulerAverageExample() {
        double t0 = 0;
        double t1 = 1;
        double stepSize = 0.001;
        double y0 = 0;
        int n = ((int) Math.ceil((t1-t0)/stepSize)) + 1;
        Function ode = new Function("f(t,y)=-t*e^(-y)");

        double[] expected = new double[11];
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

        eulerFunc.odeEuler(ode, t0, t1, stepSize, y0);

        double[] result = eulerFunc.getyValues();
        double[] actual = new double[11];
        for (int i = 0; i <= 10; i ++) {
            actual[i] =  result[100*i];
        }

        assertArrayEquals("Ode modified must past average example", expected, actual, 0.01);

    }

    @Test
    public void testOdeEulerDifficultExample() {

        double t0 = 0;
        double t1 = 0.5;
        double stepSize = 0.002;
        double y0 = 2000;
        int n = ((int) Math.ceil((t1-t0)/stepSize)) + 1;
        Function ode = new Function("f(t,y)=-0.8*y^(3/2) + 10*2000*(1-e^(-3*t))");

        double[] expected = {
            2000.00, 1864.34, 1742.26, 1632.05, 1532.26, 1441.64, 1359.14, 1283.85, 1214.98, 1151.86, 1093.90, 1040.59, 991.46, 946.12,
            904.22, 865.46, 829.55, 796.25, 765.34, 736.62, 709.93, 685.09, 661.97, 640.44, 620.38, 601.69, 584.27, 568.02, 552.88, 538.77, 525.62,
            513.37, 501.96, 491.34, 481.46, 472.28, 463.76, 455.85, 448.53, 441.75, 435.50, 429.73, 424.43, 419.56, 415.11, 411.05, 407.37, 404.03,
            401.03, 398.35, 395.96, 393.86, 392.03, 390.46, 389.13, 388.03, 387.15, 386.48, 386.01, 385.72, 385.61, 385.67, 385.89, 386.27, 386.78,
            387.43, 388.21, 389.11, 390.13, 391.25, 392.48, 393.81, 395.22, 396.72, 398.31, 399.97, 401.70, 403.50, 405.36, 407.28, 409.26, 411.28,
            413.36, 415.48, 417.64, 419.84, 422.07, 424.33, 426.63, 428.95, 431.30, 433.67, 436.06, 438.47, 440.89, 443.33, 445.78, 448.24, 450.71,
            453.18, 455.66, 458.15, 460.64, 463.13, 465.63, 468.12, 470.61, 473.10, 475.58, 478.06, 480.53, 483.00, 485.46, 487.92, 490.37, 492.80,
            495.23, 497.65, 500.06, 502.46, 504.84, 507.22, 509.58, 511.93, 514.27, 516.60, 518.91, 521.21, 523.49, 525.76, 528.02, 530.26, 532.49,
            534.71, 536.91, 539.10, 541.27, 543.42, 545.56, 547.69, 549.80, 551.90, 553.98, 556.05, 558.10, 560.14, 562.17, 564.17, 566.17, 568.15,
            570.11, 572.06, 574.00, 575.92, 577.82, 579.72, 581.59, 583.46, 585.31, 587.14, 588.97, 590.77, 592.57, 594.35, 596.12, 597.87, 599.61,
            601.34, 603.05, 604.75, 606.44, 608.11, 609.78, 611.43, 613.06, 614.69, 616.30, 617.90, 619.49, 621.07, 622.63, 624.18, 625.72, 627.25,
            628.77, 630.28, 631.77, 633.25, 634.73, 636.19, 637.64, 639.08, 640.51, 641.93, 643.33, 644.73, 646.12, 647.49, 648.86, 650.22, 651.56,
            652.90, 654.23, 655.54, 656.85, 658.15, 659.44, 660.72, 661.99, 663.25, 664.50, 665.74, 666.97, 668.20, 669.41, 670.62, 671.82, 673.01,
            674.19, 675.36, 676.53, 677.68, 678.83, 679.97, 681.10, 682.23, 683.34, 684.45, 685.55, 686.64, 687.72, 688.80, 689.87, 690.93, 691.99,
            693.03, 694.07, 695.10, 696.13, 697.15, 698.16, 699.16, 700.16, 701.15, 702.13, 703.11, 704.08, 705.04, 706.00, 706.95, 707.89
        };

        eulerFunc.odeEuler(ode, t0, t1, stepSize, y0);

        double[] actual = eulerFunc.getyValues();

        assertArrayEquals("Ode modified must past difficult example", expected, actual, 28);
    }

}
