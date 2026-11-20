package com.project.system_design_simulator.repository;

import com.project.system_design_simulator.entity.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimulationRepository
        extends JpaRepository<Simulation, Long> {

    List<Simulation> findByArchitectureId(Long architectureId);
}