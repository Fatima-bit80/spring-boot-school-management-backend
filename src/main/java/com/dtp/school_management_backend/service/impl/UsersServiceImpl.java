package com.dtp.school_management_backend.service.impl;


import com.dtp.school_management_backend.dao.MemberRepository;
import com.dtp.school_management_backend.dao.StudentRepository;
import com.dtp.school_management_backend.dao.TeacherRepository;
import com.dtp.school_management_backend.dto.StudentDTO;
import com.dtp.school_management_backend.dto.TeacherDTO;
import com.dtp.school_management_backend.entity.Member;
import com.dtp.school_management_backend.entity.Student;
import com.dtp.school_management_backend.entity.Teacher;
import com.dtp.school_management_backend.service.UsersService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {


    private TeacherRepository teacherRepository;
    private StudentRepository studentRepository;
    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(TeacherRepository teacherRepository, StudentRepository studentRepository, MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }


    @Override
    @Transactional
    public Student saveStudent(StudentDTO studentDTO) {
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

      return   studentRepository.save(student);

    }

    @Override
    @Transactional
    public Teacher saveTeacher(TeacherDTO teacherDTO) {
        Member member = new Member();
        member.setEmail(teacherDTO.getEmail());
        member.setPassword(passwordEncoder.encode(teacherDTO.getPassword()));
        member.setActive(1);
        member.setRole("ROLE_TEACHER");

        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setMember(member);

      return   teacherRepository.save(teacher);

    }



    @Override
    public Teacher findTeacherById(int teacherId, String email) throws IllegalAccessException {
        Member m =  memberRepository.findById(email).get();
        if(m.getTeacher().getTeacherId() != teacherId){
            throw new IllegalAccessException("teacher can't access another teacher's info");
        }
        return teacherRepository.findById(teacherId).get();
    }

    @Override
    public void deleteStudentById(int studentId) {
        Student student = studentRepository.findById(studentId).get();
        studentRepository.delete(student);
    }

    @Override
    public void deleteTeacherById(int teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).get();
        teacherRepository.delete(teacher);
    }

    @Override
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Student findStudentByIdForRequester(int id, String email) throws IllegalAccessException {

        Member member =  memberRepository.findById(email).get();

        if(member.getStudent() != null && (member.getStudent().getStudentId() != id)){
            throw new IllegalAccessException("student can't access another teacher's info");
        }

        Student s = studentRepository.findById(id).get();
        return s;
    }

}
