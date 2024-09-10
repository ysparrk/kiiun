package com.cx_project.kiiun.domain.lizard.repository;

import com.cx_project.kiiun.domain.lizard.entity.Lizard;
import com.cx_project.kiiun.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LizardRepository extends JpaRepository<Lizard, Long> {
    List<Lizard> findAllByMember(Member member);
}

