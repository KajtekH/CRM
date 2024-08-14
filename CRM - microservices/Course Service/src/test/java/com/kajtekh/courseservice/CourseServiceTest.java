package com.kajtekh.courseservice;


import com.kajtekh.courseservice.Model.Course;
import com.kajtekh.courseservice.Model.CourseType;
import com.kajtekh.courseservice.Service.CourseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CourseServiceTest {

    InMemoryCourseRepository courseRepository;
    CourseService courseService;

    @BeforeEach
    void setUp(){
        courseRepository = new InMemoryCourseRepository();
        courseService = new CourseService(courseRepository);
        Course course = new Course(
                BigDecimal.valueOf(300),
                "TestTitle",
                "TestDescription",
                LocalDate.of(2012,12,12),
                LocalDate.of(2137,1,1),
                CourseType.valueOf("JAVA")
        );
        courseRepository.save(course);
    }

    @AfterEach
    void tearDown(){
        courseRepository.clear();
    }
}
