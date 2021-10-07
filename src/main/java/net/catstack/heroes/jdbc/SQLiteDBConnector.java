package net.catstack.heroes.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDBConnector implements AutoCloseable {

    private Connection connection;

    public SQLiteDBConnector(String dbPath) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

            if (!connection.isClosed()) {
                System.out.println("Connected to DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        try {
            connection.close();

            if (connection.isClosed()) {
                System.out.println("DB connection is closed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
