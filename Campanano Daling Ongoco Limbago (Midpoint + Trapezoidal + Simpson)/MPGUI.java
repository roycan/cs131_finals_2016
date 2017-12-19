import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class MPGUI extends JFrame{
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	private JTextField equationField, leftBoundField, rightBoundField, stepSizeField;
	private JTextField midpointResultField, trapezoidResultField, simpsonsResultField;
	private JTextField stepSizeArrayField, filenameField;
	private JTextField richardsonField, decimalPlacesField;
	private JLabel equationLabel, leftBoundLabel, rightBoundLabel, stepSizeLabel;
	private JLabel midpointLabel, trapezoidLabel, simpsonsLabel;
	private JLabel stepSizeArrayLabel;
	private JButton integrateButton;
	private JButton writeButton;
	private JButton richardsonButton;

	private integrateButtonHandler ibHandler;
	private writeButtonHandler wbHandler;
	private richardsonButtonHandler rbHandler;

	public MPGUI(){

		equationLabel = new JLabel("Enter equation in x: ",SwingConstants.RIGHT);
		leftBoundLabel = new JLabel("Enter left boundary: ",SwingConstants.RIGHT);
		rightBoundLabel = new JLabel("Enter right boundary: ",SwingConstants.RIGHT);
		stepSizeLabel = new JLabel("Enter stepsize: ",SwingConstants.RIGHT);

		stepSizeArrayLabel = new JLabel("Enter multiple stepsizes (seperate with spaces): ",SwingConstants.RIGHT);

		midpointLabel = new JLabel("Result using Midpoint: ",SwingConstants.RIGHT);
		trapezoidLabel = new JLabel("Result using Trapezoid: ",SwingConstants.RIGHT);
		simpsonsLabel = new JLabel("Result using Simpsons: ",SwingConstants.RIGHT);

		stepSizeArrayField = new JTextField();
		filenameField = new JTextField();

		equationField = new JTextField();
		leftBoundField = new JTextField();
		rightBoundField = new JTextField();
		stepSizeField = new JTextField();
		decimalPlacesField = new JTextField("Number of decimal places");
		
		richardsonField = new JTextField();

		midpointResultField = new JTextField();
		trapezoidResultField = new JTextField();
		simpsonsResultField = new JTextField();
		midpointResultField.setEditable(false);
		trapezoidResultField.setEditable(false);
		simpsonsResultField.setEditable(false);

		integrateButton = new JButton("Integrate");
		ibHandler = new integrateButtonHandler();
	    integrateButton.addActionListener(ibHandler);

		writeButton = new JButton("Write to filename: ");
		wbHandler = new writeButtonHandler();
		writeButton.addActionListener(wbHandler);

		richardsonButton = new JButton("Use Richardson with stepsize: ");
		rbHandler = new richardsonButtonHandler();
		richardsonButton.addActionListener(rbHandler);

		Container pane = getContentPane();
		pane.setLayout(new GridLayout(12, 2,5,5));

		pane.add(equationLabel);
		pane.add(equationField);
		pane.add(leftBoundLabel);
		pane.add(leftBoundField);
		pane.add(rightBoundLabel);
		pane.add(rightBoundField);
		pane.add(stepSizeLabel);
		pane.add(stepSizeField);
		pane.add(integrateButton);
		pane.add(decimalPlacesField);
		pane.add(stepSizeArrayLabel);
		pane.add(stepSizeArrayField);
		pane.add(writeButton);
		pane.add(filenameField);
		pane.add(midpointLabel);
		pane.add(midpointResultField);
		pane.add(trapezoidLabel);
		pane.add(trapezoidResultField);
		pane.add(simpsonsLabel);
		pane.add(simpsonsResultField);
		pane.add(richardsonButton);
		pane.add(richardsonField);
		
		setTitle("Function Integrator GUI");
		setSize(WIDTH,HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private class integrateButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
		try{
			String equation;
			double leftBound,rightBound;
			double stepSize, dP;
			int numberOfIntervals;
			double midpointResult, trapezoidResult, simpsonsResult;

			if((equationField.getText().length() > 0) && (leftBoundField.getText().length() > 0) && (rightBoundField.getText().length() > 0) && (stepSizeField.getText().length() > 0) && (decimalPlacesField.getText().length() > 0)){
				dP = Integer.parseInt(decimalPlacesField.getText());
				dP = Math.pow(10,dP);
				equation = equationField.getText();
				leftBound = Double.parseDouble(leftBoundField.getText());
				rightBound = Double.parseDouble(rightBoundField.getText());
				stepSize = Double.parseDouble(stepSizeField.getText());
				numberOfIntervals = (int)((rightBound-leftBound)*(1/stepSize));

				midpointResult = MidpointClass.Midpoint(equation,leftBound,rightBound,numberOfIntervals)[0][0];
				trapezoidResult = TrapezoidClass.Trapezoid(equation,leftBound,rightBound,numberOfIntervals)[0][0];
				simpsonsResult = SimpsonsClass.Simpsons(equation,leftBound,rightBound,numberOfIntervals)[0][0];

				midpointResult = Math.round(midpointResult*dP)/dP;
				trapezoidResult = Math.round(trapezoidResult*dP)/dP;
				simpsonsResult = Math.round(simpsonsResult*dP)/dP;

				midpointResultField.setText(""+ midpointResult);
				trapezoidResultField.setText(""+ trapezoidResult);
				simpsonsResultField.setText(""+ simpsonsResult);	
			}
			else{
				JOptionPane.showMessageDialog(null,"Missing input","Error!",JOptionPane.WARNING_MESSAGE);
			}
		}catch(NumberFormatException foo){
			JOptionPane.showMessageDialog(null,"Bad input format","Error!",JOptionPane.WARNING_MESSAGE);
		}
		}
	}
	public class writeButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
		try{
			if((stepSizeArrayField.getText().length() > 0) && (filenameField.getText().length() > 0) && (equationField.getText().length() > 0) && (leftBoundField.getText().length() > 0) && (rightBoundField.getText().length() > 0)){
				int dP = Integer.parseInt(decimalPlacesField.getText());
				String equation = equationField.getText();
				String filename = filenameField.getText();
				double leftBound = Double.parseDouble(leftBoundField.getText());
				double rightBound = Double.parseDouble(rightBoundField.getText());
				String stepSizeString = stepSizeArrayField.getText();
				String[] stepSizeArrayStr = stepSizeString.split("\\s");
				double[] stepSizeArray = new double[stepSizeArrayStr.length];
				int[] numberOfIntervalsArray = new int[stepSizeArrayStr.length];
				for(int ctr = 0; ctr < stepSizeArrayStr.length; ctr++){
					stepSizeArray[ctr] = Double.parseDouble(stepSizeArrayStr[ctr]);
					numberOfIntervalsArray[ctr] = (int) ((rightBound-leftBound)*(1/stepSizeArray[ctr]));
				}
				try{
					resultsTabulator.Tabulate(filename,equation,leftBound,rightBound,numberOfIntervalsArray,dP);
					JOptionPane.showMessageDialog(null,"File ready!","Complete",JOptionPane.INFORMATION_MESSAGE);
				}catch(IOException foo){
					JOptionPane.showMessageDialog(null,"Filewrite unsuccesful","Error!",JOptionPane.WARNING_MESSAGE);
					foo.printStackTrace();
				}
			}
			else{
				JOptionPane.showMessageDialog(null,"Missing input","Error!",JOptionPane.WARNING_MESSAGE);
			}
		}catch(NumberFormatException foo){
			JOptionPane.showMessageDialog(null,"Bad input format","Error!",JOptionPane.WARNING_MESSAGE);
		}
		}
	}

	public class richardsonButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try{
			String equation;
			double leftBound,rightBound;
			double stepSize;
			double[] richardsonResult = new double[3];
			int numberOfIntervals;
			if((equationField.getText().length() > 0) && (leftBoundField.getText().length() > 0) && (rightBoundField.getText().length() > 0) && (stepSizeField.getText().length() > 0) && (richardsonField.getText().length() > 0)){
				equation = equationField.getText();
				leftBound = Double.parseDouble(leftBoundField.getText());
				rightBound = Double.parseDouble(rightBoundField.getText());
				stepSize = Double.parseDouble(stepSizeField.getText());
				richardsonResult = RichardsonClass.Richardson(equation,leftBound,rightBound,stepSize);
				midpointResultField.setText(String.format("%.2f",richardsonResult[0]));
				trapezoidResultField.setText(String.format("%.2f",richardsonResult[1]));
				simpsonsResultField.setText(String.format("%.2f",richardsonResult[2]));
				JOptionPane.showMessageDialog(null,"Richardson done!","Complete",JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null,"Missing input","Error!",JOptionPane.WARNING_MESSAGE);
			}
		}catch(NumberFormatException foo){
			JOptionPane.showMessageDialog(null,"Bad input format","Error!",JOptionPane.WARNING_MESSAGE);
		}
		}
	}

	public static void main(String[] args) throws Exception{
		MPGUI mpgui = new MPGUI();
	}
	
}
