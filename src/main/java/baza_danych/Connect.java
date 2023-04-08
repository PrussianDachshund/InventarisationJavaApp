package baza_danych;
import java.sql.*;

public class Connect {
    private final String url = "jdbc:mysql://localhost:3306/shop";
    static Connection conn;
    public final void db_connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url,"root","");
            System.out.println("DB connected");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public final void db_disconnect() {
        try {
            conn.close();
            System.out.println("DB connection closed");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static ResultSet select() {
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM strings");
            return rs;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public static void print(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int col = 1; col<= rsmd.getColumnCount(); col++){
                System.out.print(rsmd.getColumnName(col) + " ");
            }
            System.out.println("");
            while(rs.next()) {
                for (int col = 1; col<= rsmd.getColumnCount(); col++){
                    if (col > 1) System.out.print(",  ");
                    String columnValue = rs.getString(col);
                    System.out.print(columnValue + " ");
                }
                System.out.println("");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void add() {}
    public static void del() {}
    public static void update() {}

}
