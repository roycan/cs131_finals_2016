/*
 * Copyright (c) 2017 CS 131 IPSE NKIA. All rights reserved.
 */
package LU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.*;

/**
 * GUI for displaying Matrices L and U
 * @author Faith Therese Pena - CS131 THR
 */
public class MatrixOutput extends JFrame implements ActionListener {

	private CardLayout myCardLayout;
	private JButton myBtn1, myBtn2;
	private static JPanel thePanelA = new JPanel();
	private static JPanel thePanelB = new JPanel();
	private static JPanel thePanel1 = new JPanel();
	private static JPanel thePanel2 = new JPanel();
	private Container myContainer;

	public MatrixOutput() {
		myContainer = getContentPane();
		myCardLayout = new CardLayout(40,30);
		myContainer.setLayout(myCardLayout);

		myBtn1 = new JButton("L");
		myBtn2 = new JButton("U");
		myBtn1.setBounds(50,50,200,200);
		myBtn2.setBounds(50,50,200,200);
		myBtn1.addActionListener(this);
		myBtn2.addActionListener(this);

		thePanelA.add(thePanel1);
		thePanelA.add(myBtn1, BorderLayout.NORTH);
		thePanelB.add(thePanel2);
		thePanelB.add(myBtn2, BorderLayout.NORTH);

		myContainer.add("L", thePanelA);
		myContainer.add("U", thePanelB);
	}

	public static void MatrixOutput(int rowL, int colL, int rowU, int colU, double[][] L, double[][] U) {
		MatrixOutput cl = new MatrixOutput();

		thePanel1.setLayout(new GridLayout(rowL, colL));
		thePanel2.setLayout(new GridLayout(rowU, colU));

		for (int i=0; i < rowL; i++) {
			for (int j=0; j < colL; j++) {
				JTextField textFieldL = new JTextField(""+L[i][j]);
				textFieldL.setPreferredSize(new Dimension(50,50));
				textFieldL.setEditable(false);
				thePanel1.add(textFieldL);
			}
		}

		for(int i=0; i <rowU; i++) {
			for(int j=0; j <colU; j++) {
				JTextField textFieldU = new JTextField(""+U[i][j]);
				textFieldU.setPreferredSize(new Dimension(50,50));
				textFieldU.setEditable(false);
				thePanel2.add(textFieldU);
			}
		}
		cl.setSize(400,400);
		cl.setVisible(true);
		cl.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		myCardLayout.next(myContainer);
	}
}
