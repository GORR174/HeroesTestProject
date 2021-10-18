package net.catstack.heroes.jdbc;

import java.sql.Connection;

public interface DBConnector extends AutoCloseable {
    Connection getConnection();
}
