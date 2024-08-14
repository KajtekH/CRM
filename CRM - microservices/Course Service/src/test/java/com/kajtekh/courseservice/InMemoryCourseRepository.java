package com.kajtekh.courseservice;

import com.kajtekh.courseservice.Model.Course;
import com.kajtekh.courseservice.Model.CourseType;
import com.kajtekh.courseservice.Repository.CourseRepository;
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
        return Optional.of(courses.get(id));
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
    public <S extends Course> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Course> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Course> entities) {

    }

    @Override
    public void deleteAll() {

    }



}
