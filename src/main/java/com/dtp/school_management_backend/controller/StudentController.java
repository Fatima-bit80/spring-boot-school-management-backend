package com.dtp.school_management_backend.controller;


import com.dtp.school_management_backend.dto.*;
import com.dtp.school_management_backend.entity.Student;
import com.dtp.school_management_backend.mapper.SchoolMapper;
import com.dtp.school_management_backend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private UsersService usersService;
    private SchoolMapper schoolMapper;

    @Autowired
    public StudentController(UsersService usersService, SchoolMapper schoolMapper) {
        this.usersService = usersService;
        this.schoolMapper = schoolMapper;
    }

    @GetMapping("/{studentId}")
    public StudentDTO getStudentById(@PathVariable int studentId,Authentication authentication) throws IllegalAccessException {

        String email = authentication.getName();

        Student student = usersService.findStudentByIdForRequester(studentId,email);

        return schoolMapper.studentToStudentDto(student);

    }


    @GetMapping
    public List<StudentDTO> getAllStudents() {

        List<StudentDTO> allStudentDTOs = new ArrayList<>();

        List<Student> allStudents = usersService.findAllStudents();
        for (Student s:allStudents){
            allStudentDTOs.add(schoolMapper.studentToStudentDto(s));
        }
        return allStudentDTOs;
    }


    @PostMapping
    public StudentDTO createStudentAccount(@RequestBody StudentDTO studentDTO) {

      Student savedStudent = usersService.saveStudent(studentDTO);

      return schoolMapper.studentToStudentDto(savedStudent);
    }


    @DeleteMapping("/{studentId}")
    public void deleteStudentAccount(@PathVariable int studentId) {
        usersService.deleteStudentById(studentId);
    }


}
