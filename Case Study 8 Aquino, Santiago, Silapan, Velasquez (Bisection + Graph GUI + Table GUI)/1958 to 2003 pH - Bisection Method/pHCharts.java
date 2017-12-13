import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


public class pHCharts extends Application{
	
	public pHCharts(){}

	public static double bisect(double a, double b, double tol, int imax, int ppm){
		double fa = f(a,ppm);
		double fb = f(b,ppm);
		double xNS = 0;
		int k = 0;
		double pH = 0;
		if (fa*fb>0) {System.out.println("ERROR");}
		else{
			for (int i = 0; i < imax; i++) {
				xNS = (a+b)/2;
				double toli = (b-a)/2;
				double fxNS = f(xNS,ppm);
				pH = -1*Math.log10(xNS);
				if (fxNS == 0) {
					System.out.println("H+ is "+xNS+ " with pH of "+pH);
					break;
				}
				if (toli < tol) {
					break;
				}
				if (i == imax-1) {
					System.out.println("Solution not found");
					break;
				}
				if ( f(a,ppm)*fxNS < 0) {
					b = xNS;
				}
				else {
					a = xNS;
				}
				k = i;
			}
			System.out.println(xNS+"\t"+pH+"\t"+k);
		}
		return pH;
	}

	public static double f(double d,int ppm){
		//int pCO = 315;
		double kh = Math.pow(10,-1.46);
		double k1 = Math.pow(10,-6.3);
		double k2 = Math.pow(10,-10.3);
		double kw = Math.pow(10,-14);
		double ten = Math.pow(10,6);
		double y = 0;

		y = ((k1*d*kh*ppm)/(ten))+((2*k1*k2*kh*ppm)/(ten))+(kw*d)-(Math.pow(d,3));
		return y;
	}

	public static int getpCO(int t){
		double ans;
		ans = Math.ceil(0.011825*(Math.pow((t-1980.5),2))+1.356975*(t-1980.5)+339);
		int answer = (int) ans;
		return answer;
	}

	@Override public void start(Stage stage) {
        stage.setTitle("Scatter Chart Sample");
        final NumberAxis xAxis = new NumberAxis(1950, 2005, 10);
        final NumberAxis yAxis = new NumberAxis(5.587, 5.651, 0.001);        
        final ScatterChart<Number,Number> sc = new
            ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Year");                
        yAxis.setLabel("pH");
        sc.setTitle("pH Scatter Chart from 1958-2003");
       
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("pH per year");
    
        double a = Math.pow(10,-12);
		double b = Math.pow(10,-2);
		double tol = Math.pow(10,-9);
		int imax = 100;
		int y = 1958;
		int ppm = 0;

        for (int i = 1958; i < 2004; i++) {
			ppm = getpCO(i);
			series1.getData().add(new XYChart.Data(i,bisect(a,b,tol,imax,ppm)));
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