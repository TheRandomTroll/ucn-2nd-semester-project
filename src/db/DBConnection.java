package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A class used to create a connection to a database using JDBC.
 * Due to the fact that this class is going to be used multiple times,
 * and instantiation of a Connection class is costly, this
 * class uses the Singleton design pattern in its implementation.
 *
 * @see java.sql.Connection;
 */
public class DBConnection {
    private static final String SERVER_NAME = "hildur.ucn.dk";
    private static final int PORT_NUMBER = 1433;
    private static final String DATABASE_NAME = "dmaj0920_1086341";
    private static final String USERNAME = "dmaj0920_1086341";
    private static final String PASSWORD = "Password1!";
    private static Connection connection = null;

    private static DBConnection dbConnection = null;


    private DBConnection() throws SQLException {
        String urlString = String.format("jdbc:sqlserver://%s:%d;databaseName=%s", SERVER_NAME, PORT_NUMBER,
                DATABASE_NAME);
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            connection = DriverManager.getConnection(urlString, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Cannot access database. Exception: " + e.getMessage());
        }

        connection.setAutoCommit(false);
    }

    /**
     * Gets an instance of the Connection class.
     *
     * @return An instance of a Connection class.
     * @see java.sql.Connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Gets an instance of the DBConnection class.
     *
     * @return An instance of a DBConnection class.
     */
    public static DBConnection getInstance() throws SQLException {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }

        return dbConnection;
    }

    //closeDb: closes the connection to the database
    public static void closeConnection() {
        try {
            connection.close();
            dbConnection = null;
        } catch (Exception e) {
            System.out.println("Error trying to close the database " + e.getMessage());
        }
    }//end closeDB

    public static boolean instanceIsNull() {
        return dbConnection == null;
    }

    public boolean isDbConnected() {
        try (PreparedStatement ps = connection.prepareStatement("SELECT 1")) {
            return ps.execute();
        } catch (SQLException e) {
            return false;
        }
    }
}