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
    static String y; //the equation
    static String yInit; //initial value of y
    static String a; //first value of x
    static String b; //last value of x
    static String err; //step size;
    static String imax; //max iters
    static String FUN;

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
           
            Sec sec = new Sec(y, a, b, err, imax);
            bw.write(y + "," + a + ","  + b  + "," + err  + "," + imax  + "," + sec.Answer() + '\n');
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
                y = funBox.getText();
                a = aBox.getText();
                b = bBox.getText();
                err = errBox.getText();
                imax = imaxBox.getText();
                GetAnswer();
            }
        });
    }
}
