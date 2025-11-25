package org.movietracker.dao;

import org.movietracker.model.WatchList;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WatchListDAO {

    // 1. Get all movies in a user's watchlist
    public List<WatchList> getWatchList(int userId) {
        List<WatchList> userWatchList = new ArrayList<>();

        // Our SQL command
        String sql = "SELECT * FROM Wish_List WHERE user_id = ?";

        // Try to connect to the database
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // The '?' in the SQL to the actual userId
            stmt.setInt(1, userId);

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Loop through every row we found in the database
            while (rs.next()) {
                // Create a new WatchList object for this row
                WatchList movie = new WatchList();

                // Copy data from Database columns to Java object
                movie.setWishlistId(rs.getInt("wishlist_id"));
                movie.setUserId(rs.getInt("user_id"));
                movie.setMovieId(rs.getInt("movie_id"));
                movie.setTitle(rs.getString("title"));
                movie.setAvgPercentageRating(rs.getInt("avg_percentage_rating"));
                movie.setParentalRating(rs.getString("parental_rating"));
                movie.setDescription(rs.getString("description"));

                // Add to our list
                userWatchList.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print error if connection fails
        }

        return userWatchList;
    }

    // 2. Add a movie to user's watchlist
    public void addToWatchList(int userId, int movieId) {
        // We will build this next
        System.out.println("Adding movie " + movieId + " for user " + userId);
    }

    // 3. Rate a movie in the watchlist
    public void postMovieRating(int userId, int movieId, int rating) {
        // We will build this later
        System.out.println("Rating movie " + movieId + " with " + rating);
    }
}