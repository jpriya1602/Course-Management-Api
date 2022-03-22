package com.example.coursemanagementapi.Controller;

import com.example.coursemanagementapi.CourseDto;
import com.example.coursemanagementapi.CourseExistsException;
import com.example.coursemanagementapi.CourseNotFoundException;
import com.example.coursemanagementapi.Entity.Course;
import com.example.coursemanagementapi.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> GetAllCourse() {
        return courseService.GetCourses();
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Course> GetCourseById(@PathVariable Long Id) throws CourseNotFoundException {
        try {
            Course course = courseService.GetCourseById(Id);
            return ok().body(course);
        } catch (CourseNotFoundException e) {
            return status(404).build();
        }

    }

    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody CourseDto courseDto) {
        if (courseDto.getCourseName().isEmpty() || courseDto.getCourseName() == null)
            return status(HttpStatus.BAD_REQUEST).build();
        try {
            Course course = courseService.SaveCourse(courseDto);
            return status(HttpStatus.CREATED).body(course);
        } catch (Exception e) {
            return status(HttpStatus.BAD_REQUEST).build();

        }
    }


}
