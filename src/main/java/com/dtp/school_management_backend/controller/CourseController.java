package com.dtp.school_management_backend.controller;


import com.dtp.school_management_backend.dto.CourseDTO;
import com.dtp.school_management_backend.entity.Course;
import com.dtp.school_management_backend.mapper.SchoolMapper;
import com.dtp.school_management_backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private CourseService courseService;
    private SchoolMapper schoolMapper;

    @Autowired
    public CourseController(CourseService courseService, SchoolMapper schoolMapper) {
        this.courseService = courseService;
        this.schoolMapper = schoolMapper;
    }

    //anyone can view all courses
    //admins can view everything
    //teachers/students can view restricted info
    @GetMapping
    public List<CourseDTO> getAllCourses(Authentication authentication) {

        String email = authentication.getName();

        List<Course> courses = courseService.findAllCourses(email);

        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course course : courses) {
            courseDTOS.add(schoolMapper.courseToCourseDto(course,true));
        }

        return courseDTOS;
    }

    //for student to view available courses (not enrolled in them)
    @GetMapping("/available")
    public List<CourseDTO> getAvailableCourses(Authentication authentication)
    {
        String email = authentication.getName();

        List<Course> courses = courseService.findAvailableCourses(email);

        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course course : courses) {
            courseDTOS.add(schoolMapper.courseToCourseDto(course,false));
        }

        return courseDTOS;
    }

    //students can get courses they're enrolled in
    @GetMapping("/enrolled")
    public List<CourseDTO> getEnrolledCourses(Authentication authentication)
    {
        String email = authentication.getName();

        List<Course> courses = courseService.findCoursesOfStudent(email);

        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course course : courses) {
            courseDTOS.add(schoolMapper.courseToCourseDto(course,false));
        }

        return courseDTOS;
    }


    //teachers can get courses they teach
    @GetMapping("/taught")
    public List<CourseDTO> getTaughtCourses(Authentication authentication)
    {
        String email = authentication.getName();


        List<Course> courses = courseService.findCoursesOfTeacher(email);

        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course course : courses) {
            courseDTOS.add(schoolMapper.courseToCourseDto(course,false));
        }

        return courseDTOS;
    }




    // admins can save courses
    @PostMapping
    public CourseDTO saveCourse(@RequestBody CourseDTO courseDTO) {

        Course c = schoolMapper.courseDtoToCourse(courseDTO);
       Course savedCourse = courseService.saveCourse(c);

        return schoolMapper.courseToCourseDto(savedCourse,true);
    }
}
