import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.geometry.*;
import javafx.stage.*;

public class GUI extends Application {

  @Override
  public void start(Stage primaryStage) {

    TabPane tabPane = new TabPane();

    Tab trap_tab = new Tab();
    trap_tab.setText("Trapezoidal Method");
    trap_tab.setClosable(false);
    TrapezoidalGrid trapezoidalGrid = new TrapezoidalGrid();
    trap_tab.setContent(trapezoidalGrid.getGrid());
    tabPane.getTabs().add(trap_tab);

    Tab rk4_tab = new Tab();
    rk4_tab.setText("Runge-Kutta 4");
    rk4_tab.setClosable(false);
    RK4Grid rk4Grid = new RK4Grid();
    rk4_tab.setContent(rk4Grid.getGrid());
    tabPane.getTabs().add(rk4_tab);

    StackPane root = new StackPane();
    root.getChildren().add(tabPane);

    Scene scene = new Scene(root, 800, 600);

    primaryStage.setTitle("GUI");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  public static void main(String[] args) {
    launch(args);
  }
}
