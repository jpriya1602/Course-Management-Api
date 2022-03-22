package com.example.coursemanagementapi.Service;

import com.example.coursemanagementapi.CourseDto;
import com.example.coursemanagementapi.CourseExistsException;
import com.example.coursemanagementapi.CourseNotFoundException;
import com.example.coursemanagementapi.Entity.Course;
import com.example.coursemanagementapi.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> GetCourses() {
        return courseRepository.findAll();
    }

    public Course GetCourseById(Long id) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new CourseNotFoundException("course with " + id + "not found");
        }
        return course.get();
    }

    public Course SaveCourse(CourseDto courseDto) throws CourseExistsException {

        if (CheckCourseAlreadyExists(courseDto.getCourseName())) {
            throw new CourseExistsException("course with " + courseDto.getCourseName() + " already exists");
        }

        Course newCourse = new Course(courseDto.getCourseName(), courseDto.getDescription(), LocalDateTime.now(), null);
        return courseRepository.save(newCourse);
    }

    public Course UpdateCourse(long id,CourseDto courseDto) throws CourseNotFoundException{

        Course course = GetCourseById(id);
        course.setCourseName(courseDto.getCourseName());
        course.setDescription(courseDto.getDescription());
        course.setUpdatedAt(LocalDateTime.now());

        return courseRepository.save(course);
    }



    private boolean CheckCourseAlreadyExists(String courseName) throws CourseExistsException {
        Optional<Course> course = courseRepository.findByCourseNameIgnoreCase(courseName);
        if (!course.isEmpty()) {
            return true;
        }
        return false;
    }



}
