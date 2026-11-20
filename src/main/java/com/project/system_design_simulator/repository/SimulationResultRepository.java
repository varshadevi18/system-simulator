package com.project.system_design_simulator.repository;

import com.project.system_design_simulator.entity.SimulationResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimulationResultRepository
        extends JpaRepository<SimulationResult, Long> {

    List<SimulationResult> findBySimulationId(Long simulationId);
}
