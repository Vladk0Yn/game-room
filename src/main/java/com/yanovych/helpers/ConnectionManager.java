package com.yanovych.helpers;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class ConnectionManager {
    private String CONNECTION_URL;
    private String USER;
    private String PASSWORD;

    private static ConnectionManager instance;

    private ConnectionManager() {
        readConnectionProperties();
    }

    public static ConnectionManager getConnectionManager() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    private void readConnectionProperties() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream("db.properties")) {

            Properties properties = new Properties();
            properties.load(is);

            this.CONNECTION_URL = properties.getProperty("url");
            this.USER = properties.getProperty("user");
            this.PASSWORD = properties.getProperty("password");

        } catch (IOException exception) {
            log.error("Db properties file not found");
        };
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
    }
}
