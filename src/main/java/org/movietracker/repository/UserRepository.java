package org.movietracker.repository;

import org.movietracker.model.User;

public interface UserRepository {
    User login(String email);
    double signUp(String firstName, String lastName, String yourEmail);
}

