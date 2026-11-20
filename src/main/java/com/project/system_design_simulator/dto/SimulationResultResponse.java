package com.project.system_design_simulator.dto;

public class SimulationResultResponse {

    private Long resultId;
    private Long simulationId;
    private Long nodeId;
    private String nodeLabel;
    private String nodeType;
    private Double requestsHandled;
    private Double loadPercentage;
    private Double latencyMs;
    private Double errorRate;

    public SimulationResultResponse(
            Long resultId,
            Long simulationId,
            Long nodeId,
            String nodeLabel,
            String nodeType,
            Double requestsHandled,
            Double loadPercentage,
            Double latencyMs,
            Double errorRate) {

        this.resultId = resultId;
        this.simulationId = simulationId;
        this.nodeId = nodeId;
        this.nodeLabel = nodeLabel;
        this.nodeType = nodeType;
        this.requestsHandled = requestsHandled;
        this.loadPercentage = loadPercentage;
        this.latencyMs = latencyMs;
        this.errorRate = errorRate;
    }

    public Long getResultId() {
        return resultId;
    }

    public Long getSimulationId() {
        return simulationId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public String getNodeLabel() {
        return nodeLabel;
    }

    public String getNodeType() {
        return nodeType;
    }

    public Double getRequestsHandled() {
        return requestsHandled;
    }

    public Double getLoadPercentage() {
        return loadPercentage;
    }

    public Double getLatencyMs() {
        return latencyMs;
    }

    public Double getErrorRate() {
        return errorRate;
    }
}