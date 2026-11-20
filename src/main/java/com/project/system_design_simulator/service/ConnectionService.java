package com.project.system_design_simulator.service;

import com.project.system_design_simulator.entity.Architecture;
import com.project.system_design_simulator.entity.Connection;
import com.project.system_design_simulator.entity.Node;
import com.project.system_design_simulator.repository.ArchitectureRepository;
import com.project.system_design_simulator.repository.ConnectionRepository;
import com.project.system_design_simulator.repository.NodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionService {

    private final ConnectionRepository connectionRepository;

    private final ArchitectureRepository architectureRepository;

    private final NodeRepository nodeRepository;


    public ConnectionService(
            ConnectionRepository connectionRepository,
            ArchitectureRepository architectureRepository,
            NodeRepository nodeRepository) {

        this.connectionRepository =
                connectionRepository;

        this.architectureRepository =
                architectureRepository;

        this.nodeRepository =
                nodeRepository;
    }


    // =========================================================
    // CREATE CONNECTION
    // =========================================================

    public Connection createConnection(
            Long architectureId,
            Long sourceNodeId,
            Long targetNodeId,
            Connection connection) {


        Architecture architecture =
                architectureRepository
                        .findById(architectureId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Architecture not found"
                                )
                        );


        Node sourceNode =
                nodeRepository
                        .findById(sourceNodeId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Source node not found"
                                )
                        );


        Node targetNode =
                nodeRepository
                        .findById(targetNodeId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Target node not found"
                                )
                        );


        connection.setArchitecture(
                architecture
        );

        connection.setSourceNode(
                sourceNode
        );

        connection.setTargetNode(
                targetNode
        );


        return connectionRepository.save(
                connection
        );
    }


    // =========================================================
    // GET CONNECTIONS
    // =========================================================

    public List<Connection> getConnectionsByArchitecture(
            Long architectureId) {

        return connectionRepository
                .findByArchitectureId(
                        architectureId
                );
    }


    // =========================================================
    // GET CONNECTION
    // =========================================================

    public Connection getConnectionById(Long id) {

        return connectionRepository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Connection not found"
                        )
                );
    }


    // =========================================================
    // UPDATE CONNECTION
    // =========================================================

    public Connection updateConnection(
            Long id,
            Connection updatedConnection) {

        Connection existingConnection =
                getConnectionById(id);


        existingConnection.setConnectionType(
                updatedConnection.getConnectionType()
        );


        return connectionRepository.save(
                existingConnection
        );
    }


    // =========================================================
    // DELETE ONE CONNECTION
    // =========================================================

    public void deleteConnection(Long id) {

        if (!connectionRepository.existsById(id)) {

            throw new RuntimeException(
                    "Connection not found"
            );
        }


        connectionRepository.deleteById(id);
    }


    // =========================================================
    // DELETE ALL CONNECTIONS OF ARCHITECTURE
    // =========================================================

    public void deleteConnectionsByArchitecture(
            Long architectureId) {

        connectionRepository
                .deleteByArchitectureId(
                        architectureId
                );
    }
}