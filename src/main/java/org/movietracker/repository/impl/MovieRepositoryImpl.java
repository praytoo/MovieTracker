package org.movietracker.repository.impl;

import org.apache.commons.dbcp2.BasicDataSource;
import org.movietracker.model.Genre;
import org.movietracker.model.Movie;
import org.movietracker.model.ParentalRating;
import org.movietracker.repository.MovieRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieRepositoryImpl implements MovieRepository {
    private final BasicDataSource dataSource;
    public MovieRepositoryImpl(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Movie mapMovie(ResultSet rs) throws SQLException {
        return new Movie(
                rs.getInt("movie_id"),
                rs.getString("title"),
                Genre.valueOf(rs.getString("genre").toUpperCase()),
                ParentalRating.valueOf(rs.getString("parental_rating").toUpperCase()),
                rs.getDouble("avg_percentage_rating"),
                rs.getString("description"),
                rs.getDate("date_released")
        );
    }

    @Override
    public void addMovie(Movie movie) {
        final String sql = """
                INSERT INTO Movies (title, genre, avg_percentage_rating, parental_rating, date_released, description)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre().name());
            stmt.setInt(3, (int) movie.getAvrRating());
            stmt.setString(4, movie.getParentalRating().name());
            stmt.setDate(5, new Date(movie.getReleasedDate().getTime()));
            stmt.setString(6, movie.getDescription());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert movie", e);
        }
    }

    @Override
    public Movie getMovieById(int id) {
        final String sql = "SELECT * FROM Movies WHERE movie_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapMovie(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch movie by ID", e);
        }

        return null;
    }

    @Override
    public List<Movie> getAllMovies() throws SQLException {
        final String sql = "SELECT * FROM Movies ORDER BY movie_id";

        List<Movie> movies = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                movies.add(mapMovie(rs));
            }
        }

        return movies;
    }

    public List<Movie> getMoviesAlphabetically() throws SQLException {
        String sql = "SELECT * FROM Movies ORDER BY title ASC";

        List<Movie> movies = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                movies.add(mapMovie(rs));
            }
        }

        return movies;
    }

    public List<Movie> getMoviesByRating() throws SQLException {
        String sql = "SELECT * FROM Movies ORDER BY avg_percentage_rating DESC";

        List<Movie> movies = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                movies.add(mapMovie(rs));
            }
        }

        return movies;
    }

    public List<Movie> getMoviesByGenre(Genre genre) throws SQLException {
        String sql = "SELECT * FROM Movies WHERE genre = ?";

        List<Movie> movies = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, genre.name());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                movies.add(mapMovie(rs));
            }
        }

        return movies;
    }

    public List<Movie> getMoviesByYear() throws SQLException {
        String sql = "SELECT * FROM Movies ORDER BY date_released DESC";

        List<Movie> movies = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                movies.add(mapMovie(rs));
            }
        }

        return movies;
    }
}
