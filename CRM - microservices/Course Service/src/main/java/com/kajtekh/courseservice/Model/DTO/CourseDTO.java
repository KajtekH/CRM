package com.kajtekh.courseservice.Model.DTO;

import com.kajtekh.courseservice.Model.CourseType;

import java.time.LocalDate;

public record CourseDTO(Long id,
                        String title,
                        String description,
                        LocalDate startDate,
                        LocalDate endDate,
                        CourseType courseType) {
}
