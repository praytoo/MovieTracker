package org.movietracker.model;

public class WatchList {
    private int wishlistId;
    private int userId;
    private int movieId;
    private String title;
    private int avgPercentageRating;
    private String parentalRating;
    private String description;

    // Empty Constructor
    public WatchList() {
    }

    // Full Constructor
    public WatchList(int wishlistId, int userId, int movieId, String title, int avgPercentageRating, String parentalRating, String description) {
        this.wishlistId = wishlistId;
        this.userId = userId;
        this.movieId = movieId;
        this.title = title;
        this.avgPercentageRating = avgPercentageRating;
        this.parentalRating = parentalRating;
        this.description = description;
    }

    // Getters and Setters
    public int getWishlistId() { return wishlistId; }
    public void setWishlistId(int wishlistId) { this.wishlistId = wishlistId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getAvgPercentageRating() { return avgPercentageRating; }
    public void setAvgPercentageRating(int avgPercentageRating) { this.avgPercentageRating = avgPercentageRating; }

    public String getParentalRating() { return parentalRating; }
    public void setParentalRating(String parentalRating) { this.parentalRating = parentalRating; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}