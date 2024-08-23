package com.kajtekh.courseservice.repository;

import com.kajtekh.courseservice.model.Course;
import com.kajtekh.courseservice.model.CourseType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long> {

    List<Course> findAll();
    List<Course> findByCourseType(CourseType courseType);
}
