package com.project.system_design_simulator.service;
import com.project.system_design_simulator.entity.Architecture;
import com.project.system_design_simulator.entity.Node;
import com.project.system_design_simulator.repository.ArchitectureRepository;
import com.project.system_design_simulator.repository.NodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeService {

        private final NodeRepository nodeRepository;
        private final ArchitectureRepository architectureRepository;

        public NodeService(
                NodeRepository nodeRepository,
                ArchitectureRepository architectureRepository) {

            this.nodeRepository = nodeRepository;
            this.architectureRepository = architectureRepository;
        }

        public Node createNode(Long architectureId, Node node) {

            Architecture architecture = architectureRepository
                    .findById(architectureId)
                    .orElseThrow(() ->
                            new RuntimeException("Architecture not found"));

            node.setArchitecture(architecture);

            return nodeRepository.save(node);
        }

        public List<Node> getAllNodes() {
            return nodeRepository.findAll();
        }

        public List<Node> getNodesByArchitecture(Long architectureId) {
            return nodeRepository.findByArchitectureId(architectureId);
        }

        public Node getNodeById(Long id) {

            return nodeRepository.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("Node not found"));
        }

        public Node updateNode(Long id, Node updatedNode) {

            Node existingNode = getNodeById(id);

            existingNode.setLabel(updatedNode.getLabel());
            existingNode.setType(updatedNode.getType());
            existingNode.setPositionX(updatedNode.getPositionX());
            existingNode.setPositionY(updatedNode.getPositionY());

            return nodeRepository.save(existingNode);
        }

        public void deleteNode(Long id) {

            if (!nodeRepository.existsById(id)) {
                throw new RuntimeException("Node not found");
            }

            nodeRepository.deleteById(id);
        }

}
