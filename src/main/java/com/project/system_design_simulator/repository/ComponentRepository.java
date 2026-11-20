package com.project.system_design_simulator.repository;

import com.project.system_design_simulator.entity.Component;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentRepository
        extends JpaRepository<Component, Long> {
}