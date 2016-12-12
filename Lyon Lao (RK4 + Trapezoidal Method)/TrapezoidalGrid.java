import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.geometry.*;
import javafx.stage.*;

import javafx.collections.*;
import javafx.beans.value.*;
import javafx.util.*;
import java.util.*;

import java.io.*;

public class TrapezoidalGrid
{
  private GridPane grid;
  private Double I;
  private Double[] X;
  private Double[] F;

  public TrapezoidalGrid()
  {
    grid = new GridPane();
    grid.setAlignment(Pos.TOP_LEFT);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(10, 10, 10, 10));

    Label functionLabel = new Label("Integrate");
    TextField functionField = new TextField();
    functionField.setPrefWidth(180);

    Label fromLabel = new Label("from");
    TextField fromField = new TextField();
    fromField.setPrefWidth(60);

    Label toLabel = new Label("to");
    TextField toField = new TextField();
    toField.setPrefWidth(60);

    Label intervalLabel1 = new Label("with");
    TextField intervalField = new TextField();
    intervalField.setPrefWidth(60);
    Label intervalLabel2 = new Label("intervals.");

    HBox input_hbox = new HBox(3);
    input_hbox.setAlignment(Pos.CENTER_LEFT);
    input_hbox.getChildren().addAll(functionLabel, functionField,
                                      fromLabel, fromField, toLabel, toField,
                                      intervalLabel1, intervalField, intervalLabel2);

    Button btn = new Button("Calculate");

    GridPane output_grid = new GridPane();

    btn.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent e)
      {
        Double a; Double b; Integer n;
        try { a = (!fromField.getText().isEmpty()) ? Double.parseDouble(fromField.getText()) : 0.0; }
        catch (NumberFormatException exception) { a = 0.0; }
        try { b = (!toField.getText().isEmpty()) ? Double.parseDouble(toField.getText()) : 0.0; }
        catch (NumberFormatException exception) { b = 0.0; }
        try { n = (!intervalField.getText().isEmpty()) ? Integer.parseInt(intervalField.getText()) : 1; }
        catch (NumberFormatException exception) { n = 1; }

        Double[][] ret = TrapezoidalClass.Trapezoidal(functionField.getText(), a, b, n);
        I = ret[0][0]; X = ret[1]; F = ret[2];

        int CELL_SIZE = 24;

        Label x_label = new Label("X");
        GridPane.setHalignment(x_label, HPos.CENTER);
        ObservableList<Double> x_list = FXCollections.observableArrayList(X);
        ListView<Double> x_view = new ListView<Double>(x_list);
        x_view.setPrefHeight(x_list.size() * CELL_SIZE + 2);
        x_view.setFixedCellSize(CELL_SIZE);
        x_view.setCellFactory(param -> new ListCell<Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);

                if (item != null && !empty) {
                    setText(String.format("%.3f", item));
                } else {
                    setText(null);
                }
            }
        });

        Label f_label = new Label("F(x)");
        GridPane.setHalignment(f_label, HPos.CENTER);
        ObservableList<Double> f_list = FXCollections.observableArrayList(F);
        ListView<Double> f_view = new ListView<Double>(f_list);
        f_view.setPrefHeight(f_list.size() * CELL_SIZE + 2);
        f_view.setFixedCellSize(CELL_SIZE);
        f_view.setCellFactory(param -> new ListCell<Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);

                if (item != null && !empty) {
                    setText(String.format("%.3f", item));
                } else {
                    setText(null);
                }
            }
        });

        GridPane list_grid = new GridPane();
        list_grid.setPrefWidth(240);

        list_grid.add(x_label, 0, 0);
        list_grid.add(x_view, 0, 1);
        list_grid.add(f_label, 1, 0);
        list_grid.add(f_view, 1, 1);

        ScrollPane list_pane = new ScrollPane();
        list_pane.setPrefWidth(255);
        list_pane.setContent(list_grid);

        Label csv_label = new Label("CSV file name:");
        TextField csv_filename = new TextField();
        Button csv_button = new Button("Output Data as CSV");
        Label csv_filewritestatus = new Label();
        csv_filewritestatus.setMaxWidth(240);
        csv_button.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent e)
          {
            try
            {
              PrintWriter writer = new PrintWriter(csv_filename.getText(), "UTF-8");
              writer.println(TrapezoidalClass.toCsvString(I, X, F));
              writer.close();
              csv_filewritestatus.setText("Successfully wrote to \"" + csv_filename.getText() + "\"");
            }
            catch (IOException io_e)
            {
              csv_filewritestatus.setText("Error writing to \"" + csv_filename.getText() + "\"");
            }
          }
        });

        GridPane csv_output = new GridPane();
        csv_output.add(csv_label, 0, 0);
        csv_output.add(csv_filename, 1, 0);
        csv_output.add(csv_button, 0, 1, 2, 1);
        csv_output.add(csv_filewritestatus, 0, 2, 2, 1);

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("F(x)");
        ObservableList<XYChart.Series<Number, Number>> data = FXCollections.observableArrayList();

        XYChart.Series<Number, Number> lineSeries = new XYChart.Series<Number, Number>();
        lineSeries.setName(functionField.getText());

        for (int i = 0; i < Math.min(X.length, F.length); i++)
        {
          lineSeries.getData().add(new XYChart.Data<Number, Number>(X[i], F[i]));
        }

        data.add(lineSeries);

        AreaChart<Number, Number> areaChart = new AreaChart<Number, Number>(xAxis, yAxis, data);
        areaChart.setTitle("Output Graph");

        Label i_output = new Label();
        i_output.setFont(new Font(20));
        GridPane.setHalignment(i_output, HPos.LEFT);
        i_output.setText(String.format("I = %G", I));

        output_grid.getChildren().clear();
        output_grid.setAlignment(Pos.CENTER_LEFT);
        output_grid.add(i_output, 0, 0);
        output_grid.add(list_pane, 0, 1);
        output_grid.add(csv_output, 0, 2);
        output_grid.add(areaChart, 1, 0, 1, 3);
      }
    });

    grid.add(input_hbox, 0, 0);
    grid.add(output_grid, 0, 1, 2, 1);
    grid.add(btn, 1, 0);
  }

  public GridPane getGrid()
  {
    return this.grid;
  }
}
