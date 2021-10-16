package net.catstack.heroes.jdbc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class SQLiteDBConnector implements AutoCloseable {

    private Connection connection;

    public SQLiteDBConnector(@Value("${application.db-path}") String dbPath) {
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
