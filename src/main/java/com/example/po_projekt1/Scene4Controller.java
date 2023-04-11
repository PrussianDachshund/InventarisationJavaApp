package com.example.po_projekt1;

import com.example.po_projekt1.baza_danych.Connect;
import com.example.po_projekt1.klasy_inwentaryzacja.Product;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;


public class Scene4Controller implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private final Connect database_connector = new Connect();

    @FXML
    private JFXToggleButton toggle;
    @FXML
    private Button btn2, btn3, btn4;
    @FXML
    private CheckBox available;
    @FXML
    private TextField id, name, price, amount;
    @FXML
    private TableView<ObservableList> tableview;
    @FXML
    private Label err;

    private void button_disable(Button btn) {
        if(!ToggleState.state) {
            btn.setDisable(true);
        }
        else {
            btn.setDisable(false);
        }
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void toggleDisable() {
        button_disable(btn2);
        button_disable(btn3);
        button_disable(btn4);
    }

    @FXML
    private void checkAvailable(KeyEvent event) {
        System.out.print(amount.getText()+"\n");
        if(isInteger(amount.getText())) {
            if(Integer.parseInt(amount.getText())>0) {
                available.setSelected(true);
            }
        }
        else available.setSelected(false);
    }
    private boolean checkAvailable() {
        System.out.print(amount.getText()+"\n");
        if(isInteger(amount.getText())) {
            if(Integer.parseInt(amount.getText())>0) {
                available.setSelected(true);
                return true;
            }
        }
        else available.setSelected(false);
        return false;
    }

    @FXML
    private void getRow() {
       ObservableList row_list = tableview.getSelectionModel().getSelectedItem();
       String[] ret = new String[5];
       for(int i = 0; i<5; i++) {
           ret[i] = (String) row_list.get(i);
       }
        id.setText(ret[0]);
        name.setText(ret[1]);
        price.setText(ret[2]);
        amount.setText(ret[3]);
        checkAvailable();
    }

    private void isAvailable() {
    }
    @FXML
    private void updateSQL(ActionEvent event) {
        String statement="UPDATE products SET name=\""+name.getText()+"\",  price="+price.getText()+
                ", amount="+amount.getText()+", available="+checkAvailable()+" WHERE id="+id.getText()+";";
        System.out.println(statement);
        try{
            Connect.update(statement);
            err.setText("Zaktualizowano!");
            SearchSQL.searchSQL(tableview);
        }
        catch (Exception e){
            System.out.println("Błąd!");
            err.setText("Błąd!");
        }
    }


    //TOGGLE EVENTS/////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        available.setDisable(true);
        try {
            SearchSQL.searchSQL(tableview);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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

    public void switchToScene3(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scene3.fxml"));
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
