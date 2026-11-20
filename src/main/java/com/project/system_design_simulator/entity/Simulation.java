package com.project.system_design_simulator.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "simulations")
public class Simulation {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "architecture_id", nullable = false)
        private Architecture architecture;

        private Double requestsPerSecond;

        private Integer durationSeconds;

        private Double failureRate;

        private Double throughput;

        private Double averageLatencyMs;

        private Double errorRate;

        private LocalDateTime createdAt;

        public Simulation() {
        }

        public Long getId() {
            return id;
        }

        public Architecture getArchitecture() {
            return architecture;
        }

        public void setArchitecture(Architecture architecture) {
            this.architecture = architecture;
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

        public Double getThroughput() {
            return throughput;
        }

        public void setThroughput(Double throughput) {
            this.throughput = throughput;
        }

        public Double getAverageLatencyMs() {
            return averageLatencyMs;
        }

        public void setAverageLatencyMs(Double averageLatencyMs) {
            this.averageLatencyMs = averageLatencyMs;
        }

        public Double getErrorRate() {
            return errorRate;
        }

        public void setErrorRate(Double errorRate) {
            this.errorRate = errorRate;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

}
