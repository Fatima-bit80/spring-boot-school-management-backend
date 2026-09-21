package com.dtp.school_management_backend.mapper;

import com.dtp.school_management_backend.dao.*;
import com.dtp.school_management_backend.dto.*;
import com.dtp.school_management_backend.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class SchoolMapperImpl implements  SchoolMapper {

    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private CourseRepository courseRepository;
    private EnrollmentRepository enrollmentRepository;

    //todo check if this is the best way to map

    @Autowired
    public SchoolMapperImpl(StudentRepository studentRepository, TeacherRepository teacherRepository, CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public StudentDTO studentToStudentDto(Student student) {
       return new StudentDTO(student.getMember().getEmail(), student.getMember().getPassword(),student.getMember().getRole(),student.getMember().getActive(),student.getStudentId(),student.getFirstName(),student.getLastName(),student.getYear());
    }

    @Override
    public Student studentDtoToStudent(StudentDTO studentDTO) {
        return new Student(studentDTO.getStudentId(),studentDTO.getFirstName(),studentDTO.getLastName(),studentDTO.getYear());
    }

    @Override
    public TeacherDTO teacherToTeacherDto(Teacher teacher) {
        return new TeacherDTO(teacher.getMember().getEmail(), teacher.getMember().getPassword(),teacher.getMember().getRole(),teacher.getMember().getActive(),teacher.getTeacherId(),teacher.getFirstName(),teacher.getLastName());
    }

    @Override
    public Teacher teacherDtoToTeacher(TeacherDTO teacherDTO) {
        return new Teacher(teacherDTO.getTeacherId(),teacherDTO.getFirstName(),teacherDTO.getLastName());
    }

    @Override
    public EnrollmentDTO enrollmentToEnrollmentDto(Enrollment enrollment) {
        return new EnrollmentDTO(enrollment.getId(),enrollment.getCourse().getCode(),enrollment.getStudent().getStudentId(),enrollment.getGrade(),enrollment.getApproved());
    }

    @Override
    public Enrollment enrollmentDtoToEnrollment(EnrollmentDTO enrollmentDTO) {
        return new Enrollment(enrollmentDTO.getId(),courseRepository.findById(enrollmentDTO.getCourseCode()).get(), studentRepository.findById(enrollmentDTO.getStudentId()).get(),enrollmentDTO.getGrade(),enrollmentDTO.getApproved());
    }

    @Override
    public CourseDTO courseToCourseDto(Course course) {

        List<Integer> enrollments = new ArrayList<>();

        for (Enrollment e : course.getEnrollments()){
            enrollments.add(e.getId());
        }

        return new CourseDTO(course.getCode(),course.getName(),course.getYear(),course.getTeacher().getTeacherId(),enrollments);
    }

    @Override
    public Course courseDtoToCourse(CourseDTO courseDTO) {
        List<Enrollment> enrollments = new ArrayList<>();
        for (int enrollmentId:courseDTO.getEnrollments()){
            enrollments.add(enrollmentRepository.findById(enrollmentId).get());
        }
        return new Course(courseDTO.getCode(),courseDTO.getName(),courseDTO.getYear(),teacherRepository.findById(courseDTO.getTeacherId()).get(),enrollments);
    }

    @Override
    public MemberDTO memberToMemberDTO(Member member) {
        return new MemberDTO(member.getEmail(),member.getPassword(),member.getRole(),member.getActive());
    }

    @Override
    public Member memberDtoToMember(MemberDTO memberDTO){
        return new Member(memberDTO.getEmail(),memberDTO.getPassword(),memberDTO.getRole(),memberDTO.getActive());

    }
}
