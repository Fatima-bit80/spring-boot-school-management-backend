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
    private final TeacherRepository teacherRepository;
    private final MemberRepository memberRepository;


    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, TeacherRepository teacherRepository, MemberRepository memberRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Course> findAvailableCourses(String email) {
        Member member = memberRepository.findById(email).get();
        int studentId = member.getStudent().getStudentId();
        return courseRepository.findAvailableCoursesForStudent(studentId);
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
        courseRepository.save(course);
        return course;
    }

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }


}
