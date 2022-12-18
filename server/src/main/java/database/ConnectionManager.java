package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    //todo move to configs
    private final String url = "jdbc:mysql://localhost:3306/testdb";
    private final String username = "root";
    private final String password = "88102010ValekA";

    private static final ConnectionManager connectionManager = new ConnectionManager();

    private ConnectionManager() {
    }

    public static ConnectionManager getInstance() {
        return connectionManager;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
