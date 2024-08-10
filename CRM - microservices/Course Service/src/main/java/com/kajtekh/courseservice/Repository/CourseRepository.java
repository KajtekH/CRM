package com.kajtekh.courseservice.Repository;

import com.kajtekh.courseservice.Model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Integer> {
}
