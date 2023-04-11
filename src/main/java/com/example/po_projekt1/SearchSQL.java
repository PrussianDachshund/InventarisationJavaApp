package com.example.po_projekt1;

import com.example.po_projekt1.baza_danych.Connect;
import com.example.po_projekt1.klasy_inwentaryzacja.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchSQL {
    static void searchSQL(TableView tableview) throws SQLException {
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
}
