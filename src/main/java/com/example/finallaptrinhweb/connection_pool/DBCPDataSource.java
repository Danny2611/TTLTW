package com.example.finallaptrinhweb.connection_pool;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBCPDataSource {
    private static BasicDataSource ds = new BasicDataSource();
    private static Connection connection;

    private DBCPDataSource() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        } else {
            connection = ds.getConnection();
            return connection;
        }
    }

    public static Statement getStatement() throws SQLException {
        return getConnection().createStatement();
    }

    public static PreparedStatement preparedStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }

    public static DataSource getDataSource() {
        return ds;
    }

    static {
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/webbanthuocthuy?useUnicode=yes&characterEncoding=UTF-8");
        ds.setUsername("root");
        ds.setPassword("");
        ds.setMinIdle(50);
        ds.setInitialSize(50);
        ds.setMaxIdle(100);
        ds.setMaxOpenPreparedStatements(100);
    }
}
