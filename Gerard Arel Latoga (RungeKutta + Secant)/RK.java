import org.mariuszgromada.math.mxparser.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RK {
    static Function y; //the equation
    static Argument yInit; //initial value of y
    static Argument a; //first value of x
    static Argument b; //last value of x
    static Argument h; //step size;
    Expression ODE;

    double x_values[];
    double y_values[];

    public static void main(String[] args) {
    }

    public RK(String _y, String _yInit, String _a, String _b, String _h) {
            y = new Function(_y);
            yInit = new Argument(_yInit);
            a = new Argument(_a);
            b = new Argument(_b);
            h = new Argument(_h);

            int iterations = (int) ((b.getArgumentValue() - a.getArgumentValue()) / h.getArgumentValue()) + 1;
            x_values = new double[iterations + 1];
            y_values = new double[iterations + 1];

            x_values[0] = a.getArgumentValue();
            y_values[0] = yInit.getArgumentValue();

            for(int i = 1; i <= iterations ; i++) {
                a.setArgumentValue(a.getArgumentValue() + h.getArgumentValue());
                Expression e = new Expression("y", y);
                //mXparser.consolePrintln(e.calculate());

                x_values[i] = x_values[i-1] + h.getArgumentValue();
                double K1 = y.calculate(x_values[i-1], y_values[i-1]);
                double xhalf = x_values[i-1] + h.getArgumentValue()/2;
                double yK1 = y_values[i-1] + (K1 * h.getArgumentValue()/2);
                double K2 = y.calculate(xhalf, yK1);
                double yK2 = y_values[i-1] + (K2 * h.getArgumentValue()/2);
                double K3 = y.calculate(xhalf, yK2);
                double yK3 = y_values[i-1] + (K3 * h.getArgumentValue()/2);
                double K4 = y.calculate(x_values[i], yK3);
                y_values[i] = y_values[i-1] + (K1 + 2*K2 + 2*K3 + K4)*h.getArgumentValue()/6;
            }
    }

    double[] x() {
        return x_values;
    }

    double[] y() {
        return y_values;
    }
}
