package com.kajtekh.courseservice.service;

import com.kajtekh.courseservice.model.Course;
import com.kajtekh.courseservice.model.dto.CourseDTO;
import com.kajtekh.courseservice.model.dto.CreateCourseDTO;

public class CourseMapper {

    private CourseMapper() {
    }


    public static CourseDTO mapToCourseDTO(Course course) {
        return new CourseDTO(
                course.getId(),
                course.getPrice(),
                course.getTitle(),
                course.getDescription(),
                course.getStartDate(),
                course.getEndDate(),
                course.getCourseType()
        );
    }

    public static Course mapToCourse(CreateCourseDTO createCourseDTO) {
        return new Course(
                createCourseDTO.price(),
                createCourseDTO.title(),
                createCourseDTO.description(),
                createCourseDTO.startDate(),
                createCourseDTO.endDate(),
                createCourseDTO.courseType()
        );
    }

}

