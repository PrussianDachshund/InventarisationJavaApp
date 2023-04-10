package com.example.po_projekt1;

import baza_danych.Connect;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Scene2Controller implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private final Connect database_connector = new Connect();

    @FXML
    private JFXToggleButton toggle;
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
    private final String getRadioValueInString() {
        RadioButton rb = (RadioButton) group.getSelectedToggle();
        return rb.getText();
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
    private void SearchSQL(ActionEvent event) {
        System.out.println(category.getValue());
        System.out.println(price_field.getText());
        System.out.println(amount_field.getText());
        System.out.println(getRadioValueInString());
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
