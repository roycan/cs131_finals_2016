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
 * $ javac -cp './:mxparser.jar' Secant.java
 * $ java -cp './:mxparser.jar' Secant  
 * if you're on windows use ; instead of :
 * ONLY ADD THE VALUES OF THE VARIABLES THEMSELVES IN THE TEXTFIELDS.
 * DO NOT DELETE THE VARIABLE NAMES 
 * I do not own the MXParser library. All credits to its owner.
 */

public class Secant {
    static Function y; //the equation
    static Argument yInit; //initial value of y
    static Argument a; //first value of x
    static Argument b; //last value of x
    static Argument err; //step size;
    static Argument imax; //max iters
    static Expression FUN;

    double x_values[];
    double y_values[];

    JFrame frame;
    JTextField funBox;
    JTextField aBox;
    JTextField bBox;
    JTextField errBox;
    JTextField imaxBox;
    JButton computeBox;
    JTextField fileName;

    public static void main (String[] args) {
        Secant rk = new Secant();
    }

    public Secant() {
        InitUI();
    }

    private void GetAnswer() {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            int i;
            fw = new FileWriter(fileName.getText());
            bw = new BufferedWriter(fw);

            bw.write("function, Xa, Xb, Error, iMax, Answer\n");

            double Xa = a.getArgumentValue();
            double Xb = b.getArgumentValue();
            double Xi;

            System.out.println((int)a.getArgumentValue());

            if (Xa <= 0 && 0 < Xb) {
                Xa = 0;
            }

            if (y.calculate(Xa) == 0 || y.calculate(Xb) == 0) {
                bw.write(y.getFunctionExpressionString() + ", " +
                        String.format("%.2f", a.getArgumentValue()) + ", " +
                        String.format("%.2f", b.getArgumentValue()) + ", " +
                        String.format("%.2f", err.getArgumentValue()) + ", " +
                        String.format("%.2f", imax.getArgumentValue()) + ", " +
                        "0" + '\n');
            }
            else {
                for (i = 0; i < (int) imax.getArgumentValue(); i++) {
                    double FunXb = y.calculate(Xb);
                    Xi = Xb - FunXb*(Xa-Xb)/(y.calculate(Xa) - FunXb);
                    System.out.println(y.calculate(Xa));
                    if (Math.abs((Xi - Xb) / Xb) < err.getArgumentValue()) {
                        bw.write(y.getFunctionExpressionString() + ", " +
                                String.format("%.2f", a.getArgumentValue()) + ", " +
                                String.format("%.2f", b.getArgumentValue()) + ", " +
                                String.format("%.2f", err.getArgumentValue()) + ", " +
                                String.format("%.2f", imax.getArgumentValue()) + ", " +
                                String.format("%.5f",Xi) + '\n');
                        break;
                    }
                    Xa = Xb;
                    Xb = Xi;
                }
                if (i == (int) imax.getArgumentValue()) {
                    bw.write(y.getFunctionExpressionString() + ", " +
                            String.format("%.2f", a.getArgumentValue()) + ", " +
                            String.format("%.2f", b.getArgumentValue()) + ", " +
                            String.format("%.2f", err.getArgumentValue()) + ", " +
                            String.format("%.2f", imax.getArgumentValue()) + ", " +
                            imax.getArgumentExpressionString() + ", " +
                            "no answer" + '\n');
                }
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
        funBox = new JTextField("f(x) = ");
        aBox = new JTextField("Xa = ");
        bBox = new JTextField("Xb = ");
        errBox = new JTextField("err = ");
        imaxBox = new JTextField("imax = ");
        computeBox = new JButton("Compute");   
        fileName = new JTextField(".txt");

        funBox.setBounds(10, 10, 200, 15);
        aBox.setBounds(10, 25, 200, 15);
        bBox.setBounds(10, 40, 200, 15);
        errBox.setBounds(10, 55, 200, 15);
        imaxBox.setBounds(10, 70, 200, 15);
        computeBox.setBounds(25, 100, 100, 30);
        fileName.setBounds(10, 220, 200, 15);

        funBox.setVisible(true);
        aBox.setVisible(true);
        bBox.setVisible(true);
        errBox.setVisible(true);
        imaxBox.setVisible(true);
        fileName.setVisible(true);

        frame.add(funBox);
        frame.add(aBox);
        frame.add(bBox);
        frame.add(errBox);
        frame.add(imaxBox);
        frame.add(computeBox);
        frame.add(fileName);

        frame.setSize(250, 250);
        frame.setLayout(null);
        frame.setVisible(true);

        computeBox.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                a = new Argument(aBox.getText());
                b = new Argument(bBox.getText());
                err = new Argument(errBox.getText());
                imax = new Argument(imaxBox.getText());
                y = new Function(funBox.getText());

                GetAnswer();
            }
        });
    }
}
