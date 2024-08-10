package com.kajtekh.courseservice.Service;

import com.kajtekh.courseservice.Exception.CourseNotFoundException;
import com.kajtekh.courseservice.Model.Course;
import com.kajtekh.courseservice.Model.CourseType;
import com.kajtekh.courseservice.Model.DTO.CourseDTO;
import com.kajtekh.courseservice.Model.DTO.CreateCourseDTO;
import com.kajtekh.courseservice.Model.DTO.UpdateCourseDTO;
import com.kajtekh.courseservice.Repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
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

    @Transactional
    public Course updateCourse(Long id, UpdateCourseDTO updateCourseDTO) {
        Optional<Course> courseToUpdate = courseRepository.findById(id);
        if (courseToUpdate.isEmpty()) {
            throw new CourseNotFoundException(id);
        }

        courseToUpdate.get().setPrice(updateCourseDTO.price());
        courseToUpdate.get().setTitle(updateCourseDTO.title());
        courseToUpdate.get().setDescription(updateCourseDTO.description());
        courseToUpdate.get().setCourseType(updateCourseDTO.courseType());
        courseToUpdate.get().setStartDate(updateCourseDTO.startDate());
        courseToUpdate.get().setEndDate(updateCourseDTO.endDate());

        return courseRepository.save(courseToUpdate.get());
    }

    @Transactional
    public void deleteCourse(Long id) {
        try{
            courseRepository.deleteById(id);
        }
        catch(CourseNotFoundException e){
        }
    }
}
