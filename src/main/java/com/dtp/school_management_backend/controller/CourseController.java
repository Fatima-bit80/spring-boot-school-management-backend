package com.dtp.school_management_backend.controller;


import com.dtp.school_management_backend.dto.CourseDTO;
import com.dtp.school_management_backend.entity.Course;
import com.dtp.school_management_backend.mapper.SchoolMapper;
import com.dtp.school_management_backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private CourseService courseService;
    private SchoolMapper schoolMapper;

    @Autowired
    public void setCourseService(CourseService courseService, SchoolMapper schoolMapper) {
        this.courseService = courseService;
        this.schoolMapper = schoolMapper;
    }

    @GetMapping
    public List<CourseDTO> getAllCourses(){
        List<Course> courses = courseService.findAllCourses();


        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course course : courses) {
            courseDTOS.add(schoolMapper.courseToCourseDto(course));
        }

        return courseDTOS;
    }

    @GetMapping("/enrolled")
    public List<CourseDTO> getEnrolledCourses(Authentication authentication)
    {
        String email = authentication.getName();

        List<Course> courses = courseService.findCoursesOfStudent(email);


        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course course : courses) {
            courseDTOS.add(schoolMapper.courseToCourseDto(course));
        }

        return courseDTOS;
    }

    @GetMapping("/taught")
    public List<CourseDTO> getTaughtCourses(Authentication authentication)
    {
        String email = authentication.getName();

        List<Course> courses = courseService.findCoursesOfTeacher(email);


        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course course : courses) {
            courseDTOS.add(schoolMapper.courseToCourseDto(course));
        }

        return courseDTOS;
    }

    //for student to see available courses
    @GetMapping("/available")
    public List<CourseDTO> getAvailableCourses(Authentication authentication)
    {
        String email = authentication.getName();

       List<Course> courses = courseService.findAvailableCourses(email);


        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course course : courses) {
            courseDTOS.add(schoolMapper.courseToCourseDto(course));
        }

        return courseDTOS;
    }


    @PostMapping
    public CourseDTO saveCourse(CourseDTO courseDTO) {

        Course c = schoolMapper.courseDtoToCourse(courseDTO);
       Course savedCourse = courseService.saveCourse(c);

        return schoolMapper.courseToCourseDto(savedCourse);
    }
}
