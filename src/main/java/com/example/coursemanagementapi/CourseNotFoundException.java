package com.example.coursemanagementapi;

public class CourseNotFoundException extends Throwable {
    public CourseNotFoundException(String message) {
        super(message);
    }
}
