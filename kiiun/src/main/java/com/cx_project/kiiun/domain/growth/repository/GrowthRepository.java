package com.cx_project.kiiun.domain.growth.repository;

import com.cx_project.kiiun.domain.growth.entity.Growth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrowthRepository extends JpaRepository<Growth, Long> {
}
