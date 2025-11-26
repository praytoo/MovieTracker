package org.movietracker.repository;

import org.movietracker.model.Movie;

import java.sql.Connection;
import java.sql.SQLException;

public interface WishListRepository {
    void displayAllMovies(Connection connection) throws SQLException;
    int promptMovieId() throws SQLException;
    Movie findMovieByID(Connection connection, Integer movieId) throws SQLException;
    void addMovieToWishList(Connection connection, int movieId, int user_id) throws SQLException;
    void viewWishList(Connection connection, int user_id) throws SQLException;
}
