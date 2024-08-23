package com.kajtekh.courseservice;

import com.kajtekh.courseservice.model.Course;
import com.kajtekh.courseservice.model.CourseType;
import com.kajtekh.courseservice.repository.CourseRepository;
import org.springframework.lang.NonNull;

import java.util.*;

public class InMemoryCourseRepository implements CourseRepository {

    private final Map<Long, Course> courses = new HashMap<>();
    private Long mapKey = 1L;

    public void clear(){
        courses.clear();
        mapKey = 1L;
    }

    @Override
    public <S extends Course> S save(S course) {
        course.setId(mapKey++);
        courses.put(course.getId(),course);
        return course;
    }

    @Override
    public Optional<Course> findById(@NonNull Long id) {
        return Optional.ofNullable(courses.get(id));
    }

    @Override
    public List<Course> findAll() {
        return courses.values()
                .stream()
                .toList();
    }

    @Override
    public List<Course> findByCourseType(CourseType courseType) {
        return courses.values()
                .stream()
                .filter(course -> course.getCourseType().equals(courseType))
                .toList();
    }

    @Override
    public void delete(Course course) {
        courses.remove(course.getId());
    }


    ///Not used methods

    @Override
    public <S extends Course> Iterable<S> saveAll(Iterable<S> entities) {  // Non implemented - method is empty
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {  // Non implemented - method is empty
        return false;
    }

    @Override
    public Iterable<Course> findAllById(Iterable<Long> longs) {  // Non implemented - method is empty
        return null;
    }

    @Override
    public long count() {  // Non implemented - method is empty
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {  // Non implemented - method is empty

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {  // Non implemented - method is empty

    }

    @Override
    public void deleteAll(Iterable<? extends Course> entities) {  // Non implemented - method is empty

    }

    @Override
    public void deleteAll() {  // Non implemented - method is empty

    }



}
