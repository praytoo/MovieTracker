package org.movietracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

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
}
