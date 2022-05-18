package edu.fpdual.crescendo.model.connector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class responsible for creation of MySQL DB connection.
 * @author jose.m.prieto.villar
 *
 */
public class MySQLConnector {

    public Properties getProp() {
        return prop;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }

    Properties prop = new Properties();

    public MySQLConnector() {
        try {
            //Loads all the properties of file "config.properties".
            prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the connection object for a MySQL DDBB
     * @return a {@link Connection}
     */
    public Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
        try {

            //Indicates which driver is going to be used.
            Class.forName(prop.getProperty(MySQLConstants.DRIVER));

            //Creates the connection based on the obtained URL.
            return  DriverManager.getConnection(getURL());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Obtains the URL to connect to a MySQL DDBB.
     * @return an URL
     */
    private String getURL() {
        //jdbc:mysql://localhost:3306/world?user=sa&password=12345678&useSSL=false;
        return prop.getProperty(MySQLConstants.URL_PREFIX) +
                prop.getProperty(MySQLConstants.URL_HOST) + ":" +
                prop.getProperty(MySQLConstants.URL_PORT) + "/" +
                prop.getProperty(MySQLConstants.URL_SCHEMA) + "?user=" +
                prop.getProperty(MySQLConstants.USER) + "&password=" +
                prop.getProperty(MySQLConstants.PASSWD) + "&useSSL=" +
                prop.getProperty(MySQLConstants.URL_SSL) + ("&allowPublicKeyRetrieval=") +
                prop.getProperty(MySQLConstants.USE_JDBC_COMPLIANT_TIMEZONE_SHIFT) + ("&useLegacyDatetimeCode=") +
                prop.getProperty(MySQLConstants.USE_LEGACY_DATE_TIME_CODE) + ("&serverTimezone=") +
                prop.getProperty(MySQLConstants.SERVER_TIMEZONE);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MySQLConnector connector = new MySQLConnector();
        Connection connection = connector.getMySQLConnection();
        System.out.println(connection.getCatalog());
    }

}