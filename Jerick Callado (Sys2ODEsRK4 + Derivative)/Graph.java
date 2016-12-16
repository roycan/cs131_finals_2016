import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
 
public class Graph extends Application {
    private static Double[] x, y, t;
    private static String xName, yName, title;
    private static double n;
    
    public void init (Double[] x, Double[] y, Double[] t, double n, String xName, String yName, String title) {
        Graph.x = x; Graph.y = y; Graph.t = t; Graph.xName = xName; Graph.yName = yName;
        Graph.title = title; Graph.n = n;
        main(null);
    }
    
    @Override public void start(Stage stage) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(xName);
        yAxis.setLabel(yName);
        
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle(title);
                                
        XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> series1 = new XYChart.Series<Number, Number>();
        
        series.setName("Lions");
        series1.setName("Gazelles");
        
        for (int i=1; i<n+1; i++){
            series.getData().add(new XYChart.Data<Number, Number>(t[i], x[i]));
            series1.getData().add(new XYChart.Data<Number, Number>(t[i], y[i]));
        }
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
        lineChart.getData().add(series1);
        
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void stop() {
        Platform.exit();
    }
}