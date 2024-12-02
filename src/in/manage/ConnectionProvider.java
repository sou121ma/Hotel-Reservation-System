package in.manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    static Connection conn;

    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String user = "root";
    private static final String password = "sql@24";

    public static Connection creaConnection() {
        if (conn != null) {
            return conn;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ce) {
            ce.getMessage();
        }
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

}
