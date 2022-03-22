package com.example.coursemanagementapi.Service;

import com.example.coursemanagementapi.Entity.Course;
import com.example.coursemanagementapi.Repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
}
