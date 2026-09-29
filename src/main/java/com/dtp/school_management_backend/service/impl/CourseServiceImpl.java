package com.dtp.school_management_backend.service.impl;


import com.dtp.school_management_backend.dao.*;
import com.dtp.school_management_backend.entity.*;
import com.dtp.school_management_backend.exception.ForbiddenException;
import com.dtp.school_management_backend.exception.NotFoundException;
import com.dtp.school_management_backend.service.CourseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
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
    public void deleteCourse(String code) {

        Optional<Course> s = courseRepository.findById(code);
        if(s.isPresent()) {
            courseRepository.deleteById(code);
        }else {
            throw new NotFoundException("COURSE_NOT_FOUND");
        }

    }


    @Override
    @Transactional
    public Course saveCourse(Course course) {

        Optional<Course> c = courseRepository.findById(course.getCode());

        if(c.isPresent()) {
            throw new ForbiddenException("COURSE_ALREADY_EXISTS");
        }

       return courseRepository.save(course);
    }




}
