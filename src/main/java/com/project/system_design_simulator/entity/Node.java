package com.project.system_design_simulator.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "architecture_nodes")
public class Node {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String label;

        private String type;

        private Double positionX;

        private Double positionY;

        @ManyToOne
        @JoinColumn(name = "architecture_id", nullable = false)
        private Architecture architecture;

        public Node() {
        }

        public Node(String label, String type, Double positionX, Double positionY) {
            this.label = label;
            this.type = type;
            this.positionX = positionX;
            this.positionY = positionY;
        }

        public Long getId() {
            return id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Double getPositionX() {
            return positionX;
        }

        public void setPositionX(Double positionX) {
            this.positionX = positionX;
        }

        public Double getPositionY() {
            return positionY;
        }

        public void setPositionY(Double positionY) {
            this.positionY = positionY;
        }

        public Architecture getArchitecture() {
            return architecture;
        }

        public void setArchitecture(Architecture architecture) {
            this.architecture = architecture;
        }

}
