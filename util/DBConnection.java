package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    /**
     * Retrieves a singleton connection instance to the database.
     * @return Connection object.
     * @throws SQLException If a database access error occurs.
     * @throws IOException If an error occurs while reading the property file.
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Load database properties from the property file
                String connectionString = PropertyUtil.getPropertyString("dbconfig.properties");

                // Establish a connection to the database
                connection = DriverManager.getConnection(connectionString);

                // Debugging: Confirm successful connection
                System.out.println("Database connection established successfully!");

            } catch (SQLException e) {
                System.err.println("SQLException: " + e.getMessage());
                e.printStackTrace();
                throw new SQLException("Failed to establish a database connection: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("IOException: " + e.getMessage());
                e.printStackTrace();
                throw new SQLException("Failed to load database properties: " + e.getMessage());
            }
        }
        return connection;
    }
}
