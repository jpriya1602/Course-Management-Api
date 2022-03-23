package com.example.coursemanagementapi.Controller;

import com.example.coursemanagementapi.CourseDto;
import com.example.coursemanagementapi.CourseManagementApiApplication;
import com.example.coursemanagementapi.CourseNotFoundException;
import com.example.coursemanagementapi.Entity.Course;
import com.example.coursemanagementapi.Service.CourseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest(classes = CourseManagementApiApplication.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @Test
    void shouldReturnListOfCourses() throws Exception {
        LocalDateTime created_at = LocalDateTime.of(2021, 06, 30, 18, 30, 0);
        Course course = new Course(1L, "API Development using SpringBoot", "course description here", created_at, null);
        List<Course> courses = new ArrayList<>();
        courses.add(course);

        when(courseService.GetCourses()).thenReturn(courses);

        mockMvc.perform(
                        get("/api/courses"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson(courses)));
    }

    @Test
    void shouldSaveCourse() throws Exception {
        CourseDto courseDto = new CourseDto() {
        };
        courseDto.setCourseName("API Development using SpringBoot");
        courseDto.setDescripton("course description here");

        String courseRequestDto = "{ \"courseName\":\"API Development using SpringBoot\",\"Description\": \"course description here\"}";

        LocalDateTime created_at = LocalDateTime.of(2022, 03, 23, 07, 43, 57,243345);
        Course course = new Course(1L, "API Development using SpringBoot", "course description here", created_at, null);

        when(courseService.SaveCourse(courseDto)).thenReturn(course);
        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courseRequestDto))
                        .andExpect(status().isCreated());

    }

    @Test
    void shouldUpdateCourse() throws Exception {
        CourseDto courseDto = new CourseDto() {
        };
        courseDto.setCourseName("API Development using SpringBoot");
        courseDto.setDescripton("Added course description here");

        String courseRequestDto = "{ \"courseName\":\"API Development using SpringBoot\",\"Description\": \"course description here\"}";
        Long id = 1L;

        LocalDateTime created_at = LocalDateTime.of(2021, 06, 30, 18, 30, 0);
        Course course = new Course(id, "API Development using SpringBoot", "course description here", created_at, null);

        when(courseService.UpdateCourse(id,courseDto)).thenReturn(course);
        mockMvc.perform(put("/api/courses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courseRequestDto))
                        .andExpect(status().isCreated());


    }

    @Test
    void shouldThrowErrorWhenCourseIdIsNotFoundForUpdate() throws Exception {

        String courseRequestDto = "{\"Description\": \"course description here\"}";

        mockMvc.perform(put("/api/courses/1L")
                .contentType(MediaType.APPLICATION_JSON)
                .content(courseRequestDto))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldBeAbleToDeleteByCourseId() throws Exception {
        mockMvc.perform(delete("/api/courses/" + 1));

        verify(courseService, times(1)).DeleteById(1L);
    }

    @Test
    void shouldThrowErrorWhenIdIsNotFoundToDelete() throws Exception {
        doThrow(new CourseNotFoundException("course with Id = 3 not found")).when(courseService).DeleteById(3L);
        mockMvc.perform(delete("/api/courses/3"))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"error\":\"course with Id = 3 not found\"}"));
    }

    private String expectedJson(List<Course> courses) throws JsonProcessingException {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return jsonMapper.writeValueAsString(courses);
    }
    private String expectedJson(Course course) throws JsonProcessingException {
        ObjectMapper writer = new ObjectMapper();
        writer.registerModule(new JavaTimeModule());
        writer.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return writer.writeValueAsString(course);
    }
}
