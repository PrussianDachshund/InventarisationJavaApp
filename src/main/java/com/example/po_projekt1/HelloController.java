package com.example.po_projekt1;

import javafx.geometry.Insets;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label home_pict;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}