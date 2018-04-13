import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.*;

public class Main extends Application {

    Stage window;
    TableView<Product> table;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Yearly pH Levels - Bisection Method");
        window.setResizable(true);
        //variable names are random
        TableColumn<Product, Integer> nameColumn = new TableColumn<>("year");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Integer> priceColumn = new TableColumn<>("iterations");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, Double> quantityColumn = new TableColumn<>("a");
        quantityColumn.setMinWidth(200);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Product, Double> qualitycolumn = new TableColumn<>("b");
        qualitycolumn.setMinWidth(200);
        qualitycolumn.setCellValueFactory(new PropertyValueFactory<>("quality"));

        TableColumn<Product, Double> hpluscolumn = new TableColumn<>("[H+]");
        hpluscolumn.setMinWidth(200);
        hpluscolumn.setCellValueFactory(new PropertyValueFactory<>("hplus"));

        TableColumn<Product, Double> pmmcolumn = new TableColumn<>("pCO2");
        pmmcolumn.setMinWidth(200);
        pmmcolumn.setCellValueFactory(new PropertyValueFactory<>("pmm"));

        TableColumn<Product, Double> awesomecolumn = new TableColumn<>("pH");
        awesomecolumn.setMinWidth(200);
        awesomecolumn.setCellValueFactory(new PropertyValueFactory<>("awesome"));

        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(nameColumn, priceColumn, quantityColumn, qualitycolumn,hpluscolumn,pmmcolumn,awesomecolumn);


        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }


    public static double f(double d,int ppm){
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

    public ObservableList<Product> getProduct(){
        ObservableList<Product> products = FXCollections.observableArrayList();

        double a = Math.pow(10,-12);
        double b = Math.pow(10,-2);
        double tol = Math.pow(10,-9);
        int imax = 100;
        int y = 1958;
        int ppm = 0;

        for (int q = 1958; q < 2004; q++) {
            ppm = getpCO(q);
            

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
                products.add(new Product(q,k,xNS,pH,xNS,ppm,pH));
            }

            a = Math.pow(10,-12);
            b = Math.pow(10,-2);
            tol = Math.pow(10,-9);
            imax = 100;
            y = 1958;
            ppm = 0;
        }

        return products;
    }


}