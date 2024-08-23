package com.kajtekh.courseservice.model.dto;

import com.kajtekh.courseservice.model.CourseType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateCourseDTO(BigDecimal price,
                              String title,
                              String description,
                              LocalDate startDate,
                              LocalDate endDate,
                              CourseType courseType) {
}
