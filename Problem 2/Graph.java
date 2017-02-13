import java.awt.*;
import javax.swing.*;

public class Graph extends JFrame {
    public Graph(Double[] xData, Double[] yData) {
        super("Graph");
        drawable = getContentPane();
        canvas = new GraphCanvas(xData, yData);
        drawable.add(canvas);
        setSize(WIDTH, HEIGHT);
    }

	private final int WIDTH = 720;
    private final int HEIGHT = 500;
    private Container drawable;
    private JPanel canvas;
    double SCALE = 30;   // Size of the graph
    int OFFSET = 120;
        
    public class GraphCanvas extends JPanel {
        private double[] mydata;
        private int mypoints;
        private double[] theXData;
        private double[] theYData;
        
        public GraphCanvas(Double[] thexData, Double[] theyData) {
            super();
            mypoints = xData.length;
            
			theXData = new double[mypoints];
            theYData = new double[mypoints];
            
            for(int i = 0; i < points; i++) {
                theXData[i] = xData[i].doubleValue();
                theYData[i] = yData[i].doubleValue();
            }
        }
        
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            for(int i = 0; i < points - 1; i++) {
                int InitialXData = (int) (theXData[i] * SCALE);
                int FinalXData = (int) (theXData[i + 1] * SCALE);
                int InitialYData = (int) (theYData[i] * SCALE);
                int FinalYData = (int) (theYData[i + 1] * SCALE);
                g2.drawLine(InitialXData, InitialYData+OFFSET, FinalXData, FinalYData+OFFSET);
                
                // Print value
                //g2.drawString(("" + InitialXData + ", " + InitialYData), InitialXData - 20, InitialYData + 10);                   
            }
        }
    }
}

