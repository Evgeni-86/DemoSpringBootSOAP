package com.example.springbootsoap.repository;

import com.example.springbootsoap.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseJpaRepository extends JpaRepository<Course, Integer> {
}