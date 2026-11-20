package com.project.system_design_simulator.repository;

import com.project.system_design_simulator.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodeRepository extends JpaRepository<Node, Long> {

    List<Node> findByArchitectureId(Long architectureId);
}
