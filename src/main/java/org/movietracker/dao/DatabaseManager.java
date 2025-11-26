package org.movietracker.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    // Address of database
    private static final String URL = "jdbc:mysql://localhost:3306/movie_tracker";
    // Your username
    private static final String USER = "root";
    // Your password (I found this in your Main.java)
    private static final String PASSWORD = "yearup25";

    // This method creates the connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}