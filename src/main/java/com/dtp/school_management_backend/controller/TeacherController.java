package com.dtp.school_management_backend.controller;


import com.dtp.school_management_backend.dto.EnrollmentDTO;
import com.dtp.school_management_backend.dto.GradeDTO;
import com.dtp.school_management_backend.dto.TeacherDTO;
import com.dtp.school_management_backend.entity.Member;
import com.dtp.school_management_backend.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {


    private SchoolService schoolService;

    @Autowired
    public TeacherController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public TeacherDTO getTeacher(Authentication authentication) {
        String email = authentication.getName();
        Member member = schoolService.findMemberByEmail(email);
        int teacherId = member.getTeacher().getTeacherId();
        return schoolService.findTeacherById(teacherId);
    }


    @PostMapping
    public TeacherDTO createTeacherAccount(@RequestBody TeacherDTO teacherDTO) {
        return schoolService.saveTeacher(teacherDTO);

    }

    @GetMapping("/requests")
    public List<EnrollmentDTO> getRequests(Authentication authentication) {
        String email = authentication.getName();
        Member member = schoolService.findMemberByEmail(email);
        int teacherId = member.getTeacher().getTeacherId();
        return schoolService.findEnrollmentRequestsForTeacher(teacherId);
    }

    @PutMapping("/requests/accept/{requestId}")
    public EnrollmentDTO acceptRequest(@PathVariable int requestId,Authentication authentication) {

        String email = authentication.getName();
        Member member = schoolService.findMemberByEmail(email);
        int teacherId = member.getTeacher().getTeacherId();

        return schoolService.acceptEnrollmentRequest(requestId, teacherId);
    }

    @DeleteMapping("/requests/reject/{requestId}")
    public void rejectRequest(@PathVariable int requestId,Authentication authentication) {

        String email = authentication.getName();
        Member member = schoolService.findMemberByEmail(email);
        int teacherId = member.getTeacher().getTeacherId();

         schoolService.deleteEnrollmentRequest( teacherId,requestId);

    }


    @PutMapping("/grades")
    public List<EnrollmentDTO> updateGrades(@RequestBody List<GradeDTO> grades,Authentication authentication) {
        String email = authentication.getName();
        Member member = schoolService.findMemberByEmail(email);
        int teacherId = member.getTeacher().getTeacherId();

      return   schoolService.updateGrades(grades,teacherId);

    }




}
