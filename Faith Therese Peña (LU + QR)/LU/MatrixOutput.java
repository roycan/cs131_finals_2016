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


/** GUI for outputting Matrices L and U */
public class MatrixOutput extends JFrame implements ActionListener {
	CardLayout card;
	JButton b1, b2;
	static JPanel panelA = new JPanel();
	static JPanel panelB = new JPanel();
	static JPanel panel1 = new JPanel();
	static JPanel panel2 = new JPanel();
	Container c;

		MatrixOutput(){
			c = getContentPane();
			card = new CardLayout(40,30);
			c.setLayout(card);

			b1 = new JButton("L");
			b2 = new JButton("U");
			b1.setBounds(50,50,200,200);
			b2.setBounds(50,50,200,200);
			b1.addActionListener(this);
			b2.addActionListener(this);

			panelA.add(panel1);
			panelA.add(b1, BorderLayout.NORTH);
			panelB.add(panel2);
			panelB.add(b2, BorderLayout.NORTH);
			
			c.add("L", panelA);
			c.add("U", panelB);
		}

	public static void MatrixOutput(int row, int col, double[][] L, double[][] U) {
		MatrixOutput cl = new MatrixOutput();

		panel1.setLayout(new GridLayout(row, col));
		panel2.setLayout(new GridLayout(row, col));

		for (int i=0; i < row; i++) {
			for (int j=0; j < col; j++) {
				JTextField textFieldL = new JTextField(""+((double) Math.round(L[i][j]*1000)/1000));
				textFieldL.setPreferredSize(new Dimension(50,50));
				textFieldL.setEditable(false);
				panel1.add(textFieldL);

				JTextField textFieldU = new JTextField(""+((double) Math.round(U[i][j]*1000)/1000));
				textFieldU.setPreferredSize(new Dimension(50,50));
				textFieldU.setEditable(false);
				panel2.add(textFieldU);
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