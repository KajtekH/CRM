package com.kajtekh.courseservice.Model;

import java.time.LocalDate;

public record CourseDTO(Long id,
                        String title,
                        String description,
                        LocalDate startDate,
                        LocalDate endDate,
                        CourseType courseType) {
}
