package com.project.system_design_simulator.repository;

import com.project.system_design_simulator.entity.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConnectionRepository
        extends JpaRepository<Connection, Long> {

    List<Connection> findByArchitectureId(Long architectureId);

    void deleteByArchitectureId(Long architectureId);
}