package com.frauddetection.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConfig {

    private static String url;
    private static String username;
    private static String password;

    static {
        try {
            Properties props = new Properties();
            props.load(DatabaseConfig.class.getClassLoader()
                    .getResourceAsStream("config/database.properties"));

            url = props.getProperty("jdbc:mysql://localhost:3306/fraud_detection_db");
            username = props.getProperty("root");
            password = props.getProperty("Parmarsatyam09@#");

            Class.forName(props.getProperty("db.driver"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new RuntimeException("Database connection failed", e);
        }
    }
}
