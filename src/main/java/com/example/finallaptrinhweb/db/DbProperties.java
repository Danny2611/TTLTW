package com.example.finallaptrinhweb.db;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DbProperties {
    private static Properties prop = new Properties();
    public static String host;
    public static String port;
    public static String username;
    public static String password;
    public static String dbname;
    public static String FROM_EMAIL;
    public static String PASSWORD;
    public static String SMTP_HOST;
    public static String SMTP_PORT;
    public static String AUTH;
    public static String STARTTLS_ENABLE;
    public static String SSL_PROTOCOLS;
    public static String GOOGLE_CLIENT_ID;
    public static String GOOGLE_CLIENT_SECRET;
    public static String GOOGLE_REDIRECT_URI;
    public static String GOOGLE_LINK_GET_TOKEN;
    public static String GOOGLE_LINK_GET_USER_INFO;
    public static String GOOGLE_GRANT_TYPE;
    public static String AUTH_ENDPOINT;
    public DbProperties() {
    }

    static {
        try {
            File f = new File("/db.properties");
            if (f.exists()) {
                prop.load(new FileInputStream(f));
            } else {
                prop.load(DbProperties.class.getClassLoader().getResourceAsStream("db.properties"));
            }
        } catch (IOException var1) {
            throw new RuntimeException(var1);
        }

        host = prop.getProperty("db.host");
        port = prop.getProperty("db.port");
        username = prop.getProperty("db.username");
        password = prop.getProperty("db.pass");
        dbname = prop.getProperty("db.name");
        FROM_EMAIL = prop.getProperty("FROM_EMAIL");
        PASSWORD = prop.getProperty("PASSWORD");
        SMTP_HOST = prop.getProperty("SMTP_HOST");
        SMTP_PORT = prop.getProperty("SMTP_PORT");
        AUTH = prop.getProperty("AUTH");
        STARTTLS_ENABLE = prop.getProperty("STARTTLS_ENABLE");
        SSL_PROTOCOLS = prop.getProperty("SSL_PROTOCOLS");
        GOOGLE_CLIENT_ID = prop.getProperty("GOOGLE_CLIENT_ID");
        GOOGLE_CLIENT_SECRET = prop.getProperty("GOOGLE_CLIENT_SECRET");
        GOOGLE_REDIRECT_URI = prop.getProperty("GOOGLE_REDIRECT_URI");
        GOOGLE_LINK_GET_TOKEN = prop.getProperty("GOOGLE_LINK_GET_TOKEN");
        GOOGLE_LINK_GET_USER_INFO = prop.getProperty("GOOGLE_LINK_GET_USER_INFO");
        GOOGLE_GRANT_TYPE = prop.getProperty("GOOGLE_GRANT_TYPE");
        AUTH_ENDPOINT = prop.getProperty("AUTH_ENDPOINT");
    }
}