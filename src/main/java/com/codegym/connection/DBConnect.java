package com.codegym.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/chiendemo?allowPublicKeyRetrieval=true&useSSL=false";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "12345678";

    public static Connection getConnection() {
        Connection connection = null;

        String url = JDBC_URL;
        String username = USERNAME;
        String password = PASSWORD;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);

            System.out.println("Connected successfully");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error connection " + ex);
        }

        // create connection
        return connection;
    }
}