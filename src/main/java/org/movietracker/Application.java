package org.movietracker;

import org.apache.commons.dbcp2.BasicDataSource;
import org.example.UserInterface;

import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setUrl("jdbc:mysql://localhost:3306/movie_tracker");
        bds.setUsername(args[0]);
        bds.setPassword(args[1]);

        UserInterface cli = new UserInterface(bds);
        cli.start();
    }
}
