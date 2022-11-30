package com.example.coursemanagementapi.Entity;

//import org.hibernate.annotations.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "COURSE")
public class Course {


    @Id
    @JsonProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_ID")
    private Long id;

    @JsonProperty
    @Column(name = "COURSE_NAME")
    private String courseName;

    @JsonProperty
    @Column(name = "DESCRIPTION")
    private String description;

    @JsonProperty
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;



    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    public Course(String courseName, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.courseName = courseName;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Course(Long id, String courseName, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Course() {

    }
}
