package com.example.finallaptrinhweb.connection_pool;

public class Config {
    public static final String HOST_NAME = "localhost";
    public static final String DB_NAME = "webbanthuocthuy";
    public static final String DB_PORT = "3306";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "";
    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    public static final int DB_MIN_CONNECTIONS = 50;
    public static final int DB_MAX_CONNECTIONS = 100;
    public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/webbanthuocthuy30?useUnicode=yes&characterEncoding=UTF-8";

    public Config() {
    }
}