module com.example.po_projekt1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.po_projekt1 to javafx.fxml;
    exports com.example.po_projekt1;
}