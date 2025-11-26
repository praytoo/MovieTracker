package org.movietracker.repository.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.movietracker.model.Movie;
import org.movietracker.repository.WishListRepository;

import java.sql.*;
import java.util.Scanner;

@AllArgsConstructor
public class WishListRepositoryImpl implements WishListRepository {
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public void displayAllMovies(Connection connection) throws SQLException {
        // create statement
        String query = "SELECT * FROM movies";
        PreparedStatement statement = connection.prepareStatement(query);

        // execute query
        ResultSet rs = statement.executeQuery();

        // process results
        while(rs.next()) {
            System.out.println("Movie id: " + rs.getInt("movie_id"));
            System.out.println("Date Released: " + rs.getString("date_released"));
            System.out.println("Genre: " + rs.getString("genre"));
            System.out.println("Title: " + rs.getString("title"));
            System.out.println("Average Percent Rating: " + rs.getInt("avg_percentage_rating"));
            System.out.println("Parental Rating: " + rs.getString("parental_rating"));
            System.out.println("Description: " + rs.getString("description"));
            System.out.println("--------------------");
        }
    }

    @Override
    public int promptMovieId() {
        System.out.println("What movie id would you like to add to your wish list?");
        int movieId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Adding movie to wishlist...");
        return movieId;
    }

    @Override
    public Movie findMovieByID(Connection connection, Integer movieId) throws SQLException {
        String query = "SELECT * FROM movies WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, movieId);
        // execute query
        ResultSet rs = statement.executeQuery();

        while(rs.next()){
            return null;
            //replace with movie object once we have that
        }
        return null;
        //null stays there in case no movie found
    }
    @Override
    public void addMovieToWishList(Connection connection, int movieId, int user_id) throws SQLException {
        String query = "INSERT INTO wish_list_relation (movie_id, user_id) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, movieId);
        statement.setInt(2, user_id);

        //execute query
        statement.executeUpdate();
    }
    @Override
    public void viewWishList(Connection connection, int user_id) throws SQLException {
        // create statement
        String query = "SELECT * \n" +
                "FROM movie_tracker.wish_list_relation AS wlr\n" +
                "JOIN movie_tracker.movies AS m\n" +
                "ON wlr.movie_id = m.movie_id\n" +
                "WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, user_id);

        // execute query
        ResultSet rs = statement.executeQuery();

        // process results
        while(rs.next()) {
            System.out.println("Id: " + rs.getInt("id"));
            System.out.println("Movie id: " + rs.getInt("movie_id"));
            System.out.println("User id: " + rs.getInt("user_id"));
            System.out.println("Title: " + rs.getString("title"));
            System.out.println("--------------------");
        }
    }
}
