package com.example.springbootsoap.web;

import com.example.springbootsoap.entity.Course;
import com.example.springbootsoap.repository.CourseRepositoryImpl;
import com.example.springbootsoap.service.CourseDetailsService;
import com.example.springbootsoap.cources.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;


@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    private CourseDetailsService courseDetailsService;

    @PayloadRoot(namespace = "http://springbootsoap.example.com/cources", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
        Course course = courseDetailsService.getCourse(request.getId());
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        response.setCourseDetails(mapCourse(course));
        return response;
    }

    private CourseDetails mapCourse(Course course) {
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());
        return courseDetails;
    }

    @PayloadRoot(namespace = "http://springbootsoap.example.com/cources", localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
        List<Course> courseList = courseDetailsService.getAllCourses();
        courseList.stream().forEach(course -> response.getCourseDetails().add(mapCourse(course)));
        return response;
    }

    @PayloadRoot(namespace = "http://springbootsoap.example.com/cources", localPart = "DeleteCourseRequest")
    @ResponsePayload
    public DeleteCourseResponse processDeleteCourseRequest(@RequestPayload DeleteCourseRequest request) {
        DeleteCourseResponse response = new DeleteCourseResponse();
        CourseRepositoryImpl.Status status = courseDetailsService.deleteCourse(request.getId());
        com.example.springbootsoap.cources.Status statusResponse = Status.fromValue(status.toString());
        response.setStatus(statusResponse);
        return response;
    }
}