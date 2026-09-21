package com.dtp.school_management_backend.mapper;

import com.dtp.school_management_backend.dto.*;
import com.dtp.school_management_backend.entity.*;
import org.springframework.stereotype.Component;

@Component
public interface SchoolMapper {

    StudentDTO studentToStudentDto(Student student);

    TeacherDTO teacherToTeacherDto(Teacher teacher);

    EnrollmentDTO enrollmentToEnrollmentDto(Enrollment enrollment);
    Enrollment enrollmentDtoToEnrollment(EnrollmentDTO enrollmentDTO);

    CourseDTO courseToCourseDto(Course course,boolean isAdmin);
    Course courseDtoToCourse(CourseDTO courseDTO);

    MemberDTO memberToMemberDTO(Member member);
    Member memberDtoToMember(MemberDTO memberDTO);

}
