package com.dtp.school_management_backend.service;

import com.dtp.school_management_backend.dto.StudentDTO;
import com.dtp.school_management_backend.dto.TeacherDTO;
import com.dtp.school_management_backend.entity.Member;
import com.dtp.school_management_backend.entity.Student;
import com.dtp.school_management_backend.entity.Teacher;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UsersService {


    Student saveStudent(StudentDTO student);
    Student findStudentByIdForRequester(int id, String email) throws IllegalAccessException;
    List<Student> findAllStudents();



    Teacher saveTeacher(TeacherDTO teacher);
    List<Teacher> findAllTeachers();
    Teacher findTeacherById(int teacherId, String email) throws IllegalAccessException;


    void deleteStudentById(int studentId);

    void deleteTeacherById(int teacherId);
}
