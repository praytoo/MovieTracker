package org.movietracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.ResultSet;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class Movie {
    private double movie_id;
    private String title;
    private Genre genre;
    private ParentalRating parentalRating;
    private double avrRating;
    private String description;
    private Date releasedDate;

    public Movie(int movie_id, String title, Genre genre, ParentalRating parentalRating, double avrRating, String description, java.sql.Date releasedDate) {
        this.movie_id = movie_id;
        this.title = title;
        this.genre = genre;
        this.parentalRating = parentalRating;
        this.avrRating = avrRating;
        this.description = description;
        this.releasedDate = releasedDate;
    }

    public String getTitle() {
        return title;
    }

    public <E extends Enum<E>> Enum<E> getGenre() {
        return (Enum<E>) genre;
    }

    public Object getAvrRating() {
        return avrRating;
    }

    public <E extends Enum<E>> Enum<E> getParentalRating() {
        return (Enum<E>) parentalRating;
    }

    public Date getReleasedDate() {
        return releasedDate;
    }

    public String getDescription() {
        return description;
    }
}
