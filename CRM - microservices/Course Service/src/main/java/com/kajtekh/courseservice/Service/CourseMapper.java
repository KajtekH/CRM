package com.kajtekh.courseservice.Service;

import com.kajtekh.courseservice.Model.Course;
import com.kajtekh.courseservice.Model.CourseDTO;

public class CourseMapper {

    private CourseMapper() {
    }

    ;

    public static CourseDTO maptoCourseDTO(Course course) {
        return new CourseDTO(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getStartDate(),
                course.getEndDate(),
                course.getCourseType()
        );
    }
}

