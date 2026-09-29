package com.dtp.school_management_backend.service.impl;


import com.dtp.school_management_backend.dao.MemberRepository;
import com.dtp.school_management_backend.dao.StudentRepository;
import com.dtp.school_management_backend.dao.TeacherRepository;
import com.dtp.school_management_backend.dto.StudentDTO;
import com.dtp.school_management_backend.dto.TeacherDTO;
import com.dtp.school_management_backend.entity.Member;
import com.dtp.school_management_backend.entity.Student;
import com.dtp.school_management_backend.entity.Teacher;
import com.dtp.school_management_backend.exception.SchoolException;
import com.dtp.school_management_backend.service.UsersService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Teacher findTeacherById(int teacherId, String email) {
        Member m =  memberRepository.findById(email).get();
        if(m.getTeacher()!=null &&  m.getTeacher().getTeacherId() != teacherId){
            throw new SchoolException("teacher can't access another teacher's info");
        }


        Optional<Teacher> teacher = teacherRepository.findById(teacherId);

        if (teacher.isPresent()) {
            return teacher.get();
        }else {
            throw new SchoolException("no teacher with id "+teacherId+" found");
        }
    }

    @Override
    public void deleteStudentById(int studentId) {
        Optional<Student> student1 = studentRepository.findById(studentId);
        if(!student1.isPresent())
            throw new SchoolException("student with id "+studentId+" not found");
        Student student = student1.get();
        studentRepository.delete(student);
    }

    @Override
    public void deleteTeacherById(int teacherId) {
        Optional<Teacher> teacher1 = teacherRepository.findById(teacherId);
        if(!teacher1.isPresent())
            throw new SchoolException("teacher with id "+teacherId+" not found");
        Teacher teacher = teacher1.get();
        teacherRepository.delete(teacher);
    }

    @Override
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Student findStudentByIdForRequester(int id, String email){

        Member member =  memberRepository.findById(email).get();

        if(member.getStudent() != null && (member.getStudent().getStudentId() != id)){
            throw new SchoolException("student can't access another student's info");
        }

        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        }
        else {
            throw new SchoolException("no student with id "+id+" found");
        }
    }

}
