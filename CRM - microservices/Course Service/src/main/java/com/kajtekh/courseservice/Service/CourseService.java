package com.kajtekh.courseservice.Service;

import com.kajtekh.courseservice.Model.Course;
import com.kajtekh.courseservice.Model.CourseType;
import com.kajtekh.courseservice.Model.DTO.CourseDTO;
import com.kajtekh.courseservice.Model.DTO.CreateCourseDTO;
import com.kajtekh.courseservice.Repository.CourseRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Iterator;
import java.util.List;

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public Course addCourse(CreateCourseDTO createCourseDTO) {
        return courseRepository.save(CourseMapper.mapToCourse(createCourseDTO));
    }

    @Transactional(readOnly = true)
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(CourseMapper::mapToCourseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CourseDTO> getCoursesByType(CourseType courseType) {
        return courseRepository.findByCourseType(courseType)
                .stream()
                .map(CourseMapper::mapToCourseDTO)
                .toList();
    }

}
