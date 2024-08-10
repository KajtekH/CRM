package com.kajtekh.courseservice.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;


    @Enumerated(EnumType.STRING)
    private CourseType courseType;


    public Course(){};

    public Course(String title, String description, LocalDate startDate, LocalDate endDate, CourseType courseType) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseType = courseType;
    }
}
