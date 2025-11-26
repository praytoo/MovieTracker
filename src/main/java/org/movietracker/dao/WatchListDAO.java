package org.movietracker.dao;

import org.movietracker.model.Movie;
import org.movietracker.model.WatchList;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WatchListDAO {

    // 1. Get WatchList
    public List<WatchList> getWatchList(int userId) {
        List<WatchList> userWatchList = new ArrayList<>();

        String sql = "SELECT w.id as wishlist_id, w.user_id, m.* " +
                "FROM Wish_List_Relation w " +
                "JOIN Movies m ON w.movie_id = m.movie_id " +
                "WHERE w.user_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                WatchList movie = new WatchList();
                movie.setWishlistId(rs.getInt("wishlist_id"));
                movie.setUserId(rs.getInt("user_id"));
                movie.setMovieId(rs.getInt("movie_id"));
                movie.setTitle(rs.getString("title"));
                movie.setAvgPercentageRating(rs.getInt("avg_percentage_rating"));
                movie.setParentalRating(rs.getString("parental_rating"));
                movie.setDescription(rs.getString("description"));

                userWatchList.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userWatchList;
    }

    // 2. Add to WatchList
    public void addToWatchList(int userId, int movieId) {
        String sql = "INSERT INTO Wish_List_Relation (user_id, movie_id) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, movieId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Success! Movie " + movieId + " added to watchlist.");
            } else {
                System.out.println("Could not add movie. Check ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Rate a Movie
    public void postMovieRating(int userId, int movieId, int rating) {
        String sql = "INSERT INTO Ratings (user_id, movie_id, rating_value) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, movieId);
            stmt.setInt(3, rating);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Success! Rated movie " + movieId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4. Get Top 5 Movies
    public List<Movie> getTop5Movies() {
        List<Movie> allMovies = new ArrayList<>();
        String sql = "SELECT * FROM Movies";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // MANUAL WAY (Replaced Builder with standard setters)
                Movie movie = new Movie();
                movie.setMovie_id(rs.getDouble("movie_id"));
                movie.setTitle(rs.getString("title"));
                movie.setAvrRating(rs.getDouble("avg_percentage_rating"));
                movie.setDescription(rs.getString("description"));

                // Leaving complex fields null for now to prevent errors
                movie.setGenre(null);
                movie.setCast(null);
                movie.setParentalRating(null);

                allMovies.add(movie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allMovies.stream()
                .sorted(Comparator.comparingDouble(Movie::getAvrRating).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }
}