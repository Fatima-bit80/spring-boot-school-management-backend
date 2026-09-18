package com.dtp.school_management_backend.service;


import com.dtp.school_management_backend.dao.*;
import com.dtp.school_management_backend.dto.*;
import com.dtp.school_management_backend.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Member member = new Member();
        member.setEmail(studentDTO.getEmail());
        member.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
        member.setActive(1);
        member.setRole("ROLE_STUDENT");

        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setYear(studentDTO.getYear());
        student.setMember(member);

        studentRepository.save(student);

        StudentDTO s = new StudentDTO();
        s.setYear(student.getYear());
        s.setFirstName(student.getFirstName());
        s.setLastName(student.getLastName());
        s.setEmail(student.getMember().getEmail());
        s.setStudentId(s.getStudentId());

        return s;
    }

    @Override
    @Transactional
    public TeacherDTO saveTeacher(TeacherDTO teacher) {
        Member member = new Member();
        member.setEmail(teacher.getEmail());
        member.setPassword(passwordEncoder.encode(teacher.getPassword()));
        member.setActive(1);
        member.setRole("ROLE_TEACHER");

        Teacher teacher1 = new Teacher();
        teacher1.setFirstName(teacher.getFirstName());
        teacher1.setLastName(teacher.getLastName());
        teacher1.setMember(member);
        teacherRepository.save(teacher1);

        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setEmail(teacher.getEmail());
        teacherDTO.setFirstName(teacher.getFirstName());
        teacherDTO.setLastName(teacher.getLastName());
        teacherDTO.setTeacherId(teacher.getTeacherId());

        return teacherDTO;
    }

    @Override
    public Member findMemberByEmail(String email) {
       return memberRepository.findById(email).get();
    }

    @Override
    public List<EnrollmentDTO> findEnrollmentsOfStudent(int studentId) {
        List<EnrollmentDTO> enrollments = new ArrayList<>();


        List<Enrollment> enrollmentList = enrollmentRepository.findEnrollmentsForStudent(studentId);

        for (Enrollment enrollment : enrollmentList) {
            EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
            enrollmentDTO.setId(enrollment.getId());
            enrollmentDTO.setCourseCode(enrollment.getCourse().getCode());
            enrollmentDTO.setCourseName(enrollment.getCourse().getName());
            enrollmentDTO.setApproved(enrollment.getApproved());
            enrollmentDTO.setCourseYear(enrollment.getCourse().getYear());
enrollmentDTO.setTeacherName(enrollment.getCourse().getTeacher().getFirstName() +" "+enrollment.getCourse().getTeacher().getLastName());

enrollmentDTO.setStudentName(enrollment.getStudent().getFirstName()+" "+enrollment.getStudent().getLastName());
            enrollments.add(enrollmentDTO);

        }

        return enrollments;
    }

    @Override
    public List<Course> findAvailableCourses(int studentId) {
        return courseRepository.findAvailableCoursesForStudent(studentId);
    }

    @Override
    @Transactional
    public void deleteEnrollmentOfStudent(int studentId, int enrollmentId) {
        Enrollment e = enrollmentRepository.findById(enrollmentId).get();
        if(e.getStudent().getStudentId() ==  studentId){
            enrollmentRepository.deleteById(enrollmentId);
        }else{
            //todo throw an exception that this enrollment doesn't belong to the student
        }
    }

    @Override
    @Transactional
    public void deleteEnrollmentRequest(int teacherId, int requestId) {
        Enrollment e = enrollmentRepository.findById(requestId).get();

        if(e.getCourse().getTeacher().getTeacherId() == teacherId){
            enrollmentRepository.deleteById(requestId);
        }else {
            //todo throw an exception that this request isn't for the teacher
        }

    }

    @Override
    @Transactional
    public EnrollmentDTO saveEnrollment(String courseCode, int studentId) {
        Student student = studentRepository.findById(studentId).get();
        Course course = courseRepository.findById(courseCode).get();
        Enrollment e = new Enrollment(course,student);
        enrollmentRepository.save(e);

        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
        enrollmentDTO.setId(e.getId());
        enrollmentDTO.setCourseCode(courseCode);
        enrollmentDTO.setCourseName(course.getName());
        enrollmentDTO.setApproved(e.getApproved());
        enrollmentDTO.setCourseYear(e.getCourse().getYear());
        enrollmentDTO.setTeacherName(e.getCourse().getTeacher().getFirstName()+" "+e.getCourse().getTeacher().getLastName());
return enrollmentDTO;
    }

    @Override
    public List<EnrollmentDTO> findEnrollmentRequestsForTeacher(int teacherId) {

        List<Enrollment> enrollments = enrollmentRepository.findEnrollmentRequestsForTeacher(teacherId);

        List<EnrollmentDTO> enrollmentDTOs = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
            enrollmentDTO.setId(enrollment.getId());
            enrollmentDTO.setCourseCode(enrollment.getCourse().getCode());
            enrollmentDTO.setCourseName(enrollment.getCourse().getName());
            enrollmentDTO.setApproved(enrollment.getApproved());
            enrollmentDTO.setCourseYear(enrollment.getCourse().getYear());
            enrollmentDTO.setTeacherName(enrollment.getCourse().getTeacher().getFirstName()+" "+enrollment.getCourse().getTeacher().getLastName());
            enrollmentDTO.setStudentName(enrollment.getStudent().getFirstName()+" "+enrollment.getStudent().getLastName());
            enrollmentDTOs.add(enrollmentDTO);
        }

        return enrollmentDTOs;
    }

    @Override
    @Transactional
    public EnrollmentDTO acceptEnrollmentRequest(int requestId,int teacherId) {
        Enrollment e = enrollmentRepository.findById(requestId).get();
        if(e.getCourse().getTeacher().getTeacherId() ==  teacherId){
            e.setApproved(1);
            enrollmentRepository.save(e);
        }else{
            //todo throw an exception that this enrollment doesn't belong to the teacher
        }
        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
        enrollmentDTO.setId(e.getId());
        enrollmentDTO.setCourseCode(e.getCourse().getCode());
        enrollmentDTO.setCourseName(e.getCourse().getName());
        enrollmentDTO.setApproved(e.getApproved());
        enrollmentDTO.setCourseYear(e.getCourse().getYear());
        enrollmentDTO.setStudentName(e.getStudent().getFirstName()+" "+e.getStudent().getLastName());
        enrollmentDTO.setTeacherName(e.getCourse().getTeacher().getFirstName()+" "+e.getCourse().getTeacher().getLastName());
        return enrollmentDTO;
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
   public List<EnrollmentDTO> updateGrades(List<GradeDTO> grades,int teacherId) {

        List<EnrollmentDTO> enrollmentDTOs = new ArrayList<>();

        for(GradeDTO row : grades) {
            Enrollment e = enrollmentRepository.findById(row.getEnrollmentId()).get();
            if(e.getCourse().getTeacher().getTeacherId() == teacherId && e.getApproved()==1) {
                e.setGrade(row.getGrade());
                enrollmentRepository.save(e);

                EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
                enrollmentDTO.setId(e.getId());
                enrollmentDTO.setCourseCode(e.getCourse().getCode());
                enrollmentDTO.setCourseName(e.getCourse().getName());
                enrollmentDTO.setApproved(e.getApproved());
                enrollmentDTO.setCourseYear(e.getCourse().getYear());
                enrollmentDTO.setStudentName(e.getStudent().getFirstName()+" "+e.getStudent().getLastName());
                enrollmentDTO.setTeacherName(e.getCourse().getTeacher().getFirstName()+" "+e.getCourse().getTeacher().getLastName());

                enrollmentDTO.setGrade(e.getGrade());
                enrollmentDTOs.add(enrollmentDTO);

            }

        }

        return enrollmentDTOs;
    }

    @Override
    public TeacherDTO findTeacherById(int teacherId) {
        Teacher t = teacherRepository.findById(teacherId).get();
        TeacherDTO teacher = new TeacherDTO();
        teacher.setEmail(t.getMember().getEmail());
        teacher.setFirstName(t.getFirstName());
        teacher.setLastName(t.getLastName());
        return teacher;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public StudentDTO findStudentById(int id) {
        Student s = studentRepository.findById(id).get();
        StudentDTO student = new StudentDTO();
        student.setEmail(s.getMember().getEmail());
        student.setFirstName(s.getFirstName());
        student.setLastName(s.getLastName());
        student.setYear(s.getYear());
        return student;
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

    // todo add separate methods to see if enrollment belongs to teacher/ student


}
