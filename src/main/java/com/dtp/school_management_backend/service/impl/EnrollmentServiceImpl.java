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
    public void deleteEnrollment(int enrollmentId, String email) throws IllegalAccessException {

        Member member = memberRepository.findById(email).get();

        Enrollment e = enrollmentRepository.findById(enrollmentId).get();



        if((member.getStudent() != null && member.getStudent().getStudentId() != e.getStudent().getStudentId())){
            throw new IllegalAccessException("Student can't delete another student's enrollment");
        }

        if( (member.getTeacher()!=null) && (e.getCourse().getTeacher().getTeacherId()) != e.getCourse().getTeacher().getTeacherId()){
            throw new IllegalAccessException("teacher can't delete an enrollment of another teacher's course");
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
    public Enrollment updateEnrollment(int enrollmentId,Enrollment enrollment,String email) throws IllegalAccessException {
        Enrollment e = enrollmentRepository.findById(enrollmentId).get();

        Member member = memberRepository.findById(email).get();

        if((member.getTeacher()!= null) && (member.getTeacher().getTeacherId() != e.getCourse().getTeacher().getTeacherId())) {
            throw new IllegalAccessException("teacher can't update an enrollment of another teacher's course");
        }

        if(enrollment.getApproved() !=-1)
        e.setApproved(enrollment.getApproved());

        if (enrollment.getGrade()!=-1)
        e.setGrade(enrollment.getGrade());


        return enrollmentRepository.save(e);
    }



    @Override
    public List<Enrollment> findEnrollmentsOfCourse(String code,String email) throws IllegalAccessException {
        Member member = memberRepository.findById(email).get();


        if(member.getTeacher() != null ){
            Teacher teacher = member.getTeacher();
            int teacherId = teacher.getTeacherId();
            Course c =  courseRepository.findById(code).get();
            if(c.getTeacher().getTeacherId() != teacherId){
                throw new IllegalAccessException("teacher can't access an enrollment of another teacher's course");
            }
        }

        return enrollmentRepository.findEnrollmentsForCourse(code);
    }



}
