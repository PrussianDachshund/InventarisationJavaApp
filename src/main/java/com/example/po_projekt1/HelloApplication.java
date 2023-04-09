package com.example.po_projekt1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.styles.jmetro.JMetro;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 480);
        stage.setTitle("Hello!");
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
        JMetro jmetro = new JMetro();
    }

    public static void main(String[] args) {
        launch();
    }
}