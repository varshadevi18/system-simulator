package com.project.system_design_simulator.controller;

import com.project.system_design_simulator.dto.SimulationRequest;
import com.project.system_design_simulator.dto.SimulationResponse;
import com.project.system_design_simulator.dto.SimulationResultResponse;
import com.project.system_design_simulator.dto.SimulationSummaryResponse;
import com.project.system_design_simulator.service.SimulationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class SimulationController {

    private final SimulationService simulationService;

    public SimulationController(
            SimulationService simulationService) {

        this.simulationService = simulationService;
    }

    @PostMapping(
            "/architectures/{architectureId}/simulations"
    )
    public SimulationResponse runSimulation(
            @PathVariable Long architectureId,
            @RequestBody SimulationRequest request) {

        return simulationService.runSimulation(
                architectureId,
                request
        );
    }

    @GetMapping(
            "/architectures/{architectureId}/simulations"
    )
    public List<SimulationSummaryResponse>
    getSimulations(
            @PathVariable Long architectureId) {

        return simulationService
                .getSimulationsByArchitecture(
                        architectureId);
    }

    @GetMapping("/simulations/{id}")
    public ResponseEntity<SimulationSummaryResponse>
    getSimulation(
            @PathVariable Long id) {

        try {

            return ResponseEntity.ok(
                    simulationService
                            .getSimulationById(id)
            );

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(
            "/simulations/{simulationId}/results"
    )
    public ResponseEntity<List<SimulationResultResponse>>
    getSimulationResults(
            @PathVariable Long simulationId) {

        try {

            return ResponseEntity.ok(
                    simulationService
                            .getSimulationResults(
                                    simulationId)
            );

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }
}