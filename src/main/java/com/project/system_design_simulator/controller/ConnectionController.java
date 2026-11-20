package com.project.system_design_simulator.controller;

import com.project.system_design_simulator.dto.ConnectionResponse;
import com.project.system_design_simulator.entity.Connection;
import com.project.system_design_simulator.service.ConnectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ConnectionController {

    private final ConnectionService connectionService;


    public ConnectionController(
            ConnectionService connectionService) {

        this.connectionService =
                connectionService;
    }


    // =========================================================
    // CREATE CONNECTION
    // =========================================================

    @PostMapping(
            "/architectures/{architectureId}/connections"
    )
    public ConnectionResponse createConnection(

            @PathVariable Long architectureId,

            @RequestParam Long sourceNodeId,

            @RequestParam Long targetNodeId,

            @RequestBody Connection connection) {

        return convertToResponse(
                connectionService.createConnection(
                        architectureId,
                        sourceNodeId,
                        targetNodeId,
                        connection
                )
        );
    }


    // =========================================================
    // GET CONNECTIONS BY ARCHITECTURE
    // =========================================================

    @GetMapping(
            "/architectures/{architectureId}/connections"
    )
    public List<ConnectionResponse> getConnections(
            @PathVariable Long architectureId) {

        return connectionService
                .getConnectionsByArchitecture(
                        architectureId
                )
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    // =========================================================
    // DELETE ALL CONNECTIONS OF ARCHITECTURE
    // =========================================================

    @DeleteMapping(
            "/architectures/{architectureId}/connections"
    )
    public ResponseEntity<Void>
    deleteConnectionsByArchitecture(
            @PathVariable Long architectureId) {

        connectionService
                .deleteConnectionsByArchitecture(
                        architectureId
                );

        return ResponseEntity
                .noContent()
                .build();
    }


    // =========================================================
    // GET ONE CONNECTION
    // =========================================================

    @GetMapping("/connections/{id}")
    public ResponseEntity<ConnectionResponse>
    getConnection(
            @PathVariable Long id) {

        try {

            Connection connection =
                    connectionService
                            .getConnectionById(id);

            return ResponseEntity.ok(
                    convertToResponse(connection)
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }


    // =========================================================
    // UPDATE CONNECTION
    // =========================================================

    @PutMapping("/connections/{id}")
    public ConnectionResponse updateConnection(

            @PathVariable Long id,

            @RequestBody Connection connection) {

        return convertToResponse(
                connectionService.updateConnection(
                        id,
                        connection
                )
        );
    }


    // =========================================================
    // DELETE ONE CONNECTION
    // =========================================================

    @DeleteMapping("/connections/{id}")
    public ResponseEntity<Void> deleteConnection(
            @PathVariable Long id) {

        connectionService.deleteConnection(id);

        return ResponseEntity
                .noContent()
                .build();
    }


    // =========================================================
    // RESPONSE CONVERTER
    // =========================================================

    private ConnectionResponse convertToResponse(
            Connection connection) {

        return new ConnectionResponse(

                connection.getId(),

                connection.getConnectionType(),

                connection.getArchitecture().getId(),

                connection.getSourceNode().getId(),

                connection.getTargetNode().getId()
        );
    }
}