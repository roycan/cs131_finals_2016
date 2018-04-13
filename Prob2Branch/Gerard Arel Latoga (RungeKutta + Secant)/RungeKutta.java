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
    String y; //the equation
    String yInit; //initial value of y
    String a; //first value of x
    String b; //last value of x
    String h; //step size;

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
    }

    private void GetAnswer() {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(fileName.getText());
            bw = new BufferedWriter(fw);

            bw.write("x," + "y" + "\n");

            RK rk = new RK(y, yInit, a, b, h);
            x_values = rk.x();
            y_values = rk.y();

            for (int i = 0; i < x_values.length; i++) {
                bw.write(x_values[i] + "," + y_values[i] + '\n');
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
                y = odeBox.getText();
                yInit = yInitBox.getText();
                a = aBox.getText();
                b = bBox.getText();
                h = hBox.getText();

                GetAnswer();
            }
        });
    }
}
