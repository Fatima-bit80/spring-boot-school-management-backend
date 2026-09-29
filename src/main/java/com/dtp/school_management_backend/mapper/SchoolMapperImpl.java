package com.dtp.school_management_backend.mapper;

import com.dtp.school_management_backend.dao.*;
import com.dtp.school_management_backend.dto.*;
import com.dtp.school_management_backend.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class SchoolMapperImpl implements SchoolMapper {

    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private CourseRepository courseRepository;
    private EnrollmentRepository enrollmentRepository;

    //entity to dto -> response
    // dto to entity -> request

    @Autowired
    public SchoolMapperImpl(StudentRepository studentRepository, TeacherRepository teacherRepository, CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public StudentDTO studentToStudentDto(Student student) {
        return new StudentDTO(student.getMember().getEmail(), null, student.getMember().getRole(), student.getMember().getActive(), student.getStudentId(), student.getFirstName(), student.getLastName(), student.getYear());
    }


    @Override
    public TeacherDTO teacherToTeacherDto(Teacher teacher) {
        return new TeacherDTO(teacher.getMember().getEmail(), null, teacher.getMember().getRole(), teacher.getMember().getActive(), teacher.getTeacherId(), teacher.getFirstName(), teacher.getLastName());
    }


    @Override
    public EnrollmentDTO enrollmentToEnrollmentDto(Enrollment enrollment) {
        return new EnrollmentDTO(enrollment.getId(), enrollment.getCourse().getCode(), enrollment.getStudent().getStudentId(), enrollment.getGrade(), enrollment.getApproved());
    }


    @Override
    public CourseDTO courseToCourseDto(Course course, boolean showEnrollments) {

        List<Integer> enrollments = null;

        if (showEnrollments) {
            enrollments = new ArrayList<>();

            for (Enrollment e : course.getEnrollments()) {
                enrollments.add(e.getId());
            }
        }
        return new CourseDTO(course.getCode(), course.getName(), course.getYear(), course.getTeacher().getTeacherId(), enrollments);
    }

    @Override
    public Course courseDtoToCourse(CourseDTO courseDTO) {
        List<Enrollment> enrollments = new ArrayList<>();

        if(courseDTO.getEnrollments()!=null) {
            for (int enrollmentId : courseDTO.getEnrollments()) {
                enrollments.add(enrollmentRepository.findById(enrollmentId).get());
            }
        }
        return new Course(courseDTO.getCode(), courseDTO.getName(), courseDTO.getYear(), teacherRepository.findById(courseDTO.getTeacherId()).get(), enrollments);
    }


}
