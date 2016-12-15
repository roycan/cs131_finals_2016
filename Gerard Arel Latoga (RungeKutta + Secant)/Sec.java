import org.mariuszgromada.math.mxparser.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Sec {
    static Function y; //the equation
    static Argument yInit; //initial value of y
    static Argument a; //first value of x
    static Argument b; //last value of x
    static Argument err; //step size;
    static Argument imax; //max iters
    static Expression FUN;

    double x_values[];
    double y_values[];

    double answer;

    public static void main(String[] args) {
    }

    public Sec(String _y, String _a, String _b, String _err, String _imax) {
            y = new Function(_y);
            a = new Argument(_a);;
            b = new Argument(_b);
            err = new Argument(_err);
            imax = new Argument(_imax);
            int i;
            double Xa = a.getArgumentValue();
            double Xb = b.getArgumentValue();
            double Xi;

            System.out.println((int)a.getArgumentValue());

            if (Xa <= 0 && 0 < Xb) {
                Xa = 0;
            }

            if (y.calculate(Xa) == 0 || y.calculate(Xb) == 0) {
                answer = 0;
            }
            else {
                for (i = 0; i < (int) imax.getArgumentValue(); i++) {
                    double FunXb = y.calculate(Xb);
                    Xi = Xb - FunXb*(Xa-Xb)/(y.calculate(Xa) - FunXb);
                    System.out.println(y.calculate(Xa));
                    if (Math.abs((Xi - Xb) / Xb) < err.getArgumentValue()) {
                        answer = Xi;
                        break;
                    }
                    Xa = Xb;
                    Xb = Xi;
                }
                if (i == (int) imax.getArgumentValue()) {
                    answer = Double.NaN;
                }
            }
    }

    double Answer() {
        return answer;
    }
}
