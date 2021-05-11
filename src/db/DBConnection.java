package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String SERVER_NAME = "hildur.ucn.dk";
    private static final int PORT_NUMBER = 1433;
    private static final String DATABASE_NAME = "dmaj0920_1086341";
    private static final String USERNAME = "dmaj0920_1086341";
    private static final String PASSWORD = "Password1!";
    private Connection connection = null;

    private static DBConnection dbConnection = null;

    private DBConnection() {
        String urlString = String.format("jdbc:sqlserver://%s:%d;databaseName=%s", SERVER_NAME, PORT_NUMBER,
                DATABASE_NAME);
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            connection = DriverManager.getConnection(urlString, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Cannot access database. Exception: " + e.getMessage());
            return;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }

        return dbConnection;
    }
}