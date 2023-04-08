package baza_danych;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Connect {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void wyp() {
        System.out.println("Kurde faja");
    }
}
