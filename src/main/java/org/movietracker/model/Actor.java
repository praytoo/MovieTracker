package org.movietracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Actor {
    private double actorId;
    private String firstName;
    private String lastName;
}
