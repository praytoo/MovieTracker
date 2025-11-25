package userloginsystem;

import java.sql.*;
import java.util.Scanner;

public class UserLogin {
    // Database connection information
    private static final String URL = "jdbc:mysql://localhost:3306/movie_tracker";
    private static final String USER = "root";
    private static final String PASSWORD = "yearup24";
    private static final Scanner console = new Scanner(System.in);

    // This is the main class of the program.
    public static void main(String[] args) {
        start();
    }

    // Main method that starts the program
    public static void start() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n===== WELCOME TO BINGE WATCH =====\n");
            System.out.println("1. Login");
            System.out.println("2. Sign up");

            int userChoice = getIntChoice("Enter your choice: \n", 0, 2);

            switch (userChoice) {
                case 1:
                    login();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    System.out.println("\n===== Goodbye! =====");
                    isRunning = false;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + userChoice);
            }
        }
    }

    // Method to handle user login
    public static void login() {
        System.out.print("\nEnter your email: ");
        String email = console.nextLine();

        // Write the database query to find a user with that email
        String selectQuery = "SELECT user_id, first_name, last_name, email FROM Users WHERE email = ?";

        // with help of Maaike boostcamp repos
        // Try to connect to database (try-with-resources automatically closes connection)
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             // Prepare the query to be sent to database
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {

            // Put the user's email into the query (the ? becomes the email)
            statement.setString(1, email);

            // Send the query to database and get results back
            ResultSet rs = statement.executeQuery();

            // If a user with that email was found
            if (rs.next()) {
                System.out.println("\n===== LOGIN SUCCESSFUL! =====");
                System.out.println("User ID: " + rs.getInt("user_id"));
                System.out.println("Name: " + rs.getString("first_name") + " " + rs.getString("last_name"));
                System.out.println("Email: " + rs.getString("email"));

            } else {
                // else prints user not found message.
                System.out.println("User not found. Would up you like to sign up?");
            }

        } catch (SQLException e) {
            // If something goes wrong with database, print error message
            System.out.println("Error logging in: " + e.getMessage());
        }
    }

    // Method to handle user sign up
    public static void signUp() {
        System.out.println("\n===== CREATE NEW ACCOUNT =====");

        // Handles user input
        System.out.println("Enter your first name");
        String firstName = console.nextLine();

        System.out.println("Enter your last name");
        String lastName = console.nextLine();

        System.out.println("Enter your email");
        String yourEmail = console.nextLine();


        // Write the database query to insert a new user
        String insertQuery = "INSERT INTO Users (first_name, last_name, email) VALUES (?, ?, ?)";
        // Try to connect to database (try-with-resources automatically closes connection)
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             // Prepare the query to be sent to database
             PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            // Put first name into the first ? in the query
            statement.setString(1, firstName);
            // Put last name into the second ? in the query
            statement.setString(2, lastName);
            // Put email into the third ? in the query
            statement.setString(3, yourEmail);


            // Send the insert query to database and get number of rows added
            int rowsInserted = statement.executeUpdate();

            // If at least one row was added successfully
            if (rowsInserted > 0) {
                // Get the auto-generated user ID from the database
                ResultSet keys = statement.getGeneratedKeys();
                // If we got a user ID back
                if (keys.next()) {
                    // Store the user ID
                    int userId = keys.getInt(1);
                    // Print success message along with user details
                    System.out.println("\n✓ Account Created Successfully!");
                    System.out.println("Your User ID: " + userId);
                    System.out.println("Name: " + firstName + " " + lastName);
                    System.out.println("Email: " + yourEmail);
                }
            }
        } catch (SQLException e) {
            // If something goes wrong with database, print error message
            System.out.println("Error logging in: " + e.getMessage());
        }

    }

    // Helper method to get a valid number from user between min and max
    public static int getIntChoice(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int userChoice = Integer.parseInt(console.nextLine().trim());
                if (userChoice >= min && userChoice <= max) {
                    return userChoice;
                }
                System.out.println("Please enter a number between " + min + " and " + max);
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // check email method (if it exists)
}
