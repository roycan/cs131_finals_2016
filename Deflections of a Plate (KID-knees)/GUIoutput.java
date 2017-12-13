import javax.swing.*;
import java.awt.Frame;
import java.awt.*;
import javax.swing.JFrame;

public class GUIoutput extends Frame{
	public static void GUIoutput(double[][] data){
		JFrame frame = new JFrame("Elliptic PDE");
		frame.setSize(400, 400);
		GUIoutput out = new GUIoutput();
		
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout (data[0].length+1, data[0].length+1));
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(data[1].length+1, data[1].length+1));
		
		JTextField u = new JTextField("u:");
		u.setEditable(false);
		panel1.add(u);
		JTextField z = new JTextField("z:");
		z.setEditable(false);
		panel2.add(z);
		
		for(int i=0; i<data[0].length; i++){
			JTextField textFieldL = new JTextField(""+((float) Math.round(data[0][i]*1000)/1000));
			textFieldL.setEditable(false);
			panel1.add(textFieldL);
		}
		for(int i=0; i<data[1].length; i++){
			JTextField textFieldL = new JTextField(""+((float) Math.round(data[1][i]*1000)/1000));
			textFieldL.setEditable(false);
			panel2.add(textFieldL);
		}
		
		panel.add(panel1);
		panel.add(panel2);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	}
}