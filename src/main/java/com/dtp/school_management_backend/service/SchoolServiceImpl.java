package com.dtp.school_management_backend.service;

import com.example.schoolManagement.dao.*;
import com.example.schoolManagement.dto.CourseDTO;
import com.example.schoolManagement.dto.GradeRow;
import com.example.schoolManagement.dto.GradesForm;
import com.example.schoolManagement.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements  SchoolService {

    private CourseRepository courseRepository;
    private EnrollmentRepository enrollmentRepository;
    private MemberRepository memberRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public SchoolServiceImpl(CourseRepository courseRepository,EnrollmentRepository enrollmentRepository,MemberRepository memberRepository,StudentRepository studentRepository,TeacherRepository teacherRepository,PasswordEncoder passwordEncoder) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.memberRepository = memberRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
    }




    @Override
    @Transactional
    public void saveStudent(Student student) {
        Member member = student.getMember();
        String plainTextPassword = member.getPassword();
        String encodedPassword = passwordEncoder.encode(plainTextPassword);
        member.setPassword(encodedPassword);

        studentRepository.save(student);
    }

    @Override
    @Transactional
    public void saveTeacher(Teacher teacher) {
        Member member = teacher.getMember();
        String plainTextPassword = member.getPassword();
        String encodedPassword = passwordEncoder.encode(plainTextPassword);
        member.setPassword(encodedPassword);
        teacherRepository.save(teacher);
    }

    @Override
    public Member findMemberByEmail(String email) {
       return memberRepository.findById(email).get();
    }

    @Override
    public List<Enrollment> findEnrollmentsOfStudent(int studentId) {
        return enrollmentRepository.findEnrollmentsForStudent(studentId);
    }

    @Override
    public List<Course> findAvailableCourses(int studentId) {
        return courseRepository.findAvailableCoursesForStudent(studentId);
    }

    @Override
    @Transactional
    public void deleteEnrollment(int id) {
        enrollmentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveEnrollment(String courseCode, int studentId) {
        Student student = studentRepository.findById(studentId).get();
        Course course = courseRepository.findById(courseCode).get();
        Enrollment e = new Enrollment(course,student);
        enrollmentRepository.save(e);
    }

    @Override
    public List<Enrollment> findEnrollmentRequestsForTeacher(int teacherId) {
        return enrollmentRepository.findEnrollmentRequestsForTeacher(teacherId);
    }

    @Override
    @Transactional
    public void acceptEnrollmentRequest(int requestId) {
        Enrollment e = enrollmentRepository.findById(requestId).get();
        e.setApproved(1);
        enrollmentRepository.save(e);
    }

    @Override
    public List<Course> findCoursesByTeacherId(int teacherId) {
        return courseRepository.findCoursesForTeacher(teacherId);
    }

    @Override
    public List<Enrollment> findEnrollmentsOfCourse(String code) {
        return enrollmentRepository.findEnrollmentsForCourse(code);
    }

    @Override
    @Transactional
    public void updateGrades(GradesForm gradesForm) {
        for(GradeRow row : gradesForm.getRows()) {
            Enrollment e = enrollmentRepository.findById(row.getEnrollmentId()).get();
            e.setGrade(row.getGrade());

enrollmentRepository.save(e);
        }
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    @Transactional
    public void saveCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setCode(courseDTO.getCode());
        course.setName(courseDTO.getName());
        course.setYear(courseDTO.getYear());

        Teacher teacher = teacherRepository.findById(courseDTO.getTeacherId()).get();
        course.setTeacher(teacher);

        courseRepository.save(course);
    }


}
