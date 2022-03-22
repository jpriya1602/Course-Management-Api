package com.example.coursemanagementapi.Entity;

//import org.hibernate.annotations.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


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
    @NotNull
    @Column(name = "COURSE_NAME")
    private String courseName;

    @JsonProperty
    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @JsonProperty
    @NotNull
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

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
