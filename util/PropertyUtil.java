package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

    /**
     * Reads database connection properties from a property file and constructs a connection string.
     * @param propertyFileName The name of the property file.
     * @return The connection string for the database.
     * @throws IOException If an error occurs while reading the property file.
     */
    public static String getPropertyString(String propertyFileName) throws IOException {
        Properties properties = new Properties();

        // Load the properties from the specified file
        System.out.println("Loading properties from: " + propertyFileName);  // Debugging: check the file path
        try (FileInputStream fis = new FileInputStream(propertyFileName)) {
            properties.load(fis);
        }

        // Extract connection properties
        String hostname = properties.getProperty("hostname");
        String dbname = properties.getProperty("dbname");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String port = properties.getProperty("port");

        // Debugging: print the loaded properties
        System.out.println("Loaded properties: ");
        System.out.println("Hostname: " + hostname);
        System.out.println("Database: " + dbname);
        System.out.println("Username: " + username);
        System.out.println("Port: " + port);

        // Construct the connection string
        String connectionString = "jdbc:mysql://" + hostname + ":" + port + "/" + dbname + 
               "?user=" + username + "&password=" + password;

        System.out.println("Constructed connection string: " + connectionString);  // Debugging: check the connection string

        return connectionString;
    }
}
