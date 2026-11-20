package com.project.system_design_simulator.dto;

public class ConnectionResponse {

        private Long id;
        private String connectionType;
        private Long architectureId;
        private Long sourceNodeId;
        private Long targetNodeId;

        public ConnectionResponse() {
        }

        public ConnectionResponse(
                Long id,
                String connectionType,
                Long architectureId,
                Long sourceNodeId,
                Long targetNodeId) {

            this.id = id;
            this.connectionType = connectionType;
            this.architectureId = architectureId;
            this.sourceNodeId = sourceNodeId;
            this.targetNodeId = targetNodeId;
        }

        public Long getId() {
            return id;
        }

        public String getConnectionType() {
            return connectionType;
        }

        public Long getArchitectureId() {
            return architectureId;
        }

        public Long getSourceNodeId() {
            return sourceNodeId;
        }

        public Long getTargetNodeId() {
            return targetNodeId;
        }

}
