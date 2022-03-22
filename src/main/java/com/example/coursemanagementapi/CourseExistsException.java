package com.example.coursemanagementapi;

public class CourseExistsException extends Exception {
    public CourseExistsException(String message) {
        super(message);
    }
}
