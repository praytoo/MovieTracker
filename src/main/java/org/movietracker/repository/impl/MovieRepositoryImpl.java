package org.movietracker.repository.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.movietracker.repository.MovieRepository;
import org.movietracker.model.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class MovieRepositoryImpl implements MovieRepository {
    private final BasicDataSource dataSource;

    @Override
    public void addMovie(Movie movie) {

    }

    @Override
    public Movie getMovieById(int id) {
        return null;
    }

    @Override
    public void getAllMovies() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM movies");
            while(rs.next()) {
                System.out.println("Movie id: " + rs.getString("movie_id"));
                System.out.println("Date Released: " + rs.getString("date_released"));
                System.out.println("Genre: " + rs.getString("genre"));
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Average Percent Rating: " + rs.getInt("avg_percentage_rating"));
                System.out.println("Parental Rating: " + rs.getString("parental_rating"));
                System.out.println("Description: " + rs.getString("description"));
            }
        }
    }
}
