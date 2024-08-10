package com.kajtekh.courseservice.Service;

import com.kajtekh.courseservice.Repository.CourseRepository;

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
}
