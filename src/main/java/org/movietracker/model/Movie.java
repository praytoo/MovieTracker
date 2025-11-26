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

    // Empty Constructor
    public Movie() {
    }

    // Full Constructor
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

    // Getters and Setters (Manual)
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