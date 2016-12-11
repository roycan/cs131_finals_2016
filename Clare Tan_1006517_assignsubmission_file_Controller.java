package clare.le;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import org.mariuszgromada.math.mxparser.Function;

public class Controller {
    @FXML
    public TextField equationInput;

    @FXML
    public TextField xIni;

    @FXML
    public TextField xFin;

    @FXML
    public TextField hInput;

    @FXML
    public TextField yInput;

    @FXML
    public Label outputX;

    @FXML
    public Label outputY;


    // location and resources will be automatically injected by the FXML loader
    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    public Controller(){

    }

    @FXML
    private void initialize()
    {
    }

    @FXML
    public void performEuler()
    {
        String equation;
        equation = equationInput.getText();
        String expr = "ode(x, y) = " + equation;
        Function ode = new Function(expr);
        double a = Double.parseDouble(xIni.getText());
        double b = Double.parseDouble(xFin.getText());
        double h = Double.parseDouble(hInput.getText());
        double yIni =  Double.parseDouble(yInput.getText());

        EulerFunction answerStorage = new EulerFunction();
        answerStorage.odeEuler(ode,a,b,h,yIni);
        String xText = "X Values: " + Arrays.toString(answerStorage.getxValues());
        outputX.setText(xText);
        String yText = "Y Values: " + Arrays.toString(answerStorage.getyValues());
        outputY.setText(yText);


    }

    public void performModEuler()
    {
        String equation;
        equation = equationInput.getText();
        String expr = "ode(x, y) = " + equation;
        Function ode = new Function(expr);
        double a = Double.parseDouble(xIni.getText());
        double b = Double.parseDouble(xFin.getText());
        double h = Double.parseDouble(hInput.getText());
        double yIni =  Double.parseDouble(yInput.getText());

        EulerModifiedFunction answerStorage = new EulerModifiedFunction();
        answerStorage.odeModEuler(ode,a,b,h,yIni);
        String xText = "X Values: " + Arrays.toString(answerStorage.getxValues());
        outputX.setText(xText);
        String yText = "Y Values: " + Arrays.toString(answerStorage.getyValues());
        outputY.setText(yText);


    }

}
