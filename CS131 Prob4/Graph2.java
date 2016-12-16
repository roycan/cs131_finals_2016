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
    private static Double[] x, y;
    private static String xName, yName, title;
    private static double n;
    
    public void init (Double[] x, Double[] y, double n, String xName, String yName, String title) {
        Graph2.x = x; Graph2.y = y; Graph2.xName = xName; Graph2.yName = yName;
        Graph2.title = title; Graph2.n = n;
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
        for (int i=1; i<n+1; i++){
            series.getData().add(new XYChart.Data<Number, Number>(x[i], y[i]));
        }
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
       
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}