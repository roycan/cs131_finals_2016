/**
 * Gerard Arel H Latoga
 * 2013-14806
 */

import org.mariuszgromada.math.mxparser.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/* COMPILATION INSTRUCTIONS
 * $ javac -cp './:mxparser.jar' RungeKutta.java
 * $ java -cp './:mxparser.jar' RungeKutta
 * if you're on windows use ; instead of :
 * ONLY ADD THE VALUES OF THE VARIABLES THEMSELVES IN THE TEXTFIELDS.
 * DO NOT DELETE THE VARIABLE NAMES 
 * I do not own the MXParser library. All credits to its owner.
 */

public class RungeKutta {
    static Function y; //the equation
    static Argument yInit; //initial value of y
    static Argument a; //first value of x
    static Argument b; //last value of x
    static Argument h; //step size;
    static Expression ODE;

    double x_values[];
    double y_values[];

    JFrame frame;
    JTextField odeBox;
    JTextField aBox;
    JTextField bBox;
    JTextField hBox;
    JTextField yInitBox;
    JButton computeBox;
    JTextField fileName;

    public static void main (String[] args) {
        RungeKutta rk = new RungeKutta();
    }

    public RungeKutta() {
        InitUI();

        a = new Argument("x = 0");
        b = new Argument("x = 5");
        h = new Argument("h = 1");
        y = new Function("f(x,y) = x + y");
        yInit = new Argument("yInit = 0");

        Expression e = new Expression("y", y);
    }

    private void GetAnswer() {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(fileName.getText());
            bw = new BufferedWriter(fw);

            bw.write("x," + "y" + "\n");

            int iterations = (int) ((b.getArgumentValue() - a.getArgumentValue()) / h.getArgumentValue()) + 1;
            x_values = new double[iterations + 1];
            y_values = new double[iterations + 1];

            x_values[0] = a.getArgumentValue();
            y_values[0] = yInit.getArgumentValue();

            bw.write(x_values[0] + ", " + y_values[0] + '\n');

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
                mXparser.consolePrintln(x_values[i] + " " + y_values[i]);
                bw.write(x_values[i] + ", " + y_values[i] + '\n');
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                bw.close();
                fw.close();
            }
            catch (IOException f) {
                f.printStackTrace();
            }
        }
    }

    private void InitUI() {
        frame = new JFrame();
        odeBox = new JTextField("f(x, y) = ");
        aBox = new JTextField("x_0 = ");
        bBox = new JTextField("x_n = ");
        hBox = new JTextField("h = ");
        yInitBox = new JTextField("yInit = ");
        computeBox = new JButton("Compute");   
        fileName = new JTextField(".txt");

        odeBox.setBounds(10, 10, 200, 15);
        aBox.setBounds(10, 25, 200, 15);
        bBox.setBounds(10, 40, 200, 15);
        hBox.setBounds(10, 55, 200, 15);
        yInitBox.setBounds(10, 70, 200, 15);
        computeBox.setBounds(25, 100, 100, 30);
        fileName.setBounds(10, 220, 200, 15);

        odeBox.setVisible(true);
        aBox.setVisible(true);
        bBox.setVisible(true);
        hBox.setVisible(true);
        yInitBox.setVisible(true);
        fileName.setVisible(true);

        frame.add(odeBox);
        frame.add(aBox);
        frame.add(bBox);
        frame.add(hBox);
        frame.add(yInitBox);
        frame.add(computeBox);
        frame.add(fileName);

        frame.setSize(250, 250);
        frame.setLayout(null);
        frame.setVisible(true);

        computeBox.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                a = new Argument(aBox.getText());
                b = new Argument(bBox.getText());
                h = new Argument(hBox.getText());
                y = new Function(odeBox.getText());
                yInit = new Argument(yInitBox.getText());

                GetAnswer();
            }
        });
    }
}
