package com.example.po_projekt1;
import baza_danych.Connect;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXToggleButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Scene1Controller implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private final Connect database_connector = new Connect();

    @FXML
    private JFXToggleButton toggle;
    @FXML
    private Button btn2, btn3, btn4;


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
