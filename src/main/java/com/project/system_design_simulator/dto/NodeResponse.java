package com.project.system_design_simulator.dto;

public class NodeResponse {

        private Long id;
        private String label;
        private String type;
        private Double positionX;
        private Double positionY;
        private Long architectureId;

        public NodeResponse() {
        }

        public NodeResponse(
                Long id,
                String label,
                String type,
                Double positionX,
                Double positionY,
                Long architectureId) {

            this.id = id;
            this.label = label;
            this.type = type;
            this.positionX = positionX;
            this.positionY = positionY;
            this.architectureId = architectureId;
        }

        public Long getId() {
            return id;
        }

        public String getLabel() {
            return label;
        }

        public String getType() {
            return type;
        }

        public Double getPositionX() {
            return positionX;
        }

        public Double getPositionY() {
            return positionY;
        }

        public Long getArchitectureId() {
            return architectureId;
        }

}
