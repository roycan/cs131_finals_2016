import javax.swing.*;
import java.awt.Frame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

public class GUInput extends Frame{
	public static void GUInput(float[] input){
		JFrame frame = new JFrame("Elliptic PDE");
		frame.setSize(400, 400);
		
		JPanel panel = new JPanel();     
	    panel.setLayout(new GridLayout(7,1));
		
		JTextField xlen = new JTextField("x-axis length");
	    panel.add(xlen);
		JTextField ylen = new JTextField("y-axis length");
	    panel.add(ylen);
		JTextField deltaxy = new JTextField("delta x and y");
	    panel.add(deltaxy);
		JTextField load = new JTextField("load (q)");
	    panel.add(load);
		JTextField thick = new JTextField("thickness (z)");
	    panel.add(thick);
		JTextField poisson = new JTextField("poisson's ratio");
	    panel.add(poisson);
		JTextField modE = new JTextField("modulus of elasticity");
	    panel.add(modE);
		
		JPanel panel1 = new JPanel();
	    JButton jb = new JButton("Calculate");
		
		frame.getContentPane().add(panel);
	    frame.getContentPane().add(jb, BorderLayout.SOUTH);	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
		
		jb.addActionListener(new ActionListener() {
	    	public void actionPerformed (ActionEvent e) {
	    		input[0] = Float.parseFloat(xlen.getText());
				input[1] = Float.parseFloat(ylen.getText());
				input[2] = Float.parseFloat(deltaxy.getText());
				input[3] = Float.parseFloat(load.getText());
				input[4] = Float.parseFloat(thick.getText());
				input[5] = Float.parseFloat(poisson.getText());
				input[6] = Float.parseFloat(modE.getText());
				
	     		frame.dispose();
	    	}
	    });
	}
}