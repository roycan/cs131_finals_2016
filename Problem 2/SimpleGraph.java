import java.awt.*;
import javax.swing.*;

public class SimpleGraph extends JFrame {
    private final int WIDTH = 720;
    private final int HEIGHT = 500;
    private Container drawable;
    private JPanel canvas;
    double SCALE = 30;   // Size of the graph
    int OFFSET = 120;

    public SimpleGraph(Double[] xData, Double[] yData) {
        super("Graph");
        drawable = getContentPane();
        canvas = new GraphCanvas(xData, yData);
        drawable.add(canvas);
        setSize(WIDTH, HEIGHT);
    }
    
    public class GraphCanvas extends JPanel {
        private double[] data;
        private int points;
        private double[] XData;
        private double[] YData;
        
        public GraphCanvas(Double[] xData, Double[] yData) {
            super();
            points = xData.length;
            
            XData = new double[points];
            YData = new double[points];
            
            for(int i = 0; i < points; i++) {
                XData[i] = xData[i].doubleValue();
                YData[i] = yData[i].doubleValue();
            }
        }
        
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            for(int i = 0; i < points - 1; i++) {
                int x0 = (int) (XData[i] * SCALE);
                int x1 = (int) (XData[i + 1] * SCALE);
                int y0 = (int) (YData[i] * SCALE);
                int y1 = (int) (YData[i + 1] * SCALE);
                g2.drawLine(x0, y0+OFFSET, x1, y1+OFFSET);
                
                // Print value
                //g2.drawString(("" + x0 + ", " + y0), x0 - 20, y0 + 10);                   
            }
        }
    }
}

