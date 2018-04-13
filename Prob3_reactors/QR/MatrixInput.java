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


/* GUI for inputting the Matrix A */
public class MatrixInput {

	public static void MatrixInput(int row, int col, double[][] A) {
	    JFrame frame = new JFrame("Matrix Input");
	    frame.setSize(400, 400);

	    JPanel panel = new JPanel();     
	    panel.setLayout(new GridLayout(row,col));

	    List<JTextField> list = new ArrayList<JTextField>();

	    for (int i =0; i < row*col; i++) {
	       JTextField textField = new JTextField(""+0);
	       panel.add(textField);
	       list.add(textField);
	    }

	    JPanel panel1 = new JPanel();
	    JButton jb = new JButton("Calculate");
	    jb.setBounds(50,50,100,100);
	    
	    frame.getContentPane().add(panel);
	    frame.getContentPane().add(jb, BorderLayout.SOUTH);	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);

	    jb.addActionListener(new ActionListener() {
	    	public void actionPerformed (ActionEvent e) {
	    		int i = 0;
	    		int j = 0;
	    		for( JTextField f : list ) { 
				   System.out.println( f.getText() ) ;
				   A[i][j] = Double.parseDouble(f.getText());
				   j++;

				   if(j == col) {
				   	 i++;
				   	 j = 0;
				   }
				}

				System.out.println("Inside actionPerformed");
	     		System.out.println(Arrays.deepToString(A));
	     		main.flag=1;
	     		frame.dispose();
	    	}
	    });
	    
	    
	     //System.out.println(Arrays.deepToString(LUResult[1]));
	    //double[][] A = {{2, -4, 1},{6, 2, -1},{2, 2, 2}};
	    
	}
}