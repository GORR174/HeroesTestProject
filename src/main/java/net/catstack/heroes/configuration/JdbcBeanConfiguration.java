package net.catstack.heroes.configuration;

import net.catstack.heroes.jdbc.SQLiteDBConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;

@Configuration
public class JdbcBeanConfiguration {
    @Bean
    public Connection getConnection(SQLiteDBConnector connector) {
        return connector.getConnection();
    }
}
