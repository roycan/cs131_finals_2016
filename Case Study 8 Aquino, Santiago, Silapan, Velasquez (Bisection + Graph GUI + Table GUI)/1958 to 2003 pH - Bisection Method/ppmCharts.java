import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


public class ppmCharts extends Application{
	
	public ppmCharts(){}

	public static int getpCO(int t){
		double ans;
		ans = Math.ceil(0.011825*(Math.pow((t-1980.5),2))+1.356975*(t-1980.5)+339);
		int answer = (int) ans;
		return answer;
	}

	@Override public void start(Stage stage) {
        stage.setTitle("Scatter Chart Sample");
        final NumberAxis xAxis = new NumberAxis(1950, 2005, 10);
        final NumberAxis yAxis = new NumberAxis(310, 400, 1);        
        final ScatterChart<Number,Number> sc = new
            ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Year");                
        yAxis.setLabel("pCO2");
        sc.setTitle("pCO2 Scatter Chart from 1958-2003");

       
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("pC02 per year");

		double ppm = 0;

        for (int i = 1958; i < 2004; i++) {
			ppm = getpCO(i);
			series1.getData().add(new XYChart.Data(i,ppm));
		}
        


        sc.getData().addAll(series1);
        Scene scene  = new Scene(sc, 700, 600);
        stage.setScene(scene);
        stage.show();
    }

	public static void main(String[] args) {
		launch(args);
    }
}