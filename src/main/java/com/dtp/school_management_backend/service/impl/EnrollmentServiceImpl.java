package com.dtp.school_management_backend.service.impl;

import com.dtp.school_management_backend.dao.CourseRepository;
import com.dtp.school_management_backend.dao.EnrollmentRepository;
import com.dtp.school_management_backend.dao.MemberRepository;
import com.dtp.school_management_backend.entity.*;
import com.dtp.school_management_backend.service.EnrollmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final MemberRepository memberRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository,MemberRepository memberRepository,CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.memberRepository = memberRepository;
        this.courseRepository = courseRepository;
    }



    @Override
    public List<Enrollment> findEnrollments(String email) {

        Member member = memberRepository.findById(email).get();

        if(member.getTeacher() != null){
            int teacherId = member.getTeacher().getTeacherId();
            List<Enrollment> enrollments = enrollmentRepository.findEnrollmentRequestsForTeacher(teacherId);

            return enrollments;

        } else if (member.getStudent()!=null) {
            int studentId = member.getStudent().getStudentId();
            List<Enrollment> enrollments = enrollmentRepository.findEnrollmentsForStudent(studentId);
            return enrollments;
        }
        return enrollmentRepository.findAll();


    }



    @Override
    @Transactional
    public void deleteEnrollment(int enrollmentId, String email) {

        Member member = memberRepository.findById(email).get();

        Enrollment e = enrollmentRepository.findById(enrollmentId).get();

        if((member.getStudent() != null && member.getStudent().getStudentId() != enrollmentId)
                ||(member.getTeacher()!=null && e.getCourse().getTeacher().getTeacherId() != enrollmentId)){
            //todo throw exception enrollmetn does belong to stuednt/ request isn't for teacher
        }

        enrollmentRepository.deleteById(enrollmentId);
    }


    @Override
    @Transactional
    public Enrollment saveEnrollment(String courseCode, String email) {

        Member member = memberRepository.findById(email).get();
        Student student = member.getStudent();
        Course course = courseRepository.findById(courseCode).get();
        Enrollment e = new Enrollment(course,student);

        return  enrollmentRepository.save(e);


    }



    @Override
    @Transactional
    // update approved or grades
    public Enrollment updateEnrollment(int enrollmentId,Enrollment enrollment,String email){
        Enrollment e = enrollmentRepository.findById(enrollmentId).get();

        Member member = memberRepository.findById(email).get();

        if((member.getTeacher()!= null) && (member.getTeacher().getTeacherId() != e.getCourse().getTeacher().getTeacherId())) {
            //todo throw an exception that this enrollment doesn't belong to the teacher
        }

        e.setApproved(enrollment.getApproved());
        e.setGrade(enrollment.getGrade());


        return enrollmentRepository.save(e);
    }



    @Override
    public List<Enrollment> findEnrollmentsOfCourse(String code,String email) {
        Member member = memberRepository.findById(email).get();


        if(member.getTeacher() != null ){
            Teacher teacher = member.getTeacher();
            int teacherId = teacher.getTeacherId();
            Course c =  courseRepository.findById(code).get();
            if(c.getTeacher().getTeacherId() != teacherId){
                //todo throw exception that this teacher cant access the enrollments of this course
            }
        }

        return enrollmentRepository.findEnrollmentsForCourse(code);
    }



}
