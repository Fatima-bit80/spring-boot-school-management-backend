package com.dtp.school_management_backend.controller;

import com.dtp.school_management_backend.dto.EnrollmentDTO;
import com.dtp.school_management_backend.entity.Enrollment;
import com.dtp.school_management_backend.mapper.SchoolMapper;
import com.dtp.school_management_backend.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/enrollments")
public class EnrollmentController {

    private EnrollmentService enrollmentService;
    private SchoolMapper schoolMapper;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService, SchoolMapper schoolMapper) {
        this.enrollmentService = enrollmentService;
        this.schoolMapper = schoolMapper;
    }

    @GetMapping
    public List<EnrollmentDTO> getEnrollmentsOfUser(Authentication authentication) {
        List<EnrollmentDTO> enrollmentDTOList = new ArrayList<>();

        String email = authentication.getName();

        List<Enrollment> enrollments = enrollmentService.findEnrollments(email);

        for (Enrollment e:enrollments){
            enrollmentDTOList.add(schoolMapper.enrollmentToEnrollmentDto(e));
        }

        return enrollmentDTOList;

    }

    @GetMapping("/{courseCode}")
    public List<EnrollmentDTO> getEnrollmentsOfCourse(@PathVariable String courseCode,Authentication authentication) throws IllegalAccessException {
        String email = authentication.getName();

        List<Enrollment> enrollments = enrollmentService.findEnrollmentsOfCourse(courseCode,email);
        List<EnrollmentDTO> enrollmentDTOList = new ArrayList<>();
        for (Enrollment e:enrollments){
            enrollmentDTOList.add(schoolMapper.enrollmentToEnrollmentDto(e));
        }

        return enrollmentDTOList;
    }

    @DeleteMapping("/{courseCode}")
    public void deleteEnrollment(@PathVariable int courseCode, Authentication authentication) throws IllegalAccessException {
        String email = authentication.getName();

        enrollmentService.deleteEnrollment(courseCode,email);

    }


    @PostMapping("/{courseCode}")
    public EnrollmentDTO requestEnrollment(@PathVariable String courseCode, Authentication authentication) {

        String email = authentication.getName();

        Enrollment e = enrollmentService.saveEnrollment(courseCode, email);

        return schoolMapper.enrollmentToEnrollmentDto(e);
    }



/*
    //todo how to expose endpoint for accept
    @PutMapping("/accept/{requestId}")
    public EnrollmentDTO acceptRequest(@PathVariable int requestId,Authentication authentication) {

        String email = authentication.getName();
        Member member = schoolService.findMemberByEmail(email);
        int teacherId = member.getTeacher().getTeacherId();

        return schoolService.acceptEnrollmentRequest(requestId, teacherId);
    }*/




    //accept request or change grade
    @PutMapping("/{enrollmentId}")
    public EnrollmentDTO updateEnrollment(@PathVariable int enrollmentId,@RequestBody EnrollmentDTO enrollmentDTO,Authentication authentication) throws IllegalAccessException {

        String email = authentication.getName();

        Enrollment enrollment =  schoolMapper.enrollmentDtoToEnrollment(enrollmentDTO);

        Enrollment updateEnrollment =   enrollmentService.updateEnrollment(enrollmentId,enrollment,email);

        return schoolMapper.enrollmentToEnrollmentDto(updateEnrollment);

    }

}
