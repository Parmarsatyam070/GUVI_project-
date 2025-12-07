/*package com.frauddetection.dao;

import com.frauddetection.config.DatabaseConfig;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Provides JDBC Connection for subclasses.
 */
/*public class BaseDAO {
    protected Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }
}
*/






package com.frauddetection.dao;

import com.frauddetection.config.DatabaseConfig;

import java.sql.Connection;

public class BaseDAO {
    protected Connection getConnection() {
        return DatabaseConfig.getConnection();
    }
}






/*package com.frauddetection.dao;

import com.frauddetection.config.DatabaseConfig;

import java.sql.Connection;

public class BaseDAO {
    protected Connection getConnection() {
        return DatabaseConfig.getConnection();
    }
}
*/