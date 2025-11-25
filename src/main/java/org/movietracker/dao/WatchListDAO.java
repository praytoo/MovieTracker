package org.movietracker.dao;

import org.movietracker.model.Movie;
import org.movietracker.model.WatchList;
// We import * so we get Genre and ParentalRating too
import org.movietracker.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WatchListDAO {

    // 1. Get all movies in a user's watchlist
    public List<WatchList> getWatchList(int userId) {
        List<WatchList> userWatchList = new ArrayList<>();
        String sql = "SELECT * FROM Wish_List WHERE user_id = ?";

        // Connect to database and run the command
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            // Go through the results one by one
            while (rs.next()) {
                WatchList movie = new WatchList();
                // Copy info from database to our java object
                movie.setWishlistId(rs.getInt("wishlist_id"));
                movie.setUserId(rs.getInt("user_id"));
                movie.setMovieId(rs.getInt("movie_id"));
                movie.setTitle(rs.getString("title"));
                movie.setAvgPercentageRating(rs.getInt("avg_percentage_rating"));
                movie.setParentalRating(rs.getString("parental_rating"));
                movie.setDescription(rs.getString("description"));

                // Add it to our list
                userWatchList.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userWatchList;
    }

    // 2. Add a movie to user's watchlist
    public void addToWatchList(int userId, int movieId) {
        // Copy the movie info from Movies table to Wish_List table
        String sql = "INSERT INTO Wish_List (user_id, movie_id, title, avg_percentage_rating, parental_rating, description) " +
                "SELECT ?, movie_id, title, avg_percentage_rating, parental_rating, description " +
                "FROM Movies WHERE movie_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, movieId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Success! Movie " + movieId + " added to watchlist.");
            } else {
                System.out.println("Error: Could not find movie with ID " + movieId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Rate a movie (Updates the real Movies table)
    public void postMovieRating(int userId, int movieId, int rating) {
        String sql = "UPDATE Movies SET individual_star_rating = ? WHERE movie_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rating);
            stmt.setInt(2, movieId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Success! You rated movie " + movieId);
            } else {
                System.out.println("Error: Movie not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4. Get top 5 movies (Required: Use Java Streams)
    public List<Movie> getTop5Movies() {
        List<Movie> allMovies = new ArrayList<>();
        String sql = "SELECT * FROM Movies";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Get every single movie from database first
            while (rs.next()) {
                // Since the Lead used Lombok, we use .builder() to make the object
                // Note: We skip Genre/Actors for now to keep it simple and safe
                Movie movie = Movie.builder()
                        .movie_id(rs.getDouble("movie_id"))
                        .title(rs.getString("title"))
                        .avrRating(rs.getDouble("avg_percentage_rating"))
                        .description(rs.getString("description"))
                        .build();

                allMovies.add(movie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // NOW we use Streams (This is the requirement!)
        // 1. Stream the list
        // 2. Sort by rating (high to low)
        // 3. Keep only top 5
        // 4. Turn back into a List
        return allMovies.stream()
                .sorted(Comparator.comparingDouble(Movie::getAvrRating).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }
}