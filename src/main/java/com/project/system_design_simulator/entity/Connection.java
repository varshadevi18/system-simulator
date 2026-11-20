package com.project.system_design_simulator.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "architecture_connections")
public class Connection {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String connectionType;

        @ManyToOne
        @JoinColumn(name = "architecture_id", nullable = false)
        private Architecture architecture;

        @ManyToOne
        @JoinColumn(name = "source_node_id", nullable = false)
        private Node sourceNode;

        @ManyToOne
        @JoinColumn(name = "target_node_id", nullable = false)
        private Node targetNode;

        public Connection() {
        }

        public Long getId() {
            return id;
        }

        public String getConnectionType() {
            return connectionType;
        }

        public void setConnectionType(String connectionType) {
            this.connectionType = connectionType;
        }

        public Architecture getArchitecture() {
            return architecture;
        }

        public void setArchitecture(Architecture architecture) {
            this.architecture = architecture;
        }

        public Node getSourceNode() {
            return sourceNode;
        }

        public void setSourceNode(Node sourceNode) {
            this.sourceNode = sourceNode;
        }

        public Node getTargetNode() {
            return targetNode;
        }

        public void setTargetNode(Node targetNode) {
            this.targetNode = targetNode;
        }

}
