package com.example.coursemanagementapi.Entity;

//import org.hibernate.annotations.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;


import javax.persistence.*;
import java.time.LocalDate;


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
    private LocalDate createdAt;

    @JsonProperty
    @NotNull
    @Column(name = "UPDATED_AT")
    private LocalDate updatedAt;

}
