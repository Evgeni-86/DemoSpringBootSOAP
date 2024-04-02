package com.example.springbootsoap.repository;

import com.example.springbootsoap.entity.Course;
import com.example.springbootsoap.exceptions.CourseNotFoundException;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Repository
public class CourseRepositoryImpl implements CourseRepository {
    public enum Status {
        SUCCESS, FAILURE;
    }
    @Autowired
    private CourseJpaRepository courseJpaRepository;
    @Autowired
    private EntityManager entityManager;

    private List<Course> courseList = new ArrayList<>();

    {
        courseList.add(new Course(10, "Course 10", "Course 10 test"));
        courseList.add(new Course(20, "Course 20", "Course 20 test"));
        courseList.add(new Course(30, "Course 30", "Course 30 test"));
    }

    @Override
    public Course getCourse(Integer id) {
        Optional<Course> course = courseList.stream().filter(e -> e.getId() == id).findFirst();
        return course.orElseThrow(()-> new CourseNotFoundException("Course not found by id = " + id));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseList;
    }

    @Override
    public Status deleteCourse(Integer id) {
        Iterator<Course> iterator = courseList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }
}