package org.example;

import org.apache.commons.dbcp2.BasicDataSource;
import org.movietracker.model.Genre;
import org.movietracker.model.Movie;
import org.movietracker.repository.impl.MovieRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

import org.movietracker.repository.WishListRepository;

import java.sql.Connection;
import java.util.Scanner;

import static org.movietracker.servises.DataViewService.printMoviesTable;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);
    private final BasicDataSource dataSource;

    public UserInterface(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void start() throws SQLException {
        System.out.println("Welcome to Binge Watch");
        homeScreen();
    }

    //********************
    //HOME SCREEN
    //********************

    private void homeScreen() throws SQLException {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n--- HOME SCREEN ---");
            System.out.println("1) Log in");
            System.out.println("0) Exit");
            System.out.print("Enter selection: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();

                    // verify user against the database here

                    System.out.println("Welcome, " + username + "!");
                    mainScreen();
                    break;
                case "0":
                    System.out.println("Exiting application...");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid, Please try again.");
            }
        }
    }

    //*********************
    //MAIN SCREEN
    //*********************

    private void mainScreen() throws SQLException {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\n--- MAIN SCREEN ---");
            System.out.println("1) WatchList");
            System.out.println("2) WishList");
            System.out.println("3) All Movies");
            System.out.println("0) Log out");
            System.out.print("Enter selection: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    watchListMenu();
                    break;
                case "2":
                    try {
                        wishListMenu(1);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "3":
                    allMoviesMenu();
                    break;
                case "0":
                    System.out.println("Logging out...");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    //******************
    //ALL MOVIES
    //*******************

    private void allMoviesMenu() throws SQLException {
        boolean inMenu = true;
        MovieRepositoryImpl movieRepository = new MovieRepositoryImpl(dataSource);
        List<Movie> movies;

        while (inMenu) {
            System.out.println("\n--- ALL MOVIES ---");
            System.out.println("1) List all movies by alphabetical order");
            System.out.println("2) List by rating");
            System.out.println("3) List by genres");
            System.out.println("4) List by year");
            System.out.println("0) Go to Main Screen");
            System.out.print("Enter selection: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    // logic to sort by title
                    System.out.println("Sorting alphabetically...");
                    movies = movieRepository.getMoviesAlphabetically();
                    printMoviesTable(movies.stream().toList());
                    break;
                case "2":
                    // logic to sort by rating
                    System.out.println("Sorting by rating...");
                    movies = movieRepository.getMoviesByRating();
                    printMoviesTable(movies.stream().toList());
                    break;
                case "3":
                    System.out.print("Enter genre to filter: ");
                    String genre = scanner.nextLine();
                    // logic to filter list by genre
                    System.out.println("Filtering for " + genre + "...");
                    movies = movieRepository.getMoviesByGenre(Genre.valueOf((genre).toUpperCase()));
                    printMoviesTable(movies.stream().toList());
                    break;
                case "4":
                    // logic to sort or filter by year
                    System.out.println("Sorting by year...");
                    movies = movieRepository.getMoviesByYear();
                    printMoviesTable(movies.stream().toList());
                    break;
                case "0":
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    //****************************
    //WATCHLIST
    //****************************

    private void watchListMenu() {
        boolean inMenu = true;

        while (inMenu) {
            System.out.println("\n--- MY WATCHLIST ---");
            System.out.println("1) List my WatchList movies");
            System.out.println("2) Add a watched movie");
            System.out.println("3) Show my top 5 movies");
            System.out.println("0) Go to Main Screen");
            System.out.print("Enter selection: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    // need to get watchlist from db and display it
                    System.out.println("Listing watchlist...");
                    break;
                case "2":
                    System.out.print("What movie did you watch? ");
                    String movieName = scanner.nextLine();

                    System.out.print("Do you want to rate it? (yes/no): ");
                    String response = scanner.nextLine();

                    if (response.equalsIgnoreCase("yes")) {
                        System.out.print("Enter rating (1-5): ");
                        String rating = scanner.nextLine();
                        // save rating to db
                    }
                    // save movie to watched list table
                    System.out.println("Saved " + movieName + " to watchlist.");
                    break;
                case "3":
                    // stream limit logic
                    System.out.println("Showing top 5...");
                    break;
                case "0":
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // ****************************
    // WISHLIST
    //******************************

    private void wishListMenu(int movieId) throws SQLException {
        boolean inMenu = true;
        Connection connection = dataSource.getConnection();

        while (inMenu) {
            System.out.println("\n--- MY WISHLIST ---");
            System.out.println("1) List my WishList movies");
            System.out.println("2) Add movie to WishList");
            System.out.println("0) Go to Main Screen");
            System.out.print("Enter selection: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    // get wishlist from db
                    System.out.println("Listing wishlist...");
                    WishListRepository.viewWishList(connection, 1);
                    break;
                case "2":
                    WishListRepository.displayAllMovies(connection);
                    System.out.print("What movie do you want to watch? ");
                    String movieName = scanner.nextLine();
                    // add to wishlist table
                    WishListRepository.addMovieToWishList(connection, movieId, 1);
                    System.out.println(movieName + " added to wishlist!");
                    break;
                case "0":
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}