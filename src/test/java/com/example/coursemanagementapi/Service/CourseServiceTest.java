//package com.example.coursemanagementapi.Service;
//
//import com.example.coursemanagementapi.CourseDto;
//import com.example.coursemanagementapi.CourseExistsException;
//import com.example.coursemanagementapi.CourseNotFoundException;
//import com.example.coursemanagementapi.Entity.Course;
//import com.example.coursemanagementapi.Repository.CourseRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//import static java.util.Arrays.asList;
//import static org.mockito.Mockito.*;
//
//
//public class CourseServiceTest {
//
//    private CourseService courseService;
//    private CourseRepository courseRepository;
//
//    @BeforeEach
//    void beforeEach() {
//        courseRepository = mock(CourseRepository.class);
//        courseService = new CourseService(courseRepository);
//    }
//
//    @Test
//    void shouldReturnAllCourses() {
//        List<Course> CourseList = asList(mock(Course.class), mock(Course.class));
//
//        when(courseRepository.findAll()).thenReturn(CourseList);
//        courseService.getCourses();
//
//        verify(courseRepository).findAll();
//    }
//
//    @Test
//    void shouldReturnCourseByGivenId() throws CourseNotFoundException {
//        LocalDateTime created_at = LocalDateTime.of(2021, 06, 30, 18, 30, 0);
//        Course course = new Course(1L, "API Development using SpringBoot", "course description here", created_at, null);
//
//        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
//        courseService.getCourseById(1L);
//
//        verify(courseRepository).findById(1L);
//    }
//
//    @Test
//    void shouldThrowExceptionGetCourseIdIsNotFound() {
//        when(courseRepository.findById(1L)).thenReturn(Optional.empty());
//
//        Assertions.assertThrows(CourseNotFoundException.class, () -> courseService.deleteById(1L));
//    }
//
//    @Test
//    void shouldSaveCourse() throws CourseExistsException {
//
//        CourseDto courseDto = new CourseDto() {
//        };
//        courseDto.setCourseName("API Development using SpringBoot");
//        courseDto.setDescripton("course description here");
//
//
//        when(courseRepository.findByCourseNameIgnoreCase("API Development using SpringBoot")).thenReturn(Optional.empty());
//
//        courseService.saveCourse(courseDto);
//
//        verify(courseRepository).findByCourseNameIgnoreCase("API Development using SpringBoot");
//        verify(courseRepository).save(any(Course.class));
//
//    }
//
//    @Test
//    void shouldThrowExceptionWhenCourseTitleIsAlreadyPresent() {
//        CourseDto courseDto = new CourseDto() {
//        };
//        courseDto.setCourseName("Test course");
//        courseDto.setDescripton("Test description here");
//
//
//        when(courseRepository.findByCourseNameIgnoreCase("Test course")).thenReturn(Optional.of(new Course()));
//
//        Assertions.assertThrows(CourseExistsException.class, () -> courseService.saveCourse(courseDto));
//    }
//
//    @Test
//    void shouldUpdateCourse() throws CourseNotFoundException {
//        LocalDateTime created_at = LocalDateTime.of(2021, 06, 30, 18, 30, 0);
//        Course course = new Course(1L, "API Development using SpringBoot", "course description here", created_at, null);
//        CourseDto courseDto = new CourseDto() {
//        };
//        courseDto.setCourseName("API Development using SpringBoot");
//        courseDto.setDescripton("course description here");
//
//        Long id = 1L;
//
//        when(courseRepository.findById(id)).thenReturn(Optional.of(course));
//        when(courseRepository.findByCourseNameIgnoreCase("API Development using SpringBoot")).thenReturn(Optional.empty());
//
//        courseService.updateCourse(id, courseDto);
//
//        verify(courseRepository).save(any(Course.class));
//    }
//
//    @Test
//    void shouldThrowExceptionWhenUpdateCourseIsCalledWithInvalidId() {
//        when(courseRepository.findById(1L)).thenReturn(Optional.empty());
//
//        Assertions.assertThrows(CourseNotFoundException.class, () -> courseService.deleteById(1L));
//    }
//
//    @Test
//    void shouldDeleteCourse() throws CourseNotFoundException {
//        Course course = new Course();
//        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
//
//        courseService.deleteById(1L);
//
//        verify(courseRepository, times(1)).deleteById(1L);
//
//    }
//
//    @Test
//    void shouldThrowExceptionWhenDeleteIsInvokedWithInvalidId() {
//        when(courseRepository.findById(1L)).thenReturn(Optional.empty());
//
//        Assertions.assertThrows(CourseNotFoundException.class, () -> courseService.deleteById(1L));
//
//    }
//
//}
