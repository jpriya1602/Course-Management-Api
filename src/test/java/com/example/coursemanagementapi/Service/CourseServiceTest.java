package com.example.coursemanagementapi.Service;

import com.example.coursemanagementapi.CourseDto;
import com.example.coursemanagementapi.CourseExistsException;
import com.example.coursemanagementapi.CourseNotFoundException;
import com.example.coursemanagementapi.Entity.Course;
import com.example.coursemanagementapi.Repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;


public class CourseServiceTest {

   private CourseService courseService;
   private CourseRepository courseRepository;

   @BeforeEach
   void beforeEach(){
       courseRepository = mock(CourseRepository.class);
       courseService = new CourseService(courseRepository);
   }

    @Test
    void shouldReturnAllCourses() {
        List<Course> CourseList = asList(mock(Course.class));

        when(courseRepository.findAll()).thenReturn(CourseList);
        courseService.GetCourses();

        verify(courseRepository).findAll();
    }

    @Test
    void shouldReturnCourseByGivenId() throws CourseNotFoundException {
        LocalDateTime created_at = LocalDateTime.of(2021, 06, 30, 18, 30, 0);
        Course course = new Course(1L,"API Development using SpringBoot","course description here",created_at,null);

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        courseService.GetCourseById(1L);

        verify(courseRepository).findById(1L);
    }

    @Test
    void shouldSaveCourse() throws CourseExistsException {
        LocalDateTime created_at = LocalDateTime.of(2021, 06, 30, 18, 30, 0);
        Course course = new Course(1L,"API Development using SpringBoot","course description here",created_at,null);

        CourseDto courseDto = new CourseDto(){};
        courseDto.setCourseName("API Development using SpringBoot");
        courseDto.setDescripton("course description here");

        when(courseRepository.findByCourseNameIgnoreCase("API Development using SpringBoot")).thenReturn(Optional.empty());
        when(courseRepository.save(course)).thenReturn(course);

        courseService.SaveCourse(courseDto);

        verify(courseRepository).findByCourseNameIgnoreCase("API Development using SpringBoot");
        // donot know how to check the save call ( differrent object)
    }
}
