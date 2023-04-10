package com.example.po_projekt1;

import baza_danych.Connect;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.property.ReadOnlyObjectWrapper;
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

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;


public class Scene2Controller implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private final Connect database_connector = new Connect();
    private String statement;

    @FXML
    private JFXToggleButton toggle, and_or_1, and_or_2;
    @FXML
    private Button btn2, btn3, btn4, btn_search;
    @FXML
    private ComboBox category;
    @FXML
    private TextField price_field, amount_field;
    @FXML
    private RadioButton available_radio, not_available_radio;
    @FXML
    private final ToggleGroup group = new ToggleGroup();
    @FXML
    private TableView tableview;
    private ObservableList data;

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
        button_disable(btn_search);
    }
    private final void comboSearchInit() {
        category.getItems().removeAll(category.getItems());
        category.getItems().addAll("Produkty","Gitary","Klawisze","Perkusyjne","Wzmacniacze","Kolumny");
        category.getSelectionModel().select(0);
    }
    private final void setToogleGrp() {
        available_radio.setToggleGroup(group);
        not_available_radio.setToggleGroup(group);
    }
    private final int getRadioValueInString() {
        RadioButton rb = (RadioButton) group.getSelectedToggle();
        if((rb.getText().equals("Dostępny"))) {
            return 1;
        }
        else return 0;
    }

    //INITIALIZATION////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setToogleGrp();

        if(ToggleState.state) {
            toggle.setSelected(true);
        }

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
        comboSearchInit();
    }

    //SEARCH SQL////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void searchSQL(ActionEvent event) throws SQLException {
        category.getValue();
        price_field.getText();
        amount_field.getText();
        getRadioValueInString();
        String sql_query_1 = "SELECT * FROM products";
        String sql_query_2 = "";
        String sql_query_3 = "";
        String sql_join = "";
        switch ((String) category.getValue()) {
            case "Produkty":
                sql_query_2 = "products";
                break;
            case "Gitary":
                sql_query_1 = "SELECT products.id, strings.id, name, type, material, colour, fret_amount, " +
                        "strings_amount, products.amount, products.price FROM products";
                sql_query_2 = "strings";
                sql_join += " JOIN "+sql_query_2 + " ON products.id="+sql_query_2+".id_prod";
                break;
            case "Klawisze":
                sql_query_1 = "SELECT products.id, keys_instruments.id, name, type, keys_amount, weight," +
                        " speaker_power, speaker_size, products.amount, products.price FROM products";
                sql_query_2 = "keys_instruments";
                sql_join += " JOIN "+sql_query_2 + " ON products.id="+sql_query_2+".id_prod";
                break;
            case "Perkusyjne":
                sql_query_1 = "SELECT products.id, percussive.id, name, type, material, size_percussive, " +
                        "products.amount, products.price FROM products";
                sql_query_2 = "percussive";
                sql_join += " JOIN "+sql_query_2 + " ON products.id="+sql_query_2+".id_prod";
                break;
            case "Wzmacniacze":
                sql_query_1 = "SELECT products.id, amplifiers.id, name, type, power, speaker_size, channels_amount," +
                        " effect_loop, products.amount, products.price FROM products";
                sql_query_2 = "amplifiers";
                sql_join += " JOIN "+sql_query_2 + " ON products.id="+sql_query_2+".id_prod";
                break;
            case "Kolumny":
                sql_query_1 = "SELECT products.id, columns.id, name, power, speaker_size," +
                        " speakers_amount, impedance, weight, inputs_amount, products.amount," +
                        " products.price FROM products";
                sql_query_2 = "columns";
                sql_join += " JOIN "+sql_query_2 + " ON products.id="+sql_query_2+".id_prod";
                break;
            default: {
            }
        }
        statement = sql_query_1+sql_join+sql_query_3+whereClause();
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
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>, ObservableValue<String>>() {
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

    private String whereClause() {
        String sql_query_4 = "";
        String sql_query_5 = "";
        String sql_query_6 = "";
        String sql_and_or_1 = "";
        String sql_and_or_2= "";
        String sql_query_7 = ";";
        if(getRadioValueInString() == 1) {
            sql_query_4 = " available=1";
        }
        else if(getRadioValueInString() == 0){
            sql_query_4 = " available=0";
        }

        if(!(price_field.getText().equals(""))) {
            if(and_or_1.isSelected()) {
                sql_and_or_1 = " OR";
            }
            else sql_and_or_1 = " AND";

            sql_query_5 = sql_and_or_1 + " price" + price_field.getText();
        }
        if(!(amount_field.getText().equals(""))) {
            if(and_or_2.isSelected()) {
                sql_and_or_2 = " OR";
            }
            else sql_and_or_2 = " AND";
            sql_query_6 =sql_and_or_2 + " amount" + amount_field.getText();
        }

        return " WHERE"+sql_query_4+sql_query_5+sql_query_6+sql_query_7;
    }

    private void sqlTablePrint(String statement) throws SQLException {
        ResultSet rs = Connect.select(statement);

        //DYNAMIC TABLE//

    }
    //SCENE CONTROLLER//////////////////////////////////////////////////////////////////////////////////////////////////
    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        toggleDisable();
    }

    public void switchToScene3(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scene3.fxml"));
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
