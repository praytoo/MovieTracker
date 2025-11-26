package org.movietracker.model;

import java.sql.Timestamp;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private Timestamp dateJoined;

    // Empty Constructor
    public User() {
    }

    // Constructor 1: The one your Lead's code is looking for (4 arguments)
    public User(int userId, String firstName, String lastName, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Constructor 2: The full version (5 arguments)
    public User(int userId, String firstName, String lastName, String email, Timestamp dateJoined) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateJoined = dateJoined;
    }

    // --- GETTERS AND SETTERS ---

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Timestamp getDateJoined() { return dateJoined; }
    public void setDateJoined(Timestamp dateJoined) { this.dateJoined = dateJoined; }
}