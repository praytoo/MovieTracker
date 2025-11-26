package org.movietracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private double userId;
    private String firstName;
    private String lastName;
    private String email;
}
