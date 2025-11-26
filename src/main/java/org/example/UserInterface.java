package org.example;

import org.apache.commons.dbcp2.BasicDataSource;
import org.movietracker.model.User;
import org.movietracker.repository.impl.UserRepositoryImpl;

import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);
    private final BasicDataSource dataSource;

    // Constructor that receives the UserRepository
    public UserInterface(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void start() {
        System.out.println("======== WELCOME TO BINGE WATCH ========");
        homeScreen();
    }

    //********************
    //HOME SCREEN
    //********************

    private void homeScreen() {
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
                        mainScreen();
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

    private void mainScreen() {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\n======== MAIN SCREEN ========");
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
                    wishListMenu();
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

    private void allMoviesMenu() {
        boolean inMenu = true;

        while (inMenu) {
            System.out.println("\n======== ALL MOVIES ========");
            System.out.println("1) List all movies by alphabetical order");
            System.out.println("2) List by rating");
            System.out.println("3) List by genres");
            System.out.println("4) List by year");
            System.out.println("5) List by actor");
            System.out.println("0) Go to Main Screen");
            System.out.print("Enter selection: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    // logic to sort by title
                    System.out.println("Sorting alphabetically...");
                    break;
                case "2":
                    // logic to sort by rating
                    System.out.println("Sorting by rating...");
                    break;
                case "3":
                    System.out.print("Enter genre to filter: ");
                    String genre = scanner.nextLine();
                    // logic to filter list by genre
                    System.out.println("Filtering for " + genre + "...");
                    break;
                case "4":
                    // logic to sort or filter by year
                    System.out.println("Sorting by year...");
                    break;
                case "5":
                    // logic to filter by actor
                    System.out.println("Filtering by actor...");
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
            System.out.println("\n======== MY WATCHLIST ========");
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

                    if(response.equalsIgnoreCase("yes")) {
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

    private void wishListMenu() {
        boolean inMenu = true;

        while (inMenu) {
            System.out.println("\n======== MY WISHLIST ========");
            System.out.println("1) List my WishList movies");
            System.out.println("2) Add movie to WishList");
            System.out.println("0) Go to Main Screen");
            System.out.print("Enter selection: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    // get wishlist from db
                    System.out.println("Listing wishlist...");
                    break;
                case "2":
                    System.out.print("What movie do you want to watch? ");
                    String movieName = scanner.nextLine();
                    // add to wishlist table
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