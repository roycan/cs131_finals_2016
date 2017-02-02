/** Traces a single curve which shows how the x-values vary with respect to the change in the y-values
    and vice versa by plotting the x-values in the x-axis and y-values in the y-axis.
*/    

import java.awt.event.ActionEvent;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
 
public class Graph2 extends Application {
    private static Double[] theXValues, theYValues;
    private static String theXAxisLabel, theYAxisLabel, theTitle;
    private static double theNumTimeIntervals;
    
    public static void main(String[] args) {
        launch(args);
    }

    public void setInitialGraphValues (Double[] x, Double[] y, double n, String xName, String yName, String title) {
        Graph2.theXValues = x; 
        Graph2.theYValues = y; 
        Graph2.theXAxisLabel = xName; 
        Graph2.theYAxisLabel = yName;
        Graph2.theTitle = title; 
        Graph2.theNumTimeIntervals = n;
        main(null);
    }
    
    @Override public void start(Stage stage) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(theXAxisLabel);
        yAxis.setLabel(theYAxisLabel);
        
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle(theTitle);
                                
        XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
        for (int i=1; i<theNumTimeIntervals+1; i++){
            series.getData().add(new XYChart.Data<Number, Number>(theXValues[i], theYValues[i]));
        }
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
       
        stage.setScene(scene);
        stage.show();
    }
 
}