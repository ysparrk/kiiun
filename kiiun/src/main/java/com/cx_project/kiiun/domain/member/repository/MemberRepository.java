package com.cx_project.kiiun.domain.member.repository;

import com.cx_project.kiiun.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
    Member findById(long id);
}
