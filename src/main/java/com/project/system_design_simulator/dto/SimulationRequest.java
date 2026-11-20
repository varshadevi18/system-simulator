package com.project.system_design_simulator.dto;

public class SimulationRequest {

    private Double requestsPerSecond;

    private Integer durationSeconds;

    private Double failureRate;

    public SimulationRequest() {
    }

    public Double getRequestsPerSecond() {
        return requestsPerSecond;
    }

    public void setRequestsPerSecond(Double requestsPerSecond) {
        this.requestsPerSecond = requestsPerSecond;
    }

    public Integer getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public Double getFailureRate() {
        return failureRate;
    }

    public void setFailureRate(Double failureRate) {
        this.failureRate = failureRate;
    }
}