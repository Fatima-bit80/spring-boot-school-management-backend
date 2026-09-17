package com.dtp.school_management_backend.dao;

import com.dtp.school_management_backend.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
}
