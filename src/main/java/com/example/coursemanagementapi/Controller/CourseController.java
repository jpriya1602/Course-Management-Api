package com.example.coursemanagementapi.Controller;

import com.example.coursemanagementapi.CourseDto;
import com.example.coursemanagementapi.CourseNotFoundException;
import com.example.coursemanagementapi.Entity.Course;
import com.example.coursemanagementapi.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

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

    @GetMapping("/{id}")
    public ResponseEntity<Object> GetCourseById(@PathVariable Long id) {
        try {
            Course course = courseService.GetCourseById(id);
            return ok().body(course);
        } catch (CourseNotFoundException e) {
            return status(HttpStatus.BAD_REQUEST).body("{\"error\": \"" + e.getMessage() + "\"}");
        }

    }

    @PostMapping
    public ResponseEntity<Object> saveCourse(@RequestBody CourseDto courseDto) {
        if (courseDto.getCourseName().isEmpty() || courseDto.getCourseName() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Course title is required\"}");
        try {
            Course course = courseService.SaveCourse(courseDto);
            return status(HttpStatus.CREATED).body(course);
        } catch (Exception e) {
            return status(HttpStatus.BAD_REQUEST).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        if (courseDto.getCourseName().isEmpty() || courseDto.getCourseName() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Course title is required\"}");
        try {
            Course course = courseService.UpdateCourse(id, courseDto);
            return status(HttpStatus.CREATED).body(course);
        } catch (Exception e) {
            return status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable Long id) {
        try {
            courseService.DeleteById(id);
            return status(HttpStatus.OK).build();
        }
        catch (Exception e){
            return status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

}
