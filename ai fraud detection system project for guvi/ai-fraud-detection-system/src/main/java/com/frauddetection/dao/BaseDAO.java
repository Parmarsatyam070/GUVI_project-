package com.frauddetection.dao;

import com.frauddetection.config.DatabaseConfig;

import java.sql.Connection;

public class BaseDAO {
    protected Connection getConnection() {
        return DatabaseConfig.getConnection();
    }
}