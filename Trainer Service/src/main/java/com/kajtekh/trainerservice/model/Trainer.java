package com.kajtekh.trainerservice.model;

import jakarta.persistence.*;
import lombok.Data;

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

    private String expertise;

    private String bio;

    private String profilePictureUrl;

}