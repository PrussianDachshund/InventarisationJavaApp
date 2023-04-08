package baza_danych;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Connect {
    private final static String url = "jdbc:mysql://localhost:3306/shop";
    static Connection conn;
    public static void db_connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url,"root","");
            System.out.println("DB connected");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void db_disconnect() {
        try {
            conn.close();
            System.out.println("DB connection closed");
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    public void wyp() {
        System.out.println("Kurde faja");
    }
}
