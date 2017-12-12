import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
 
 
public class Graphs extends Application {
    private static Double[] x, y, t;
    private static String xName, yName, title;
    private static double a, b, c, d, n;
    
    public void init (Double[] x, Double[] y, double a, double b, double c, double d, Double[] t, double n) {
        Graphs.x = x;
        Graphs.y = y;
        Graphs.a = a;
        Graphs.b = b;
        Graphs.c = c;
        Graphs.d = d;
        Graphs.t = t;
        Graphs.n = n;
        main(null);
    }
    
    @Override public void start(Stage stage) {
        NumberAxis xSeries = new NumberAxis();
        NumberAxis ySeries = new NumberAxis();
        NumberAxis xPhase = new NumberAxis();
        NumberAxis yPhase = new NumberAxis();
        xSeries.setLabel("Time (t)");
        ySeries.setLabel("Population");
        xPhase.setLabel("Prey population");
        yPhase.setLabel("Predator population");
        
        LineChart<Number,Number> seriesChart = new LineChart<Number,Number>(xSeries,ySeries);
        LineChart<Number,Number> phaseChart = new LineChart<Number,Number>(xPhase,yPhase);
                
        seriesChart.setTitle("Predator-Prey : Time Series");
        phaseChart.setTitle("Predator-Prey : Phase Plane");

                                
        XYChart.Series<Number, Number> timeSeries1 = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> timeSeries2 = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> phaseSeries = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> phaseCritPoint1 = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> phaseCritPoint2 = new XYChart.Series<Number, Number>();
        
        timeSeries1.setName("Prey");
        timeSeries2.setName("Predator");
        phaseSeries.setName("Predator vs Prey");
        phaseCritPoint1.setName("Critical point x, y = (0,0)");
        phaseCritPoint2.setName("Critical point x, y = (c/d, a/b)");
        
        for (int i=1; i<n+1; i++){
            timeSeries1.getData().add(new XYChart.Data<Number, Number>(t[i], x[i]));
            timeSeries2.getData().add(new XYChart.Data<Number, Number>(t[i], y[i]));
            phaseSeries.getData().add(new XYChart.Data<Number, Number>(x[i], y[i]));
        }
        phaseCritPoint1.getData().add(new XYChart.Data<Number, Number>(0, 0));
        phaseCritPoint2.getData().add(new XYChart.Data<Number, Number>(c/d, a/b));

        TilePane tile = new TilePane();
        tile.setHgap(8);
        tile.setPrefRows(1);
        tile.setPrefColumns(2);
        tile.setPrefTileWidth(530);
        tile.setPrefTileHeight(415);
        tile.getChildren().addAll(seriesChart, phaseChart);
        // FlowPane root = new FlowPane();
        // root.getChildren().addAll(seriesChart, phaseChart);

        Scene scene = new Scene(tile, 1120, 420);
        scene.getStylesheets().add("style.css");
        //Scene scene  = new Scene(seriesChart,800,600);
        seriesChart.getData().add(timeSeries1);
        seriesChart.getData().add(timeSeries2);
        seriesChart.setCreateSymbols(false);

        phaseChart.getData().add(phaseSeries);
        phaseChart.getData().add(phaseCritPoint2);
        phaseChart.getData().add(phaseCritPoint1);
        phaseChart.setCreateSymbols(true);
        
        stage.setScene(scene);
        stage.setTitle("CS 131, Case Study 4A: Lotka-Volterra Models' Graphs");
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