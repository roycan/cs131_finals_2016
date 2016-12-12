import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
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

public class RK4Grid
{
  private GridPane grid;
  private Double[] T;
  private Double[] X;
  private Double[] Y;

  private int CELL_SIZE = 24;

  public RK4Grid()
  {
    grid = new GridPane();
    grid.setAlignment(Pos.TOP_LEFT);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(10, 10, 10, 10));

    Label xfunctionLabel = new Label("dx / dt: ");
    TextField xfunctionField = new TextField();
    xfunctionField.setPrefWidth(180);

    Label yfunctionLabel = new Label("dy / dt: ");
    TextField yfunctionField = new TextField();
    yfunctionField.setPrefWidth(180);

    Label fromLabel = new Label("t ranges from: ");
    TextField fromField = new TextField();
    fromField.setPrefWidth(60);

    Label toLabel = new Label("to: ");
    TextField toField = new TextField();
    toField.setPrefWidth(60);

    Label hLabel = new Label("h: ");
    TextField hField = new TextField();
    hField.setPrefWidth(60);

    Label x1Label = new Label("initial x: ");
    TextField x1Field = new TextField();
    x1Field.setPrefWidth(60);

    Label y1Label = new Label("initial y: ");
    TextField y1Field = new TextField();
    y1Field.setPrefWidth(60);

    Label aLabel = new Label("Coefficients for A:");
    String[] strA = {"0.5", "0.5", "1.0"};
    ObservableList<String> aList = FXCollections.observableArrayList(strA);
    ListView<String> aInput = new ListView<String>(aList);
    aInput.setEditable(true);
    aInput.setOrientation(Orientation.HORIZONTAL);

    aInput.setCellFactory(TextFieldListCell.forListView());

    aInput.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>()
    {
      @Override
      public void handle(ListView.EditEvent<String> t)
      {
        try
        {
          Double.parseDouble(t.getNewValue());
          aInput.getItems().set(t.getIndex(), t.getNewValue());
        }
        catch (NumberFormatException e){ System.out.println("Not a number!"); }
      }
    });

    Label bLabel = new Label("Coefficients for B:");
    String[][] strB = {{"0.5"},
                    {"0.0", "0.5"},
                    {"0.0", "0.0", "1.0"}};
    ObservableList<String> b2List = FXCollections.observableArrayList(strB[0]);
    ListView<String> b2Input = new ListView<String>(b2List);
    b2Input.setEditable(true);
    b2Input.setOrientation(Orientation.HORIZONTAL);

    b2Input.setCellFactory(TextFieldListCell.forListView());

    b2Input.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>()
    {
      @Override
      public void handle(ListView.EditEvent<String> t)
      {
        try
        {
          Double.parseDouble(t.getNewValue());
          b2Input.getItems().set(t.getIndex(), t.getNewValue());
        }
        catch (NumberFormatException e){ System.out.println("Not a number!"); }
      }
    });

    ObservableList<String> b3List = FXCollections.observableArrayList(strB[1]);
    ListView<String> b3Input = new ListView<String>(b3List);
    b3Input.setEditable(true);
    b3Input.setOrientation(Orientation.HORIZONTAL);

    b3Input.setCellFactory(TextFieldListCell.forListView());

    b3Input.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>()
    {
      @Override
      public void handle(ListView.EditEvent<String> t)
      {
        try
        {
          Double.parseDouble(t.getNewValue());
          b3Input.getItems().set(t.getIndex(), t.getNewValue());
        }
        catch (NumberFormatException e){ System.out.println("Not a number!"); }
      }
    });

    ObservableList<String> b4List = FXCollections.observableArrayList(strB[2]);
    ListView<String> b4Input = new ListView<String>(b4List);
    b4Input.setEditable(true);
    b4Input.setOrientation(Orientation.HORIZONTAL);

    b4Input.setCellFactory(TextFieldListCell.forListView());

    b4Input.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>()
    {
      @Override
      public void handle(ListView.EditEvent<String> t)
      {
        try
        {
          Double.parseDouble(t.getNewValue());
          b4Input.getItems().set(t.getIndex(), t.getNewValue());
        }
        catch (NumberFormatException e){ System.out.println("Not a number!"); }
      }
    });

    Label cLabel = new Label("Coefficients for C:");
    String[] strC = {"1.0", "2.0", "2.0", "1.0"};
    ObservableList<String> cList = FXCollections.observableArrayList(strC);
    ListView<String> cInput = new ListView<String>(cList);
    cInput.setEditable(true);
    cInput.setOrientation(Orientation.HORIZONTAL);

		cInput.setCellFactory(TextFieldListCell.forListView());

		cInput.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>()
    {
      @Override
      public void handle(ListView.EditEvent<String> t)
      {
        try
        {
          Double.parseDouble(t.getNewValue());
          cInput.getItems().set(t.getIndex(), t.getNewValue());
        }
        catch (NumberFormatException e){ System.out.println("Not a number!"); }
      }
		});

    HBox input_hbox = new HBox(3);
    input_hbox.setAlignment(Pos.CENTER_LEFT);
    input_hbox.getChildren().addAll(xfunctionLabel, xfunctionField,
                                      yfunctionLabel, yfunctionField);

    HBox input_hbox2 = new HBox(3);
    input_hbox2.setAlignment(Pos.CENTER_LEFT);
    input_hbox2.getChildren().addAll(fromLabel, fromField, toLabel, toField,
                                      hLabel, hField, x1Label, x1Field, y1Label, y1Field);

    GridPane inputlist_grid = new GridPane();
    inputlist_grid.setAlignment(Pos.CENTER_LEFT);
    inputlist_grid.add(aLabel, 0, 0);
    inputlist_grid.add(aInput, 1, 0);
    inputlist_grid.add(bLabel, 0, 1);
    inputlist_grid.add(b2Input, 1, 1);
    inputlist_grid.add(b3Input, 1, 2);
    inputlist_grid.add(b4Input, 1, 3);
    inputlist_grid.add(cLabel, 0, 4);
    inputlist_grid.add(cInput, 1, 4);

    TitledPane inputlists = new TitledPane("Non-classical RK4 Coefficients", inputlist_grid);
    inputlists.setExpanded(false);
    inputlists.setCollapsible(false);

    CheckBox nc_rk4 = new CheckBox("Use Non-Classical RK4");
    nc_rk4.selectedProperty().addListener(new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
          if (newValue == false) inputlists.setExpanded(newValue);
          inputlists.setCollapsible(newValue);
        }
    });
    GridPane.setValignment(nc_rk4, VPos.CENTER);

    Button btn = new Button("Calculate");

    GridPane output_grid = new GridPane();

    btn.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent e)
      {
        Double a; Double b; Double h; Double x1; Double y1;
        try { a = (!fromField.getText().isEmpty()) ? Double.parseDouble(fromField.getText()) : 0.0; }
        catch (NumberFormatException exception) { a = 0.0; }
        try { b = (!toField.getText().isEmpty()) ? Double.parseDouble(toField.getText()) : 0.0; }
        catch (NumberFormatException exception) { b = 0.0; }
        try { h = (!hField.getText().isEmpty()) ? Double.parseDouble(hField.getText()) : 0.5; }
        catch (NumberFormatException exception) { h = 0.5; }
        try { x1 = (!x1Field.getText().isEmpty()) ? Double.parseDouble(x1Field.getText()) : 0.0; }
        catch (NumberFormatException exception) { x1 = 0.0; }
        try { y1 = (!y1Field.getText().isEmpty()) ? Double.parseDouble(y1Field.getText()) : 0.0; }
        catch (NumberFormatException exception) { y1 = 0.0; }

        Double[][] ret;
        if (nc_rk4.isSelected())
        {
          Double[] A = new Double[strA.length];
          for (int i = 0; i < aList.size(); i++)
          {
            A[i] = Double.parseDouble(aList.get(i));
          }

          Double[][] B = new Double[strB.length][strB[strB.length - 1].length];
          for (int i = 0; i < b2List.size(); i++)
          {
            B[0][i] = Double.parseDouble(b2List.get(i));
          }
          for (int i = 0; i < b3List.size(); i++)
          {
            B[1][i] = Double.parseDouble(b3List.get(i));
          }
          for (int i = 0; i < b4List.size(); i++)
          {
            B[2][i] = Double.parseDouble(b4List.get(i));
          }

          Double[] C = new Double[strC.length];
          for (int i = 0; i < cList.size(); i++)
          {
            C[i] = Double.parseDouble(cList.get(i));
          }

          ret = RK4Class.RK4(xfunctionField.getText(), yfunctionField.getText(), a, b, h, x1, y1, A, B, C);
        }
        else
        {
          ret = RK4Class.RK4(xfunctionField.getText(), yfunctionField.getText(), a, b, h, x1, y1);
        }
        T = ret[0]; X = ret[1]; Y = ret[2];

        Label t_label = new Label("T");
        GridPane.setHalignment(t_label, HPos.CENTER);
        ObservableList<Double> t_list = FXCollections.observableArrayList(T);
        ListView<Double> t_view = new ListView<Double>(t_list);
        t_view.setPrefHeight(t_list.size() * CELL_SIZE + 2);
        t_view.setFixedCellSize(CELL_SIZE);

        Label x_label = new Label("X");
        GridPane.setHalignment(x_label, HPos.CENTER);
        ObservableList<Double> x_list = FXCollections.observableArrayList(X);
        ListView<Double> x_view = new ListView<Double>(x_list);
        x_view.setPrefHeight(x_list.size() * CELL_SIZE + 2);
        x_view.setFixedCellSize(CELL_SIZE);

        Label y_label = new Label("Y");
        GridPane.setHalignment(y_label, HPos.CENTER);
        ObservableList<Double> y_list = FXCollections.observableArrayList(Y);
        ListView<Double> y_view = new ListView<Double>(y_list);
        y_view.setPrefHeight(y_list.size() * CELL_SIZE + 2);
        y_view.setFixedCellSize(CELL_SIZE);

        GridPane list_grid = new GridPane();
        list_grid.setPrefWidth(240);

        list_grid.add(t_label, 0, 0);
        list_grid.add(t_view, 0, 1);
        list_grid.add(x_label, 1, 0);
        list_grid.add(x_view, 1, 1);
        list_grid.add(y_label, 2, 0);
        list_grid.add(y_view, 2, 1);

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
              writer.println(RK4Class.toCsvString(T, X, Y));
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

        NumberAxis txAxis = new NumberAxis();
        txAxis.setLabel("t");
        NumberAxis tyAxis = new NumberAxis();
        tyAxis.setLabel("t");
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("y");

        ObservableList<XYChart.Series<Number, Number>> x_data = FXCollections.observableArrayList();
        ObservableList<XYChart.Series<Number, Number>> y_data = FXCollections.observableArrayList();

        XYChart.Series<Number, Number> xSeries = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> ySeries = new XYChart.Series<Number, Number>();

        for (int i = 0; i < Math.min(X.length, Y.length); i++)
        {
          xSeries.getData().add(new XYChart.Data<Number, Number>(T[i], X[i]));
          ySeries.getData().add(new XYChart.Data<Number, Number>(T[i], Y[i]));
        }

        x_data.add(xSeries);
        y_data.add(ySeries);

        LineChart<Number, Number> xChart = new LineChart<Number, Number>(txAxis, xAxis);
        xChart.setData(x_data);
        xChart.setTitle("x");
        xChart.setLegendVisible(false);

        LineChart<Number, Number> yChart = new LineChart<Number, Number>(tyAxis, yAxis);
        yChart.setData(y_data);
        yChart.setTitle("y");
        yChart.setLegendVisible(false);

        VBox chartBox = new VBox(0);
        chartBox.getChildren().addAll(xChart, yChart);

        output_grid.getChildren().clear();
        output_grid.setAlignment(Pos.CENTER_LEFT);
        output_grid.add(list_pane, 0, 0);
        output_grid.add(csv_output, 0, 1);
        output_grid.add(chartBox, 1, 0, 1, 2);
      }
    });

    grid.add(input_hbox, 0, 0, 2, 1);
    grid.add(input_hbox2, 0, 1, 2, 1);
    grid.add(nc_rk4, 0, 2);
    grid.add(inputlists, 1, 2);
    grid.add(output_grid, 0, 3, 3, 1);
    grid.add(btn, 2, 2);
  }

  public GridPane getGrid()
  {
    return this.grid;
  }
}
