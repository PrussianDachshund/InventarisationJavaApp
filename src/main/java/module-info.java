module com.example.po_projekt1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.jfxtras.styles.jmetro;


    opens com.example.po_projekt1 to javafx.fxml;
    exports com.example.po_projekt1;
}