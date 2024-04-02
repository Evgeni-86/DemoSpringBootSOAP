package com.example.springbootsoap.service;

import com.example.springbootsoap.entity.Course;
import com.example.springbootsoap.repository.CourseRepository;
import com.example.springbootsoap.repository.CourseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseDetailsService {
    @Autowired
    private CourseRepository courseRepository;

    public Course getCourse(Integer id) {
        return courseRepository.getCourse(id);
    }

    public List<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }

    public CourseRepositoryImpl.Status deleteCourse(Integer id) {
        return courseRepository.deleteCourse(id);
    }
}