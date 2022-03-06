package Daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Denis
 */
public class Dao {

    private String databaseName;

    private Connection con;

    // By providing a constructor that takes the connection as a parameter
    // we provide a way to take in a mock object and replace our REAL
    // connection with a mock
    public Dao(Connection con) {
        this.con = con;
    }

    public Dao(String databaseName) {
        this.databaseName = databaseName;
    }

    public Connection getConnection() {
        // If there was no connection provided, make one
        if (con == null) {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/" + databaseName;
            String username = "root";
            String password = "";
            Connection tempCon = null;
            try {
                Class.forName(driver);
                tempCon = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException ex1) {
                System.out.println("Failed to find driver class " + ex1.getMessage());
                System.exit(1);
            } catch (SQLException ex2) {
                System.out.println("Connection failed " + ex2.getMessage());
            }
            return tempCon;
        } else // If there was a connection provided, use that one
        {
            return con;
        }
    }

    public void freeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            System.out.println("Failed to free connection: " + e.getMessage());
            System.exit(1);
        }
    }
}
