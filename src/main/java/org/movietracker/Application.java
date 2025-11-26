package org.movietracker;

import org.apache.commons.dbcp2.BasicDataSource;
import org.movietracker.dao.WatchListDAO;
import org.movietracker.model.Movie;
import org.movietracker.model.WatchList;
// import org.movietracker.repository.impl.MovieRepositoryImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws SQLException {

        // setup db connection
        BasicDataSource bds = new BasicDataSource();
        bds.setUrl("jdbc:mysql://localhost:3306/movie_tracker");
        bds.setUsername("root");
        bds.setPassword("yearup25"); // check if this matches your local db

        // lead's code was crashing here so i commented it out for now
        // MovieRepositoryImpl movieRepository = new MovieRepositoryImpl(bds);

        // my watchlist dao
        WatchListDAO watchListDAO = new WatchListDAO();
        Scanner scanner = new Scanner(System.in);

        // fake user id for testing
        int currentUserId = 1;

        // main loop
        while (true) {
            System.out.println("\n=================================");
            System.out.println("      MOVIE TRACKER (TEST)");
            System.out.println("=================================");
            System.out.println("2. View My Watchlist");
            System.out.println("3. Add to Watchlist");
            System.out.println("4. Rate a Movie");
            System.out.println("5. Top 5 Movies");
            System.out.println("0. Exit");
            System.out.print("\nChoose an option: ");

            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next(); // clear bad input
            }

            switch (choice) {
                case 1:
                    System.out.println("Not working yet");
                    break;

                case 2:
                    System.out.println("\n--- Your Watchlist ---");
                    List<WatchList> myWatchList = watchListDAO.getWatchList(currentUserId);

                    if (myWatchList.isEmpty()) {
                        System.out.println("Your watchlist is empty.");
                    } else {
                        // print out the movies
                        for (WatchList w : myWatchList) {
                            System.out.println("• " + w.getTitle() + " (Rating: " + w.getAvgPercentageRating() + "%)");
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Movie ID to add: ");
                    int addId = scanner.nextInt();
                    watchListDAO.addToWatchList(currentUserId, addId);
                    break;

                case 4:
                    System.out.print("Enter Movie ID to rate: ");
                    int rateId = scanner.nextInt();

                    System.out.print("Enter your rating (0-100): ");
                    int rating = scanner.nextInt();

                    watchListDAO.postMovieRating(currentUserId, rateId, rating);
                    break;

                case 5:
                    System.out.println("\n--- Top 5 Highest Rated Movies ---");
                    List<Movie> top5 = watchListDAO.getTop5Movies();

                    for (Movie m : top5) {
                        System.out.println("★ " + m.getTitle() + " - " + m.getAvrRating());
                    }
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}