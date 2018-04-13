/** Traces two curves, one which shows how the x-values vary with respect to the change in time
    and another which shows how the y-values vary with respect to the change in time. 
    Plots the time values in the x-axis and the x (or y for the second curve) values in the y-axis. 
  */       

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
 
public class Graph extends Application {
    private static Double[] theXValues, theYValues, theTimeValues;
    private static String theXAxisLabel, theYAxisLabel, theTitle;
    private static double n;

    public static void main(String[] args) {
        launch(args);
    }
    
    public void setInitialGraphValues (Double[] x, Double[] y, Double[] t, double n, String xName, String yName, String title) {
        Graph.theXValues = x; 
        Graph.theYValues = y; 
        Graph.theTimeValues = t; 
        Graph.theXAxisLabel = xName; 
        Graph.theYAxisLabel = yName;
        Graph.theTitle = title; 
        Graph.theNumTimeIntervals = n;
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
        XYChart.Series<Number, Number> series1 = new XYChart.Series<Number, Number>();
        
        series.setName("Lions");
        series1.setName("Gazelles");
        
        for (int i=1; i<n+1; i++){
            xTimeSeries.getData().add(new XYChart.Data<Number, Number>(theTimeValues[i], theXValues[i]));
            yTimeSeries.getData().add(new XYChart.Data<Number, Number>(theTimeValues[i], theYValues[i]));
        }
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(xTimeSeries);
        lineChart.getData().add(yTimeSeries);
        
        stage.setScene(scene);
        stage.show();
    }

    
    @Override
    public void stop() {
        Platform.exit();
    }
}