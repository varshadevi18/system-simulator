//package com.project.system_design_simulator.controller;
//
//import com.project.system_design_simulator.entity.Node;
//import com.project.system_design_simulator.service.NodeService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:5173")
//public class NodeController {
//
//        private final NodeService nodeService;
//
//        public NodeController(NodeService nodeService) {
//            this.nodeService = nodeService;
//        }
//
//        @PostMapping("/architectures/{architectureId}/nodes")
//        public Node createNode(
//                @PathVariable Long architectureId,
//                @RequestBody Node node) {
//
//            return nodeService.createNode(architectureId, node);
//        }
//
//        @GetMapping("/nodes")
//        public List<Node> getAllNodes() {
//
//            return nodeService.getAllNodes();
//        }
//
//        @GetMapping("/architectures/{architectureId}/nodes")
//        public List<Node> getNodesByArchitecture(
//                @PathVariable Long architectureId) {
//
//            return nodeService.getNodesByArchitecture(architectureId);
//        }
//
//        @GetMapping("/nodes/{id}")
//        public ResponseEntity<Node> getNodeById(
//                @PathVariable Long id) {
//
//            try {
//                return ResponseEntity.ok(
//                        nodeService.getNodeById(id)
//                );
//            } catch (RuntimeException e) {
//                return ResponseEntity.notFound().build();
//            }
//        }
//
//        @PutMapping("/nodes/{id}")
//        public Node updateNode(
//                @PathVariable Long id,
//                @RequestBody Node node) {
//
//            return nodeService.updateNode(id, node);
//        }
//
//        @DeleteMapping("/nodes/{id}")
//        public ResponseEntity<Void> deleteNode(
//                @PathVariable Long id) {
//
//            nodeService.deleteNode(id);
//
//            return ResponseEntity.noContent().build();
//        }
//
//}
package com.project.system_design_simulator.controller;

import com.project.system_design_simulator.dto.NodeResponse;
import com.project.system_design_simulator.entity.Node;
import com.project.system_design_simulator.service.NodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class NodeController {

    private final NodeService nodeService;

    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @PostMapping("/architectures/{architectureId}/nodes")
    public NodeResponse createNode(
            @PathVariable Long architectureId,
            @RequestBody Node node) {

        return convertToResponse(
                nodeService.createNode(architectureId, node)
        );
    }

    @GetMapping("/nodes")
    public List<NodeResponse> getAllNodes() {

        return nodeService.getAllNodes()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @GetMapping("/architectures/{architectureId}/nodes")
    public List<NodeResponse> getNodesByArchitecture(
            @PathVariable Long architectureId) {

        return nodeService
                .getNodesByArchitecture(architectureId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @GetMapping("/nodes/{id}")
    public ResponseEntity<NodeResponse> getNodeById(
            @PathVariable Long id) {

        try {

            Node node = nodeService.getNodeById(id);

            return ResponseEntity.ok(
                    convertToResponse(node)
            );

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/nodes/{id}")
    public NodeResponse updateNode(
            @PathVariable Long id,
            @RequestBody Node node) {

        return convertToResponse(
                nodeService.updateNode(id, node)
        );
    }

    @DeleteMapping("/nodes/{id}")
    public ResponseEntity<Void> deleteNode(
            @PathVariable Long id) {

        nodeService.deleteNode(id);

        return ResponseEntity.noContent().build();
    }

    private NodeResponse convertToResponse(Node node) {

        return new NodeResponse(
                node.getId(),
                node.getLabel(),
                node.getType(),
                node.getPositionX(),
                node.getPositionY(),
                node.getArchitecture().getId()
        );
    }
}
