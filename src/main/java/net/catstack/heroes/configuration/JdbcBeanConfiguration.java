package net.catstack.heroes.configuration;

import net.catstack.heroes.jdbc.DBConnector;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;

@Configuration
public class JdbcBeanConfiguration {
    @Bean
    public Connection getConnection(@Qualifier("mysql") DBConnector connector) {
        return connector.getConnection();
    }
}
