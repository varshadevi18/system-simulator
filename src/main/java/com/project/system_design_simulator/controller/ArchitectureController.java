package com.project.system_design_simulator.controller;

import com.project.system_design_simulator.entity.Architecture;
import com.project.system_design_simulator.service.ArchitectureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/architectures")
@CrossOrigin(origins = "http://localhost:5173")

public class ArchitectureController {

        private final ArchitectureService architectureService;

        public ArchitectureController(ArchitectureService architectureService) {
            this.architectureService = architectureService;
        }

        @PostMapping
        public Architecture createArchitecture(
                @RequestBody Architecture architecture) {

            return architectureService.createArchitecture(architecture);
        }

        @GetMapping
        public List<Architecture> getAllArchitectures() {

            return architectureService.getAllArchitectures();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Architecture> getArchitectureById(
                @PathVariable Long id) {

            return architectureService.getArchitectureById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @PutMapping("/{id}")
        public Architecture updateArchitecture(
                @PathVariable Long id,
                @RequestBody Architecture architecture) {

            return architectureService.updateArchitecture(id, architecture);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteArchitecture(
                @PathVariable Long id) {

            architectureService.deleteArchitecture(id);

            return ResponseEntity.noContent().build();
        }

}
