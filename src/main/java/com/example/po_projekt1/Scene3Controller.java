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
import klasy_inwentaryzacja.*;

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
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private TableView tableview;
    @FXML
    private TabPane tab;
    @FXML
    private Label label_guitar, label_keys, label_perc, label_amp, label_col, label_del;
    @FXML
    private TextField guitar_name,guitar_material,
            guitar_type, guitar_price, guitar_amount, guitar_color, guitar_frets, guitar_strings;
    @FXML
    private TextField keys_name, keys_type, keys_price, keys_amount, keys_k_amount, keys_size, keys_power, keys_weight;
    @FXML
    private TextField perc_name, perc_type, perc_material, perc_size, perc_price, perc_amount;
    @FXML
    private TextField amp_name, amp_type,amp_channel_am, amp_amount, amp_price, amp_eff_loop,amp_size, amp_power;
    @FXML
    private TextField col_name, col_power, col_weight, col_size, col_impedance, col_speaker_amount,
            col_price, col_amount;
    @FXML
    private TextField delete;

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
                        String statement_2_2 = ind + ", \""+ guitar.getName()+ "\", \""+guitar.getType()
                                + "\", \""+guitar.getMaterial()+ "\", \""+guitar.getColour()+ "\", "+guitar.getFret_amount()+
                                ", "+guitar.getStrings_amount()+");";
                        System.out.println(statement_2_1+statement_2_2);
                        Connect.add(statement_2_1+statement_2_2);
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
                price = Float.parseFloat(keys_price.getText());
                amount = Integer.parseInt(keys_amount.getText());
                name = keys_name.getText();
                type = keys_type.getText();
                int k_amount = Integer.parseInt(keys_k_amount.getText());
                float size = Float.parseFloat(keys_size.getText());
                float power = Float.parseFloat(keys_power.getText());
                float weight = Float.parseFloat(keys_weight.getText());

                try {
                    Keys keys_instr = new Keys(price, amount, name, type, k_amount, weight, power, size);
                    String statement_1 = "INSERT INTO products(price, amount, available) VALUES (";
                    String statement_2 = keys_instr.getPrice() +", "+ keys_instr.getAmount() +", "+ keys_instr.isAvailable()+");";
                    String sql_1_2 = statement_1+statement_2;
                    System.out.println(sql_1_2);

                    try {
                        Connect.add(sql_1_2);
                        ResultSet rs = Connect.select("SELECT id FROM products ORDER BY 1 DESC LIMIT 1");
                        rs.next();
                        int ind = Integer.parseInt(rs.getString(1));
                        String statement_2_1 = "INSERT INTO keys_instruments(id_prod, name, type, keys_amount," +
                                " weight, speaker_size, speaker_power) VALUES (";
                        String statement_2_2 = ind + ", \""+ keys_instr.getName()+ "\", \""+keys_instr.getType()
                                + "\", "+keys_instr.getKeys_amount()+ ", "+keys_instr.getWeight()+ ", "+keys_instr.getSpeaker_size()+
                                ", "+keys_instr.getSpeaker_power()+");";
                        System.out.println(statement_2_1+statement_2_2);
                        Connect.add(statement_2_1+statement_2_2);
                        label_keys.setText("Dodano!");
                    }
                    catch (Exception e) {
                        System.out.println("Kurde");
                        label_keys.setText("Błąd!");
                    }
                }
                catch (Exception e) {
                    System.out.println(e);
                }

                break;
            case 2:
                System.out.println(2);
                name = perc_name.getText();
                type = perc_type.getText();
                size = Float.parseFloat(perc_size.getText());
                material = perc_material.getText();
                amount = Integer.parseInt(perc_amount.getText());
                price = Float.parseFloat(perc_price.getText());


                try {
                    Percussive perc = new Percussive(price, amount, name, material, type, size);
                    String statement_1 = "INSERT INTO products(price, amount, available) VALUES (";
                    String statement_2 = perc.getPrice() +", "+ perc.getAmount() +", "+ perc.isAvailable()+");";
                    String sql_1_2 = statement_1+statement_2;
                    System.out.println(sql_1_2);
                    try {
                        Connect.add(sql_1_2);
                        ResultSet rs = Connect.select("SELECT id FROM products ORDER BY 1 DESC LIMIT 1");
                        rs.next();
                        int ind = Integer.parseInt(rs.getString(1));
                        String statement_2_1 = "INSERT INTO percussive(id_prod, name, type, material," +
                                " size_percussive) VALUES (";
                        String statement_2_2 = ind + ", \""+ perc.getName()+ "\", \""+perc.getType()
                                + "\", \""+perc.getMaterial()+ "\", "+ perc.getSize()+");";
                        System.out.println(statement_2_1+statement_2_2);
                        Connect.add(statement_2_1+statement_2_2);
                        label_perc.setText("Dodano!");
                    }
                    catch (Exception e) {
                        System.out.println("Kurde");
                        label_perc.setText("Błąd!");
                    }
                }
                catch (Exception e) {
                    System.out.println(e);
                }

                break;
            case 3:
                System.out.println(3);
                name = amp_name.getText();
                type = amp_type.getText();
                power = Float.parseFloat(amp_power.getText());
                size = Float.parseFloat(amp_size.getText());
                boolean eff_loop = Boolean.parseBoolean(amp_eff_loop.getText());
                System.out.print("\n\n\n\n"+eff_loop+"\n\n\n\n");
                int channel_am = Integer.parseInt(amp_channel_am.getText());
                price = Float.parseFloat(amp_price.getText());
                amount = Integer.parseInt(amp_amount.getText());
                try {
                    Amplifier amp = new Amplifier(price, amount, name, power, size, channel_am, type, eff_loop);
                    String statement_1 = "INSERT INTO products(price, amount, available) VALUES (";
                    String statement_2 = amp.getPrice() +", "+ amp.getAmount() +", "+ amp.isAvailable()+");";
                    String sql_1_2 = statement_1+statement_2;
                    System.out.println(sql_1_2);
                    try {
                        Connect.add(sql_1_2);
                        ResultSet rs = Connect.select("SELECT id FROM products ORDER BY 1 DESC LIMIT 1");
                        rs.next();
                        int ind = Integer.parseInt(rs.getString(1));
                        String statement_2_1 = "INSERT INTO amplifiers(id_prod, name, type, power," +
                                " speaker_size, channels_amount, effect_loop) VALUES (";
                        String statement_2_2 = ind + ", \""+ amp.getName()+ "\", \""+amp.getType()
                                + "\", "+amp.getPower()+ ", "+ amp.getSpeaker_size()+", "+amp.getChannels_amount()+
                                ", "+amp.isEffect_loop()+");";
                        System.out.println(statement_2_1+statement_2_2);
                        Connect.add(statement_2_1+statement_2_2);
                        label_amp.setText("Dodano!");

                    }
                    catch (Exception e) {
                        System.out.println("Kurde");
                        label_amp.setText("Błąd!");
                    }
                }
                catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 4:
                System.out.println(4);
                name = col_name.getText();
                weight = Float.parseFloat(col_weight.getText());
                size = Float.parseFloat(col_size.getText());
                power = Float.parseFloat(col_power.getText());
                price = Float.parseFloat(col_price.getText());
                amount = Integer.parseInt(col_amount.getText());
                int speaker_am = Integer.parseInt(col_speaker_amount.getText());
                float imp = Float.parseFloat(col_impedance.getText());

                try {
                    Column column_amp = new Column(price, amount, name, power, size, imp,weight, speaker_am);
                    String statement_1 = "INSERT INTO products(price, amount, available) VALUES (";
                    String statement_2 = column_amp.getPrice() +", "+ column_amp.getAmount() +", "+ column_amp.isAvailable()+");";
                    String sql_1_2 = statement_1+statement_2;
                    System.out.println(sql_1_2);
                    try {
                        Connect.add(sql_1_2);
                        ResultSet rs = Connect.select("SELECT id FROM products ORDER BY 1 DESC LIMIT 1");
                        rs.next();
                        int ind = Integer.parseInt(rs.getString(1));
                        String statement_2_1 = "INSERT INTO columns(id_prod, name, power," +
                                " speaker_size, speakers_amount, impedance, weight) VALUES (";
                        String statement_2_2 = ind + ", \""+ column_amp.getName()+ "\", "+column_amp.getPower()+ ", "
                                + column_amp.getSpeaker_size()+", "+column_amp.getSpeaker_size()+", "+
                                column_amp.getImpedance()+", "+column_amp.getWeight()+");";
                        System.out.println(statement_2_1+statement_2_2);
                        Connect.add(statement_2_1+statement_2_2);
                        label_col.setText("Dodano!");
                    }
                    catch (Exception e) {
                        System.out.println("Kurde");
                        label_col.setText("Błąd!");
                    }
                }
                catch (Exception e) {
                    System.out.println(e);
                }
                break;
            default: {}
        }



        tableview.getColumns().clear();
        tableview.getItems().clear();
        tableview.refresh();
        searchSQL();
    }
    @FXML
    private void delSQL(ActionEvent event) throws SQLException {
        int id = Integer.parseInt(delete.getText());
        String statement = "DELETE FROM products WHERE id="+id+";";

        try {
            Connect.del(statement);
            tableview.getColumns().clear();
            tableview.getItems().clear();
            tableview.refresh();
            searchSQL();
            label_del.setText("Usunięto!");
        }
        catch (Exception e) {
            label_del.setText("Błąd!");
        }
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
