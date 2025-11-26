package org.movietracker.model;

import java.util.Date;
import java.util.List;

public class Movie {
    private double movie_id;
    private String title;
    private Genre genre;
    private List<Actor> cast;
    private ParentalRating parentalRating;
    private double avrRating;
    private String description;
    private Date releasedDate;

    // 1. Empty Constructor
    public Movie() {
    }

    // 2. Lead's Constructor (7 arguments, NO CAST/ACTORS)
    // This fixes the error in MovieRepositoryImpl
    public Movie(double movie_id, String title, Genre genre, ParentalRating parentalRating, double avrRating, String description, Date releasedDate) {
        this.movie_id = movie_id;
        this.title = title;
        this.genre = genre;
        this.parentalRating = parentalRating;
        this.avrRating = avrRating;
        this.description = description;
        this.releasedDate = releasedDate;
        this.cast = null; // Set cast to null since they didn't provide it
    }

    // 3. Full Constructor (8 arguments, includes CAST)
    public Movie(double movie_id, String title, Genre genre, List<Actor> cast, ParentalRating parentalRating, double avrRating, String description, Date releasedDate) {
        this.movie_id = movie_id;
        this.title = title;
        this.genre = genre;
        this.cast = cast;
        this.parentalRating = parentalRating;
        this.avrRating = avrRating;
        this.description = description;
        this.releasedDate = releasedDate;
    }

    // --- GETTERS AND SETTERS ---
    public double getMovie_id() { return movie_id; }
    public void setMovie_id(double movie_id) { this.movie_id = movie_id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }

    public List<Actor> getCast() { return cast; }
    public void setCast(List<Actor> cast) { this.cast = cast; }

    public ParentalRating getParentalRating() { return parentalRating; }
    public void setParentalRating(ParentalRating parentalRating) { this.parentalRating = parentalRating; }

    public double getAvrRating() { return avrRating; }
    public void setAvrRating(double avrRating) { this.avrRating = avrRating; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getReleasedDate() { return releasedDate; }
    public void setReleasedDate(Date releasedDate) { this.releasedDate = releasedDate; }
}