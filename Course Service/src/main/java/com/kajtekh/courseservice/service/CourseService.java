package com.kajtekh.courseservice.service;

import com.kajtekh.courseservice.exception.CourseNotFoundException;
import com.kajtekh.courseservice.exception.InvalidCourseTypeException;
import com.kajtekh.courseservice.model.Course;
import com.kajtekh.courseservice.model.CourseType;
import com.kajtekh.courseservice.model.dto.CourseDTO;
import com.kajtekh.courseservice.model.dto.CreateCourseDTO;
import com.kajtekh.courseservice.model.dto.UpdateCourseDTO;
import com.kajtekh.courseservice.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.EnumSet;
import java.util.List;


@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void addCourse(CreateCourseDTO createCourseDTO) {
        courseRepository.save(CourseMapper.mapToCourse(createCourseDTO));
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

        if (!isValidCourseType(courseType)) {
            throw new InvalidCourseTypeException(courseType);
        }

        return courseRepository.findByCourseType(courseType)
                .stream()
                .map(CourseMapper::mapToCourseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CourseDTO getCourseById(Long id) {
        return CourseMapper.mapToCourseDTO(courseRepository.findById(id)
                .orElseThrow(()-> new CourseNotFoundException(id)));
    }

    @Transactional
    public Course updateCourse(Long id, UpdateCourseDTO updateCourseDTO) {
        Course courseToUpdate = courseRepository.findById(id)
                        .orElseThrow(()-> new CourseNotFoundException(id));

        courseToUpdate.setPrice(updateCourseDTO.price());
        courseToUpdate.setTitle(updateCourseDTO.title());
        courseToUpdate.setDescription(updateCourseDTO.description());
        courseToUpdate.setCourseType(updateCourseDTO.courseType());
        courseToUpdate.setStartDate(updateCourseDTO.startDate());
        courseToUpdate.setEndDate(updateCourseDTO.endDate());

        return courseRepository.save(courseToUpdate);
    }

    @Transactional
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(()-> new CourseNotFoundException(id));
        courseRepository.delete(course);
    }

    private boolean isValidCourseType(CourseType courseType) {
        return courseType != null && EnumSet.allOf(CourseType.class).contains(courseType);
    }

}
