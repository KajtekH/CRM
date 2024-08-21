package com.kajtekh.trainerservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name="trainers")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String firstName;

    private String lastName;

    private String bio;

    private String profilePictureUrl;

    public Trainer(Long userId, String firstName, String lastName, String bio, String profilePictureUrl) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.profilePictureUrl = profilePictureUrl;
    }
}