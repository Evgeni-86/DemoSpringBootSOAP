package com.example.springbootsoap.repository;

import com.example.springbootsoap.entity.Course;

import java.util.List;


public interface CourseRepository {
    Course getCourse(Integer id);
    List<Course> getAllCourses();
    CourseRepositoryImpl.Status deleteCourse(Integer id);
}