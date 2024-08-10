package com.kajtekh.courseservice.Exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Long id) {
        super("Course Not Found: " + id);
    }
}
