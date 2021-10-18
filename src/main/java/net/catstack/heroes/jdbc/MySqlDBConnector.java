package net.catstack.heroes.jdbc;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component("mysql")
public class MySqlDBConnector implements DBConnector {

    private Connection connection;

    public MySqlDBConnector() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3309/db", "gorr", "pswd123");

            if (!connection.isClosed()) {
                System.out.println("Connected to MySql DB");
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
