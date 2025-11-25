package org.movietracker.repository;

import org.movietracker.model.Movie;

import java.sql.SQLException;

public interface MovieRepository {
    void addMovie(Movie movie);
    Movie getMovieById(int id);
    void getAllMovies() throws SQLException;
}
