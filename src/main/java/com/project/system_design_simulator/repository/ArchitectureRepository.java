package com.project.system_design_simulator.repository;

import com.project.system_design_simulator.entity.Architecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchitectureRepository extends JpaRepository<Architecture, Long> {
}