package in.manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {
    static Connection conn;

    private   String url ="jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}";
    private   String user ="${DB_USER}";
    private   String password="${DB_PASSWORD}";

    public ConnectionProvider(){

        // Simulate environment variables (or use actual system environment variables)
        System.setProperty("DB_HOST", "localhost");
        System.setProperty("DB_PORT", "3306");
        System.setProperty("DB_NAME", "hotel_db");
        System.setProperty("DB_USER","root");
        System.setProperty("DB_PASSWORD", "sql@24");

        url = replacePlaceholders(url);
        user = replacePlaceholders(user);
        password = replacePlaceholders(password);

    }
    
    public Connection creaConnection() {
        
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

    private String replacePlaceholders(String input) {
        Properties props = System.getProperties(); // Get system properties

        for (String key : props.stringPropertyNames()) {
            input = input.replace("${" + key + "}", props.getProperty(key));
        }
        return input;
    }

}
