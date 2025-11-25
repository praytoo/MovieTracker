package org.movietracker.dao;

import org.movietracker.model.WatchList;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WatchListDAO {

    // 1. Get all movies in a user's watchlist
    public List<WatchList> getWatchList(int userId) {
        List<WatchList> userWatchList = new ArrayList<>();
        // TODO: Add database logic
        return userWatchList;
    }

    // 2. Add a movie to user's watchlist
    public void addToWatchList(int userId, int movieId) {
        System.out.println("Adding movie " + movieId + " for user " + userId);
    }

    // 3. Rate a movie in the watchlist
    public void postMovieRating(int userId, int movieId, int rating) {
        System.out.println("Rating movie " + movieId + " with " + rating);
    }
}