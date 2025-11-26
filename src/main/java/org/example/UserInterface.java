package org.example;

import org.apache.commons.dbcp2.BasicDataSource;
import org.movietracker.model.User;
import org.movietracker.repository.impl.UserRepositoryImpl;
import org.movietracker.model.Genre;
import org.movietracker.model.Movie;
import org.movietracker.repository.impl.MovieRepositoryImpl;

// --- ARSI'S IMPORTS ---
import org.movietracker.dao.WatchListDAO;
import org.movietracker.model.WatchList;
// ----------------------

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
        final UserRepositoryImpl userRepository = new UserRepositoryImpl(dataSource);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n======== HOME SCREEN ========");
            System.out.println("1) Log in");
            System.out.println("2) Sign up");
            System.out.println("0) Exit");
            System.out.print("Enter selection: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    // Call the login method from UserRepository
                    System.out.print("\nEnter your email: ");
                    String email = scanner.nextLine();
                    User user = userRepository.login(email);
                    if (user != null) {
                        System.out.println("\n===== LOGIN SUCCESSFUL! =====");
                        System.out.println("User ID: " + user.getUserId());
                        System.out.println("Name: " + user.getFirstName() + " " + user.getLastName());
                        System.out.println("Email: " + user.getEmail());

                        // Pass the User ID to the main screen!
                        mainScreen(user.getUserId());
                    } else {
                        System.out.println("User not found");
                    }
                    break;
                case "2":
                    System.out.println("\n===== CREATE NEW ACCOUNT =====");

                    // Handles user input
                    System.out.println("Enter your first name");
                    String firstName = scanner.nextLine();

                    System.out.println("Enter your last name");
                    String lastName = scanner.nextLine();

                    System.out.println("Enter your email");
                    String yourEmail = scanner.nextLine();

                    // Check if email already exists
                    if (userRepository.emailExists(yourEmail)) {
                        System.out.println("\n❌ Email already exists! Please use a different email.");
                        return;
                    }
                    // Call the sign-up method from UserRepository
                    double userID = userRepository.signUp(firstName, lastName, yourEmail);
                    if (userID != 0.0) {
                        System.out.println("\n✓ Account Created Successfully!");
                        System.out.println("Your User ID: " + userID);
                        System.out.println("Name: " + firstName + " " + lastName);
                        System.out.println("Email: " + yourEmail);

                        // Pass the new User ID to the main screen!
                        mainScreen((int) userID);
                    } else {
                        // else prints user not found message.
                        System.out.println("User not found. Try again or would you like to sign up?");
                    }
                    break;
                case "0":
                    System.out.println("Exiting application... GOODBYE!");
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

    // Updated to accept userId
    private void mainScreen(int userId) throws SQLException {
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
                    // Pass userId to your menu
                    watchListMenu(userId);
                    break;
                case "2":
                    try {
                        // Pass userId to the wishlist menu (Fixed from hardcoded 1)
                        wishListMenu(userId);
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
                    try {
                        movies = movieRepository.getMoviesByGenre(Genre.valueOf((genre).toUpperCase()));
                        printMoviesTable(movies.stream().toList());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid genre.");
                    }
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
    //WATCHLIST (ARSI'S FEATURE)
    //****************************

    private void watchListMenu(int userId) {
        boolean inMenu = true;
        // Instantiate your DAO
        WatchListDAO watchListDAO = new WatchListDAO();

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
                    System.out.println("\nListing watchlist...");
                    List<WatchList> myWatchList = watchListDAO.getWatchList(userId);

                    if (myWatchList.isEmpty()) {
                        System.out.println("Your watchlist is empty.");
                    } else {
                        // Simple print format
                        for (WatchList w : myWatchList) {
                            System.out.println("• " + w.getTitle() + " (Your Rating: " + w.getAvgPercentageRating() + "%)");
                        }
                    }
                    break;

                case "2":
                    System.out.print("Enter the ID of the movie you watched: ");
                    // Use ID because DAO requires it
                    int movieId = -1;
                    if(scanner.hasNextInt()) {
                        movieId = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                    } else {
                        System.out.println("Invalid ID.");
                        scanner.nextLine();
                        break;
                    }

                    // Add to watchlist
                    watchListDAO.addToWatchList(userId, movieId);

                    System.out.print("Do you want to rate it? (yes/no): ");
                    String response = scanner.nextLine();

                    if (response.equalsIgnoreCase("yes")) {
                        System.out.print("Enter rating (0-100): ");
                        if(scanner.hasNextInt()) {
                            int rating = scanner.nextInt();
                            scanner.nextLine(); // consume newline
                            watchListDAO.postMovieRating(userId, movieId, rating);
                        } else {
                            System.out.println("Invalid rating number.");
                            scanner.nextLine();
                        }
                    }
                    break;

                case "3":
                    System.out.println("\n--- Top 5 Highest Rated Movies ---");
                    List<Movie> top5 = watchListDAO.getTop5Movies();
                    for (Movie m : top5) {
                        System.out.printf("★ %s - %.1f\n", m.getTitle(), m.getAvrRating());
                    }
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

    // Renamed parameter to userId to be consistent
    private void wishListMenu(int userId) throws SQLException {
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
                    System.out.println("Listing wishlist...");
                    // Updated to use real userId
                    WishListRepository.viewWishList(connection, userId);
                    break;
                case "2":
                    WishListRepository.displayAllMovies(connection);
                    System.out.print("Enter the Movie ID to add: ");
                    // Assuming this takes an ID based on previous context
                    int mid = -1;
                    if(scanner.hasNextInt()) {
                        mid = scanner.nextInt();
                        scanner.nextLine();
                    }

                    if (mid != -1) {
                        // Updated to use real userId
                        WishListRepository.addMovieToWishList(connection, mid, userId);
                        System.out.println("Movie added to wishlist!");
                    }
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