package org.movietracker.servises;

import org.movietracker.model.Movie;

import java.util.List;

public class DataViewService {
    public static void printMoviesTable(List<Movie> movies) {
        System.out.printf(
                "%-5s %-25s %-12s %-8s %-15s %-12s %-40s%n",
                "ID", "Title", "Genre", "Rating", "Parental", "Released", "Description"
        );
        System.out.println("=".repeat(120));

        for (Movie m : movies) {
            String shortenedDesc = m.getDescription();

            System.out.printf(
                    "%-25s %-12s %-8.2f %-15s %-12s %-40s%n",
                    m.getTitle(),
                    m.getGenre().name(),
                    m.getAvrRating(),
                    m.getParentalRating().name(),
                    m.getReleasedDate().toString(),
                    shortenedDesc
            );
        }
    }

}
