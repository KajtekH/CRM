package com.kajtekh.courseservice.Model.DTO;

import com.kajtekh.courseservice.Model.CourseType;

import java.time.LocalDate;

public record CreateCourseDTO(String title,
                              String description,
                              LocalDate startDate,
                              LocalDate endDate,
                              CourseType courseType) {
}
