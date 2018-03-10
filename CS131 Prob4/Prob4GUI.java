/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/

/*
 *
 * @author User
*/

import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javax.swing.JFrame;

public class Prob4GUI extends javax.swing.JFrame {

    private static Double[] theXValues, theYValues, theTimeValues;
    private static double theNumTimeIntervals;

    @SuppressWarnings("unchecked")

    /** Declaration of variables needed for the GUI */
    private javax.swing.JButton myjButton1;
    private javax.swing.JButton myjButton2;
    private javax.swing.JButton myjButton3;
    private javax.swing.JFrame myjFrame1;
    private javax.swing.JLabel myjLabel1;
    private javax.swing.JLabel myjLabel2;
    private javax.swing.JLabel myjLabel3;
    private javax.swing.JLabel myjLabel4;
    private javax.swing.JLabel myjLabel5;
    private javax.swing.JTextField myjTextField1;
    private javax.swing.JTextField myjTextField2;
    private javax.swing.JTextField myjTextField3;
    private javax.swing.JTextField myjTextField4;
    private javax.swing.JTextField myjTextField5;

    /** Runs the program */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Prob4GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            new Prob4GUI().setVisible(true);
        });

    }

    /** Initializes the necessary GUI components */
    private void initComponents() {

        myjFrame1 = new javax.swing.JFrame();
        myjLabel1 = new javax.swing.JLabel();
        myjLabel2 = new javax.swing.JLabel();
        myjLabel3 = new javax.swing.JLabel();
        myjLabel4 = new javax.swing.JLabel();
        myjTextField1 = new javax.swing.JTextField();
        myjTextField2 = new javax.swing.JTextField();
        myjTextField3 = new javax.swing.JTextField();
        myjTextField4 = new javax.swing.JTextField();
        myjButton1 = new javax.swing.JButton();
        myjTextField5 = new javax.swing.JTextField();
        myjLabel5 = new javax.swing.JLabel();
        myjButton2 = new javax.swing.JButton();
        myjButton3 = new javax.swing.JButton();

        javax.swing.GroupLayout myjFrame1Layout = new javax.swing.GroupLayout(myjFrame1.getContentPane());
        myjFrame1.getContentPane().setLayout(myjFrame1Layout);
        myjFrame1Layout.setHorizontalGroup(
            myjFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        myjFrame1Layout.setVerticalGroup(
            myjFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        myjLabel1.setText("PROBLEM 4");

        myjLabel2.setText("Initial X-value:");

        myjLabel3.setText("Initial Y-value:");

        myjLabel4.setText("Range of t:");

        myjButton1.setText("Calculate");
        myjButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myjButton1ActionPerformed(evt);
            }
        });

        myjLabel5.setText("Step Size:");

        myjButton2.setText("Show Graph");
        myjButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myjButton2ActionPerformed(evt);
            }
        });

        myjButton3.setText("Show 2nd graph");
        myjButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myjButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(myjTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(myjButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(myjLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(myjTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(myjLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(myjTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(myjTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(myjLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(myjLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(myjTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(myjLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(myjButton2)
                        .addGap(33, 33, 33)
                        .addComponent(myjButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(myjLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(myjLabel2)
                    .addComponent(myjTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myjLabel4)
                    .addComponent(myjTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myjTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(myjLabel5)
                    .addComponent(myjTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myjLabel3)
                    .addComponent(myjTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myjButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(myjButton2)
                    .addComponent(myjButton3))
                .addContainerGap())
        );

        pack();
    }

    /** Calculates the given values */
    private void myjButton1ActionPerformed(java.awt.event.ActionEvent evt) {

        Double xInitial = Double.parseDouble(myjTextField1.getText());
        Double yInitial = Double.parseDouble(myjTextField2.getText());
        Double timeInitial = Double.parseDouble(myjTextField3.getText());
        Double timeFinal = Double.parseDouble(myjTextField4.getText());
        Double stepSize = Double.parseDouble(myjTextField5.getText());
        
        theNumTimeIntervals = (timeFinal-timeInitial)/stepSize;
        
        driverRK4 rkSolver = new driverRK4();
        rkSolver.setXYTs(timeInitial, timeFinal, stepSize, xInitial, yInitial);
        theXValues = rkSolver.getXValues();
        theYValues = rkSolver.getYValues();
        theTimeValues = rkSolver.getTimeValues();
    }

    /** Displays the graph produced by the values */
    private void myjButton2ActionPerformed(java.awt.event.ActionEvent evt) {

        dispose();
        Graph firstGraph = new Graph();
        firstGraph.init(theXValues,theYValues,theTimeValues,theNumTimeIntervals,"Time (t)", "", "Lions and Gazelles");
        
    }

    /** Displays a second graph */
    private void myjButton3ActionPerformed(java.awt.event.ActionEvent evt) {

        dispose();
        Graph2 secondGraph = new Graph2();
        secondGraph.init(theXValues,theYValues,theNumTimeIntervals,"Lions", "Gazelles", "Lions and Gazelles");
    }

    /** Calls the components */
    public Prob4GUI() {
        initComponents();
    }

}

