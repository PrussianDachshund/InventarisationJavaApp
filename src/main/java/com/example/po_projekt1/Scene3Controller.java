package com.example.po_projekt1;

import baza_danych.Connect;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import klasy_inwentaryzacja.Strings;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Scene3Controller implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private final Connect database_connector = new Connect();

    @FXML
    private JFXToggleButton toggle;
    @FXML
    private Button btn2, btn3, btn4, add_guitar;
    @FXML
    private TableView tableview;
    @FXML
    private TabPane tab;
    @FXML
    private Label label_guitar;
    @FXML
    private TextField guitar_name,guitar_material,
            guitar_type, guitar_price, guitar_amount, guitar_color, guitar_frets, guitar_strings;

    //CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void button_disable(Button btn) {
        if(!ToggleState.state) {
            btn.setDisable(true);
        }
        else {
            btn.setDisable(false);
        }
    }

    private void toggleDisable() {
        button_disable(btn2);
        button_disable(btn3);
        button_disable(btn4);
    }
    //SQL///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void searchSQL() throws SQLException {
         String statement = "SELECT * FROM products";
        System.out.println(statement);
        ObservableList<ObservableList> data;
        data = FXCollections.observableArrayList();
        tableview.getItems().clear();
        tableview.getColumns().clear();
        tableview.refresh();
        try{
            ResultSet rs = Connect.select(statement);
            for(int i=0; i<rs.getMetaData().getColumnCount();i++) {
                final int j=i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tableview.getColumns().addAll(col);
            }
            while(rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1; i<=rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            tableview.setItems(data);

        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void addSQL(ActionEvent event) throws SQLException {
        System.out.println(tab.getSelectionModel().getSelectedIndex());

        switch (tab.getSelectionModel().getSelectedIndex()) {
            case 0:
                float price = Float.parseFloat(guitar_price.getText());
                int amount = Integer.parseInt(guitar_amount.getText());
                String name = guitar_name.getText();
                String type = guitar_type.getText();
                String material = guitar_material.getText();
                int fret = Integer.parseInt(guitar_frets.getText());
                int strings_am = Integer.parseInt(guitar_strings.getText());
                String color = guitar_color.getText();

                try {
                    Strings guitar = new Strings(price, amount, name, type, material, fret, strings_am, color);

                    String statement_1 = "INSERT INTO products(price, amount, available) VALUES (";
                    String statement_2 = guitar.getPrice() +", "+ guitar.getAmount() +", "+ guitar.isAvailable()+");";
                    String sql_1_2 = statement_1+statement_2;

                    try {
                        Connect.add(sql_1_2);
                        ResultSet rs = Connect.select("SELECT id FROM products ORDER BY 1 DESC LIMIT 1");
                        rs.next();
                        int ind = Integer.parseInt(rs.getString(1));
                        String statement_2_1 = "INSERT INTO strings(id_prod, name, type, material, colour," +
                                " fret_amount, strings_amount) VALUES (";
                        String statement_2_2 = ind + ", ";

                        label_guitar.setText("Dodano!");
                    }
                    catch (Exception e) {
                        System.out.println("Kurde");
                        label_guitar.setText("Błąd!");
                    }

                }
                catch (Exception e) {
                    System.out.println(e);
                    label_guitar.setText("Błąd!");
                }

                break;
            case 1:
                System.out.println(1);
                break;
            case 2:
                System.out.println(2);
                break;
            case 3:
                System.out.println(3);
                break;
            case 4:
                System.out.println(4);
                break;
            default: {}
        }



        tableview.getColumns().clear();
        tableview.getItems().clear();
        tableview.refresh();
        searchSQL();
    }
    //TOGGLE EVENTS/////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(ToggleState.state) {
            toggle.setSelected(true);
        }
       // System.out.println(ToggleState.state);
        toggleDisable();
        toggle.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {

                if(toggle.isSelected()) {
                    database_connector.db_connect();
                    if(database_connector.isDbOn()) {
                        ToggleState.state = toggle.isSelected();
                        toggleDisable();
                    }
                    else {
                        toggle.setSelected(false);
                    }
                }
                else if (!toggle.isSelected()) {
                    database_connector.db_disconnect();
                    ToggleState.state = toggle.isSelected();
                    toggleDisable();

                }
            }
        });
        try {
            searchSQL();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    //SCENE CONTROLLER////////////////////////////////////////////////////////////////////////////////////////////
    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        toggleDisable();
    }

    public void switchToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        toggleDisable();
    }

    public void switchToScene4(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scene4.fxml"));
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        toggleDisable();
    }

    public void switchToScene5(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scene5.fxml"));
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        toggleDisable();
    }

}
