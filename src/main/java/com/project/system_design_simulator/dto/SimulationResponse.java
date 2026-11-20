package com.project.system_design_simulator.dto;

import java.util.List;

public class SimulationResponse {

    private Long simulationId;

    private Long architectureId;

    private Double requestsPerSecond;

    private Integer durationSeconds;

    private Double throughput;

    private Double averageLatencyMs;

    private Double errorRate;

    private List<SimulationNodeResult> nodeResults;

    public SimulationResponse() {
    }

    public SimulationResponse(
            Long simulationId,
            Long architectureId,
            Double requestsPerSecond,
            Integer durationSeconds,
            Double throughput,
            Double averageLatencyMs,
            Double errorRate,
            List<SimulationNodeResult> nodeResults) {

        this.simulationId = simulationId;
        this.architectureId = architectureId;
        this.requestsPerSecond = requestsPerSecond;
        this.durationSeconds = durationSeconds;
        this.throughput = throughput;
        this.averageLatencyMs = averageLatencyMs;
        this.errorRate = errorRate;
        this.nodeResults = nodeResults;
    }

    public Long getSimulationId() {
        return simulationId;
    }

    public Long getArchitectureId() {
        return architectureId;
    }

    public Double getRequestsPerSecond() {
        return requestsPerSecond;
    }

    public Integer getDurationSeconds() {
        return durationSeconds;
    }

    public Double getThroughput() {
        return throughput;
    }

    public Double getAverageLatencyMs() {
        return averageLatencyMs;
    }

    public Double getErrorRate() {
        return errorRate;
    }

    public List<SimulationNodeResult> getNodeResults() {
        return nodeResults;
    }
}