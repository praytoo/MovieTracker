package org.movietracker.repository.impl;

import org.apache.commons.dbcp2.BasicDataSource;
import org.movietracker.model.User;
import org.movietracker.repository.UserRepository;

import java.sql.*;

public class UserRepositoryImpl implements UserRepository {
    // Store the database connection pool
    private final BasicDataSource dataSource;

    public UserRepositoryImpl(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Method to handle user login
    @Override
    public User login(String email) {
        User user = null;
        // Write the database query to find a user with that email
        String selectQuery = "SELECT user_id, first_name, last_name, email FROM Users WHERE email = ?";

        // Try to connect to database (try-with-resources automatically closes connection)
        try (Connection connection = dataSource.getConnection();
             // Prepare the query to be sent to database
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {

            // Put the user's email into the query (the ? becomes the email)
            statement.setString(1, email);

            // Send the query to database and get results back
            ResultSet rs = statement.executeQuery();

            // If a user with that email was found
            if (rs.next()) {
                user = new User(rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"));

            }

        } catch (SQLException e) {
            // If something goes wrong with database, print error message
            System.out.println("Error logging in: " + e.getMessage());
        }
        return user;
    }

    // Method to handle user sign up
    @Override
    public double signUp(String firstName, String lastName, String yourEmail) {
        double userID = 0.0;
        // Write the database query to insert a new user
        String insertQuery = "INSERT INTO Users (first_name, last_name, email) VALUES (?, ?, ?)";

        // Try to connect to database (try-with-resources automatically closes connection)
        try (Connection connection = dataSource.getConnection();
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
                    userID = keys.getInt(1);
                }
            }
        } catch (SQLException e) {
            // If something goes wrong with database, print error message
            System.out.println("Error signing up: " + e.getMessage());
        }
        return userID;
    }

    // Check if email already exists in database
    public boolean emailExists(String email) {
        String checkQuery = "SELECT email FROM Users WHERE email = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(checkQuery)) {

            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error checking email: " + e.getMessage());
            return false;
        }
    }
}
