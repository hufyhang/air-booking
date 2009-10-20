
package model;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author HANG Feifei
 */
public class DatabaseModel {

    private String serverName;
    private static DatabaseModel instance;
    private Connection connection;

    private static String databaseName = "airtickets";
    private static String databaseUsername = "root";
    private static String databasePassword = "password";
    private static String driver = "com.mysql.jdbc.Driver";

    protected DatabaseModel() {
    }

    public static DatabaseModel getInstance() {
        if( instance == null ) {
            instance = new DatabaseModel();
        }
        return instance;
    }

    public Connection getConnection() {
        createConnection();
        return connection;
    }

    public void createConnection() {
        if(connection == null)
        {
            try{
                Class.forName(driver);
                String url = "jdbc:mysql://" + getServerName() + "/" + databaseName;
                connection = DriverManager.getConnection(url, databaseUsername, databasePassword);
            } catch (Exception exp){
                JOptionPane.showMessageDialog(new JFrame(),
                    "Database connection could not be established.  Please" +
                            "check your network connection and restart.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
                exp.printStackTrace();
                connection = null;
            }
        }
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
