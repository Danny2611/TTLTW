package com.example.finallaptrinhweb.db;


import com.example.finallaptrinhweb.model.User;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.jdbi.v3.core.Jdbi;

public class JDBIConnector {
    private static final JDBIConnector instance = new JDBIConnector();
    private static Jdbi jdbi;

    public static JDBIConnector me() {
        return instance;
    }

    private JDBIConnector() {
    }

    private static void makeConnect() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://" + DbProperties.host + ":" + DbProperties.port + "/" + DbProperties.dbname);
        dataSource.setUser(DbProperties.username);
        dataSource.setPassword(DbProperties.password);

        try {
            dataSource.setUseCompression(true);
            dataSource.setAutoReconnect(true);
        } catch (SQLException var2) {
            var2.printStackTrace();
            throw new RuntimeException(var2);
        }

        jdbi = Jdbi.create(dataSource);
    }

    public static Jdbi get() throws SQLException {
        if (jdbi == null) {
            makeConnect();
        }

        return jdbi;
    }

    public static void main(String[] args) throws SQLException {
        me();
        List<User> users = (List) get().withHandle((handle) -> {
            return (List) handle.createQuery("SELECT * FROM users").mapToBean(User.class).collect(Collectors.toList());
        });
        System.out.println(users);
    }
}
