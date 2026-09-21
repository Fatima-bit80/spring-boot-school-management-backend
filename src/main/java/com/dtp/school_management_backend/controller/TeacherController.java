package com.dtp.school_management_backend.controller;


import com.dtp.school_management_backend.dto.TeacherDTO;
import com.dtp.school_management_backend.entity.Teacher;
import com.dtp.school_management_backend.mapper.SchoolMapper;
import com.dtp.school_management_backend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {


    private UsersService usersService;
    private SchoolMapper schoolMapper;

    @Autowired
    public TeacherController(UsersService usersService, SchoolMapper schoolMapper) {
        this.usersService = usersService;
        this.schoolMapper = schoolMapper;
    }

    @GetMapping("/teacherId")
    public TeacherDTO getTeacher(@RequestParam int teacherId,Authentication authentication) {
        String email = authentication.getName();

        Teacher teacher = usersService.findTeacherById(teacherId,email);
        return  schoolMapper.teacherToTeacherDto(teacher);
    }


    @GetMapping
    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = usersService.findAllTeachers();

        List<TeacherDTO> teacherDTOS = new ArrayList<>();
        for (Teacher teacher : teachers) {
            teacherDTOS.add(schoolMapper.teacherToTeacherDto(teacher));
        }
        return teacherDTOS;
    }


    @PostMapping
    public TeacherDTO createTeacherAccount(@RequestBody TeacherDTO teacherDTO) {

        Teacher savedTeacher = usersService.saveTeacher(teacherDTO);

        return schoolMapper.teacherToTeacherDto(savedTeacher);
    }




    @DeleteMapping("/teacherId}")
    public void deleteTeacherAccount(@RequestParam int teacherId) {
        usersService.deleteTeacherById(teacherId);
    }




}
