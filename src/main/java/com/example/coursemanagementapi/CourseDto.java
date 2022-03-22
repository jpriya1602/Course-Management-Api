package com.example.coursemanagementapi;

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

    public String getDescripton() {
        return Description;
    }


    public String CourseName;
    public String Description;
}
