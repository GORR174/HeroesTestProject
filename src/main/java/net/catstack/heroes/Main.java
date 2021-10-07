package net.catstack.heroes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection connection;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:heroes.db");

            if (!connection.isClosed()) {
                System.out.println("Connected to DB");
            }

            connection.close();

            if (connection.isClosed()) {
                System.out.println("DB connection is closed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
