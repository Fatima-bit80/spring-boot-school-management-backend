package com.dtp.school_management_backend.service.impl;


import com.dtp.school_management_backend.dao.*;
import com.dtp.school_management_backend.entity.*;
import com.dtp.school_management_backend.service.CourseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;


    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, MemberRepository memberRepository) {
        this.courseRepository = courseRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Course> findAllCourses(String email) {
        Member m =  memberRepository.findById(email).get();

        List<Course> courses = courseRepository.findAll();


        return courses;
    }

    @Override
    public List<Course> findAvailableCourses(String email) {
        Member member = memberRepository.findById(email).get();
        int studentId = member.getStudent().getStudentId();
        List<Course> courses = courseRepository.findAvailableCoursesForStudent(studentId);


        return courses;
    }


    @Override
    public List<Course> findCoursesOfStudent(String email) {
        Member member = memberRepository.findById(email).get();
            int studentId = member.getStudent().getStudentId();
            return courseRepository.findCoursesOfStudent(studentId);

    }

    @Override
    public List<Course> findCoursesOfTeacher(String email) {
        Member member = memberRepository.findById(email).get();
            int teacherId = member.getTeacher().getTeacherId();
            return courseRepository.findCoursesOfTeacher(teacherId);
    }


    @Override
    @Transactional
    public Course saveCourse(Course course) {
       return courseRepository.save(course);
    }




}
