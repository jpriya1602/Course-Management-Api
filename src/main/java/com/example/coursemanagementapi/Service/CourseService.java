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

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new CourseNotFoundException("course with Id = " + id + " not found");
        }
        return course.get();
    }

    public Course saveCourse(CourseDto courseDto) throws CourseExistsException {

        if (checkCourseAlreadyExists(courseDto.getCourseName())) {
            throw new CourseExistsException("course with tile " + courseDto.getCourseName() + " already exists");
        }

        Course newCourse = new Course(courseDto.getCourseName(), courseDto.getDescription(), LocalDateTime.now(), null);
        return courseRepository.save(newCourse);
    }

    public Course updateCourse(long id, CourseDto courseDto) throws CourseNotFoundException{

        Course course = getCourseById(id);
        course.setCourseName(courseDto.getCourseName());
        course.setDescription(courseDto.getDescription());
        course.setUpdatedAt(LocalDateTime.now());

        return courseRepository.save(course);
    }

    public void deleteById(Long id) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new CourseNotFoundException("course with " + id + " not found");
        }
        courseRepository.deleteById(id);
    }

    private boolean checkCourseAlreadyExists(String courseName){
        Optional<Course> course = courseRepository.findByCourseNameIgnoreCase(courseName);
        return course.isPresent();
    }



}
