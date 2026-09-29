package com.dtp.school_management_backend.service.impl;

import com.dtp.school_management_backend.dao.CourseRepository;
import com.dtp.school_management_backend.dao.EnrollmentRepository;
import com.dtp.school_management_backend.dao.MemberRepository;
import com.dtp.school_management_backend.dto.EnrollmentDTO;
import com.dtp.school_management_backend.entity.*;
import com.dtp.school_management_backend.exception.ForbiddenException;
import com.dtp.school_management_backend.exception.NotFoundException;
import com.dtp.school_management_backend.service.EnrollmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            List<Enrollment> enrollments = enrollmentRepository.findEnrollmentForTeachersCourses(teacherId);

            return enrollments;

        } else if (member.getStudent()!=null) {
            int studentId = member.getStudent().getStudentId();
            List<Enrollment> enrollments = enrollmentRepository.findEnrollmentsForStudent(studentId);
            return enrollments;
        }

        //else -> admin
        return enrollmentRepository.findAll();


    }



    @Override
    @Transactional
    public void deleteEnrollment(int enrollmentId, String email)  {

        Member member = memberRepository.findById(email).get();

        Optional<Enrollment> enrollment = enrollmentRepository.findById(enrollmentId);

        if(!enrollment.isPresent()){
            throw new NotFoundException("enrollment doesn't exist");
        }

        Enrollment e = enrollment.get();



        if((member.getStudent() != null && member.getStudent().getStudentId() != e.getStudent().getStudentId())){
            throw new ForbiddenException("Student can't delete another student's enrollment");
        }

        if( (member.getTeacher()!=null) && (e.getCourse().getTeacher().getTeacherId()) != member.getTeacher().getTeacherId()){
            throw new ForbiddenException("teacher can't delete an enrollment of another teacher's course");
        }
        enrollmentRepository.deleteById(enrollmentId);
    }


    @Override
    @Transactional
    public Enrollment saveEnrollment(String courseCode, String email) {

        Member member = memberRepository.findById(email).get();
        Student student = member.getStudent();
        Optional<Course> course1 = courseRepository.findById(courseCode);
        if(!course1.isPresent()){
            throw new NotFoundException("course not found");
        }
        Course course = course1.get();
        Enrollment e = new Enrollment(course,student);

        return  enrollmentRepository.save(e);

    }



    @Override
    @Transactional
    // update approved or grades
    public Enrollment updateEnrollment(int enrollmentId, EnrollmentDTO enrollment, String email)  {
        Optional<Enrollment> enrollment1 = enrollmentRepository.findById(enrollmentId);
        if(!enrollment1.isPresent()){
            throw new NotFoundException("enrollment not found");
        }
        Enrollment e = enrollment1.get();

        Member member = memberRepository.findById(email).get();

        if((member.getTeacher()!= null) && (member.getTeacher().getTeacherId() != e.getCourse().getTeacher().getTeacherId())) {
            throw new ForbiddenException("teacher can't update an enrollment of another teacher's course");
        }

        if(enrollment.getApproved() != null)
        e.setApproved(enrollment.getApproved());

        if (enrollment.getGrade()!= null)
        e.setGrade(enrollment.getGrade());


        return enrollmentRepository.save(e);
    }



    @Override
    public List<Enrollment> findEnrollmentsOfCourse(String code,String email) {

        Member member = memberRepository.findById(email).get();

        Optional<Course> course = courseRepository.findById(code);
        if(!course.isPresent())
            throw new NotFoundException("course with code "+code+" not found");

        if(member.getTeacher() != null ){
            Teacher teacher = member.getTeacher();
            int teacherId = teacher.getTeacherId();

            Course c = course.get();
            if(c.getTeacher().getTeacherId() != teacherId){
                throw new ForbiddenException("teacher can't access an enrollment of another teacher's course");
            }
        }

        return enrollmentRepository.findEnrollmentsForCourse(code);
    }



}
