package com.example.coursemanagementapi;

import java.time.LocalDateTime;

public class CourseDto {

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public void setDescripton(String description) {
        Description = description;
    }

    public String getDescription() {
        return Description;
    }


    public String CourseName;
    public String Description;
}
