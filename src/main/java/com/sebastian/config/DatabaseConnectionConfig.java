package com.sebastian.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DatabaseConnectionConfig {

    private final String DATABASE_URL = "jdbc:mysql://localhost:3306/warehouse";
    private final String USER = "root";
    private final String PASSWORD = "";

    @Bean
    public Connection warehouseDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }


}
