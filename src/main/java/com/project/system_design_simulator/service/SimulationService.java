package com.project.system_design_simulator.service;
import com.project.system_design_simulator.dto.SimulationResultResponse;
import com.project.system_design_simulator.dto.SimulationSummaryResponse;
import com.project.system_design_simulator.dto.SimulationNodeResult;
import com.project.system_design_simulator.dto.SimulationRequest;
import com.project.system_design_simulator.dto.SimulationResponse;
import com.project.system_design_simulator.entity.Architecture;
import com.project.system_design_simulator.entity.Node;
import com.project.system_design_simulator.entity.Simulation;
import com.project.system_design_simulator.entity.SimulationResult;
import com.project.system_design_simulator.repository.ArchitectureRepository;
import com.project.system_design_simulator.repository.NodeRepository;
import com.project.system_design_simulator.repository.SimulationRepository;
import com.project.system_design_simulator.repository.SimulationResultRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SimulationService {

    private final ArchitectureRepository architectureRepository;
    private final NodeRepository nodeRepository;
    private final SimulationRepository simulationRepository;
    private final SimulationResultRepository simulationResultRepository;

    public SimulationService(
            ArchitectureRepository architectureRepository,
            NodeRepository nodeRepository,
            SimulationRepository simulationRepository,
            SimulationResultRepository simulationResultRepository) {

        this.architectureRepository = architectureRepository;
        this.nodeRepository = nodeRepository;
        this.simulationRepository = simulationRepository;
        this.simulationResultRepository = simulationResultRepository;
    }

    @Transactional
    public SimulationResponse runSimulation(
            Long architectureId,
            SimulationRequest request) {

        // 1. Find architecture
        Architecture architecture =
                architectureRepository.findById(architectureId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Architecture not found"));

        // 2. Find nodes
        List<Node> nodes =
                nodeRepository.findByArchitectureId(architectureId);

        if (nodes.isEmpty()) {
            throw new RuntimeException(
                    "Architecture has no nodes");
        }

        // 3. Read input
        double requestsPerSecond =
                request.getRequestsPerSecond();

        int durationSeconds =
                request.getDurationSeconds();

        double failureRate =
                request.getFailureRate();

        // 4. Create Simulation
        Simulation simulation = new Simulation();

        simulation.setArchitecture(architecture);
        simulation.setRequestsPerSecond(requestsPerSecond);
        simulation.setDurationSeconds(durationSeconds);
        simulation.setFailureRate(failureRate);
        simulation.setCreatedAt(LocalDateTime.now());

        /*
         * IMPORTANT:
         * Save the Simulation BEFORE creating
         * SimulationResult records.
         *
         * This generates the Simulation ID.
         */
        simulation =
                simulationRepository.save(simulation);

        // 5. Calculate simulation
        double bottleneckCapacity =
                Double.MAX_VALUE;

        double totalLatency = 0;

        List<SimulationNodeResult> nodeResults =
                new ArrayList<>();

        List<SimulationResult> databaseResults =
                new ArrayList<>();

        for (Node node : nodes) {

            double capacity =
                    getCapacity(node.getType());

            double load =
                    (requestsPerSecond / capacity) * 100;

            double nodeErrorRate =
                    failureRate;

            if (load > 100) {

                nodeErrorRate +=
                        Math.min(load - 100, 100);
            }

            double latency =
                    getBaseLatency(node.getType());

            if (load > 80) {

                latency *=
                        1 + ((load - 80) / 100);
            }

            double handledRequests =
                    Math.min(
                            requestsPerSecond,
                            capacity);

            bottleneckCapacity =
                    Math.min(
                            bottleneckCapacity,
                            capacity);

            totalLatency += latency;

            double finalLoad =
                    Math.min(load, 200);

            double finalErrorRate =
                    Math.min(nodeErrorRate, 100);

            // Create database result
            SimulationResult result =
                    new SimulationResult();

            result.setSimulation(simulation);
            result.setNode(node);
            result.setRequestsHandled(
                    handledRequests);
            result.setLoadPercentage(
                    finalLoad);
            result.setLatencyMs(latency);
            result.setErrorRate(
                    finalErrorRate);

            databaseResults.add(result);

            // Create API result
            nodeResults.add(
                    new SimulationNodeResult(
                            node.getId(),
                            node.getLabel(),
                            node.getType(),
                            handledRequests,
                            finalLoad,
                            latency,
                            finalErrorRate
                    )
            );
        }

        // 6. Calculate overall metrics

        double throughput =
                Math.min(
                        requestsPerSecond,
                        bottleneckCapacity);

        double averageLatency =
                totalLatency / nodes.size();

        double overallErrorRate =
                failureRate;

        if (requestsPerSecond >
                bottleneckCapacity) {

            double overload =
                    ((requestsPerSecond -
                            bottleneckCapacity)
                            / requestsPerSecond)
                            * 100;

            overallErrorRate += overload;
        }

        overallErrorRate =
                Math.min(
                        overallErrorRate,
                        100);

        // 7. Update Simulation
        simulation.setThroughput(
                throughput);

        simulation.setAverageLatencyMs(
                averageLatency);

        simulation.setErrorRate(
                overallErrorRate);

        simulation =
                simulationRepository.save(simulation);

        // 8. NOW save SimulationResults
        for (SimulationResult result :
                databaseResults) {

            simulationResultRepository.save(result);
        }

        // 9. Return response
        return new SimulationResponse(
                simulation.getId(),
                architectureId,
                requestsPerSecond,
                durationSeconds,
                throughput,
                averageLatency,
                overallErrorRate,
                nodeResults
        );
    }
    public List<SimulationSummaryResponse> getSimulationsByArchitecture(
            Long architectureId) {

        return simulationRepository
                .findByArchitectureId(architectureId)
                .stream()
                .map(simulation -> new SimulationSummaryResponse(
                        simulation.getId(),
                        simulation.getArchitecture().getId(),
                        simulation.getRequestsPerSecond(),
                        simulation.getDurationSeconds(),
                        simulation.getThroughput(),
                        simulation.getAverageLatencyMs(),
                        simulation.getErrorRate(),
                        simulation.getCreatedAt()
                ))
                .toList();
    }
    public SimulationSummaryResponse getSimulationById(Long id) {

        Simulation simulation =
                simulationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Simulation not found"));

        return new SimulationSummaryResponse(
                simulation.getId(),
                simulation.getArchitecture().getId(),
                simulation.getRequestsPerSecond(),
                simulation.getDurationSeconds(),
                simulation.getThroughput(),
                simulation.getAverageLatencyMs(),
                simulation.getErrorRate(),
                simulation.getCreatedAt()
        );
    }
    public List<SimulationResultResponse> getSimulationResults(
            Long simulationId) {

        if (!simulationRepository.existsById(simulationId)) {
            throw new RuntimeException(
                    "Simulation not found");
        }

        return simulationResultRepository
                .findBySimulationId(simulationId)
                .stream()
                .map(result -> new SimulationResultResponse(
                        result.getId(),
                        result.getSimulation().getId(),
                        result.getNode().getId(),
                        result.getNode().getLabel(),
                        result.getNode().getType(),
                        result.getRequestsHandled(),
                        result.getLoadPercentage(),
                        result.getLatencyMs(),
                        result.getErrorRate()
                ))
                .toList();
    }

    private double getCapacity(String type) {

        if (type == null) {
            return 1000;
        }

        return switch (type) {

            case "CLIENT" -> 10000;

            case "LOAD_BALANCER" -> 5000;

            case "API_GATEWAY" -> 3000;

            case "SERVICE" -> 1000;

            case "CACHE" -> 5000;

            case "DATABASE" -> 500;

            case "QUEUE" -> 2000;

            default -> 1000;
        };
    }

    private double getBaseLatency(String type) {

        if (type == null) {
            return 50;
        }

        return switch (type) {

            case "CLIENT" -> 10;

            case "LOAD_BALANCER" -> 5;

            case "API_GATEWAY" -> 15;

            case "SERVICE" -> 40;

            case "CACHE" -> 5;

            case "DATABASE" -> 80;

            case "QUEUE" -> 20;

            default -> 50;
        };
    }
}