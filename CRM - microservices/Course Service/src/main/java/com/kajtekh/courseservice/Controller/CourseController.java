package com.kajtekh.courseservice.Controller;

import com.kajtekh.courseservice.Exception.CourseNotFoundException;
import com.kajtekh.courseservice.Exception.InvalidCourseTypeException;
import com.kajtekh.courseservice.Model.Course;
import com.kajtekh.courseservice.Model.CourseType;
import com.kajtekh.courseservice.Model.DTO.CourseDTO;
import com.kajtekh.courseservice.Model.DTO.CreateCourseDTO;
import com.kajtekh.courseservice.Model.DTO.UpdateCourseDTO;
import com.kajtekh.courseservice.Service.CourseService;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/COURSE-SERVICE/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<CourseDTO>> getCoursesByType(@PathVariable  CourseType type) {
        try{
            return ResponseEntity.ok(courseService.getCoursesByType(type));
        }
        catch (InvalidCourseTypeException e){
            return  ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCourse(@RequestBody CreateCourseDTO courseDTO) {
        courseService.addCourse(courseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @RequestBody UpdateCourseDTO courseDTO) {
        courseService.updateCourse(id, courseDTO);
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
