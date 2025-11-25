package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_tracker", "root", "yearup25");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
        ) {
            Map<String, String> columns = new HashMap<>();
            try(ResultSet rs = statement.executeQuery()) {
                for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    columns.put(rs.getMetaData().getColumnName(i), rs.getMetaData().getColumnTypeName(i));
                }

                while (rs.next()) {
                    for(String columnKey : columns.keySet()) {
                        if(columns.get(columnKey).equals("VARCHAR") || columns.get(columnKey).toUpperCase().contains("INT")) {
                            System.out.println(rs.getString(columnKey));
                        }
                    }
                    // make it throw exception, handled by catch of the outer block

                }
            }

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}