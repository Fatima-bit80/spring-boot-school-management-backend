package com.dtp.school_management_backend.dao;

import com.dtp.school_management_backend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
