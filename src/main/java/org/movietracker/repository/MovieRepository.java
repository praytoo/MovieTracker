package org.movietracker.repository;

import org.movietracker.model.Genre;
import org.movietracker.model.Movie;

import java.sql.SQLException;
import java.util.List;

public interface MovieRepository {
    void addMovie(Movie movie);
    Movie getMovieById(int id);
    List<Movie> getAllMovies() throws SQLException;
    List<Movie> getMoviesAlphabetically() throws SQLException;
    List<Movie> getMoviesByRating() throws SQLException;
    List<Movie> getMoviesByGenre(Genre genre) throws SQLException;
    List<Movie> getMoviesByYear() throws SQLException;
    List<Movie> getWishlistMoviesAlphabetically(int userId) throws SQLException;
    List<Movie> getWishlistMoviesByRating(int userId) throws SQLException;
    List<Movie> getWishlistMoviesByGenre(int userId, Genre genre) throws SQLException;
    List<Movie> getWishlistMoviesByYear(int userId, int year) throws SQLException;

}
