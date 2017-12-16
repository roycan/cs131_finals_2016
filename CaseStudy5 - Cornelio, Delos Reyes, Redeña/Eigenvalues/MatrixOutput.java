/* Faith Therese Pena
   CS131 THR
*/

import javax.swing.*;   
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.*;


/* GUI for outputting the Matrices Q and R */
public class MatrixOutput extends JFrame implements ActionListener {
	CardLayout card;
	// JButton b1, b2;
	static JPanel panelA = new JPanel();
	// static JPanel panelB = new JPanel();
	static JPanel panel1 = new JPanel();
	// static JPanel panel2 = new JPanel();
	Container c;

		MatrixOutput(){
			c = getContentPane();
			card = new CardLayout(40,30);
			c.setLayout(card);

			// b1 = new JButton("Q");
			// b2 = new JButton("R");
			// b1.setBounds(50,50,200,200);
			// b2.setBounds(50,50,200,200);
			// b1.addActionListener(this);
			// b2.addActionListener(this);

			JLabel eigenheader = new JLabel("Eigenvalues in the diagonal:");
			eigenheader.setPreferredSize(new Dimension(200,50));
			panelA.add(eigenheader);

			panelA.add(panel1);
			// panelA.add(b1, BorderLayout.NORTH);
			// panelB.add(panel2);
			// panelB.add(b2, BorderLayout.NORTH);
			
			c.add("Q", panelA);
			// c.add("R", panelB);
		}

	public static void MatrixOutput(int row, int col, double[][] E) {
		double[] eigenvals = new double[3];
		System.out.println("Eigenvalues:");
		for (int e=0; e < row; e++){
			eigenvals[e] = E[e][e];
			System.out.println(eigenvals[e]);
		}

		
	    

		MatrixOutput cl = new MatrixOutput();

		panel1.setLayout(new GridLayout(row, col));
		// panel2.setLayout(new GridLayout(row, col));

		for (int i=0; i < row; i++) {
			for (int j=0; j < col; j++) {
				JTextField textFieldL = new JTextField(""+((double) Math.round(E[i][j] * 100) / 100));
				// JTextField textFieldU = new JTextField(""+((double) Math.round(R[i][j] * 100) / 100));
				textFieldL.setPreferredSize(new Dimension(50,50));
				// textFieldU.setPreferredSize(new Dimension(50,50));
				textFieldL.setEditable(false);
				// textFieldU.setEditable(false);
				panel1.add(textFieldL);
				// panel2.add(textFieldU);
			}
		}

		

		cl.setSize(400,400);
		cl.setVisible(true);
		cl.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		card.next(c);
	}
}