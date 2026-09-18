package com.dtp.school_management_backend.controller;


import com.dtp.school_management_backend.dto.*;
import com.dtp.school_management_backend.entity.Member;
import com.dtp.school_management_backend.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private SchoolService schoolService;

    @Autowired
    public StudentController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public StudentDTO getStudent(Authentication authentication) {
        String email = authentication.getName();
        Member member = schoolService.findMemberByEmail(email);
        int studentId = member.getStudent().getStudentId();
        return schoolService.findStudentById(studentId);
    }


    @PostMapping
    public StudentDTO createStudentAccount(@RequestBody StudentDTO studentDTO) {

      return schoolService.saveStudent(studentDTO);

    }

    @GetMapping("/enrollments")
    public List<EnrollmentDTO> getEnrollments(Authentication authentication) {
        String email = authentication.getName();
        Member member = schoolService.findMemberByEmail(email);
        int studentId = member.getStudent().getStudentId();
        return schoolService.findEnrollmentsOfStudent(studentId);

    }

    @DeleteMapping("/enrollments/{courseId}")
    public void getEnrollments(@PathVariable int courseId, Authentication authentication) {
        String email = authentication.getName();
        Member member = schoolService.findMemberByEmail(email);
        int studentId = member.getStudent().getStudentId();

        schoolService.deleteEnrollmentOfStudent(studentId, courseId);

    }


    @PostMapping("enrollments/{courseCode}")
    public EnrollmentDTO requestEnrollment(@PathVariable String courseCode, Authentication authentication) {

        String email = authentication.getName();
        Member member = schoolService.findMemberByEmail(email);
        int studentId = member.getStudent().getStudentId();
        return schoolService.saveEnrollment(courseCode, studentId);
    }
}
