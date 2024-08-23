package com.kajtekh.courseservice;


import com.kajtekh.courseservice.exception.CourseNotFoundException;
import com.kajtekh.courseservice.model.Course;
import com.kajtekh.courseservice.model.CourseType;
import com.kajtekh.courseservice.model.dto.CourseDTO;
import com.kajtekh.courseservice.model.dto.UpdateCourseDTO;
import com.kajtekh.courseservice.service.CourseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    InMemoryCourseRepository courseRepository;
    CourseService courseService;

    @BeforeEach
    void setUp() {
        courseRepository = new InMemoryCourseRepository();
        courseService = new CourseService(courseRepository);
    }

    @AfterEach
    void tearDown() {
        courseRepository.clear();
    }

    @Test
    void whenNoCoursesReturnEmptyList() {
        List<CourseDTO> courseList = courseService.getAllCourses();
        assertTrue(courseList.isEmpty());
    }

    @Test
    void whenCourseDoesNotExistShouldThrowException() {
        assertThrows(CourseNotFoundException.class, () -> courseService.getCourseById(-1L));
    }

    @Test
    void whenUpdatedCourseDoesNotExistShouldThrowException() {
        UpdateCourseDTO updateCourse = new UpdateCourseDTO(
                BigDecimal.valueOf(300),
                "TestTitle",
                "TestDescription",
                LocalDate.of(2012, 12, 12),
                LocalDate.of(2137, 1, 1),
                CourseType.valueOf("JAVA")
        );
        assertThrows(CourseNotFoundException.class, () -> courseService.updateCourse(-1L, updateCourse));
    }

    @Test
    void ShouldUpdateCourse() {
        Course course = new Course(
                BigDecimal.valueOf(300),
                "TestTitle",
                "TestDescription",
                LocalDate.of(2012, 12, 12),
                LocalDate.of(2137, 1, 1),
                CourseType.valueOf("JAVA")
        );

        courseRepository.save(course);

        UpdateCourseDTO updateCourse = new UpdateCourseDTO(
                BigDecimal.valueOf(200),
                "TestTitle232",
                "TestDescription",
                LocalDate.of(1991, 12, 12),
                LocalDate.of(2137, 12, 13),
                CourseType.valueOf("JAVA")
        );

        courseService.updateCourse(1L, updateCourse);
        assertEquals(updateCourse.price(), courseRepository.findById(1L).get().getPrice());
        assertEquals(updateCourse.description(), courseRepository.findById(1L).get().getDescription());
        assertEquals(updateCourse.title(), courseRepository.findById(1L).get().getTitle());
        assertEquals(updateCourse.courseType(), courseRepository.findById(1L).get().getCourseType());
        assertEquals(updateCourse.startDate(), courseRepository.findById(1L).get().getStartDate());
        assertEquals(updateCourse.endDate(), courseRepository.findById(1L).get().getEndDate());
    }
}
