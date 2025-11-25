package org.example;

import java.util.Scanner;

public class UserInterface {
    private static Scanner scanner = new Scanner(System.in);



    public void start() {
        System.out.println("Welcome to Binge Watch");
        homeScreen();
    }

    //********************
    //HOME SCREEN
    //********************

    private void homeScreen() {
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

    private void mainScreen() {
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
            System.out.println("\n--- ALL MOVIES ---");
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