package com.project.system_design_simulator.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "simulation_results")
public class SimulationResult {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "simulation_id", nullable = false)
        private Simulation simulation;

        @ManyToOne
        @JoinColumn(name = "node_id", nullable = false)
        private Node node;

        private Double requestsHandled;

        private Double loadPercentage;

        private Double latencyMs;

        private Double errorRate;

        public SimulationResult() {
        }

        public Long getId() {
            return id;
        }

        public Simulation getSimulation() {
            return simulation;
        }

        public void setSimulation(Simulation simulation) {
            this.simulation = simulation;
        }

        public Node getNode() {
            return node;
        }

        public void setNode(Node node) {
            this.node = node;
        }

        public Double getRequestsHandled() {
            return requestsHandled;
        }

        public void setRequestsHandled(Double requestsHandled) {
            this.requestsHandled = requestsHandled;
        }

        public Double getLoadPercentage() {
            return loadPercentage;
        }

        public void setLoadPercentage(Double loadPercentage) {
            this.loadPercentage = loadPercentage;
        }

        public Double getLatencyMs() {
            return latencyMs;
        }

        public void setLatencyMs(Double latencyMs) {
            this.latencyMs = latencyMs;
        }

        public Double getErrorRate() {
            return errorRate;
        }

        public void setErrorRate(Double errorRate) {
            this.errorRate = errorRate;
        }

}
