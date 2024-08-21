package com.kajtekh.courseservice.Service;

import com.kajtekh.courseservice.Model.Course;
import com.kajtekh.courseservice.Model.DTO.CourseDTO;
import com.kajtekh.courseservice.Model.DTO.CreateCourseDTO;

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

