package com.cx_project.kiiun.domain.lizard.repository;

import com.cx_project.kiiun.domain.lizard.entity.Lizard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LizardRepository extends JpaRepository<Lizard, Long> {
}
