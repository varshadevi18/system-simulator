package com.project.system_design_simulator.service;

import com.project.system_design_simulator.entity.Architecture;
import com.project.system_design_simulator.repository.ArchitectureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ArchitectureService {

        private final ArchitectureRepository architectureRepository;

        public ArchitectureService(ArchitectureRepository architectureRepository) {
            this.architectureRepository = architectureRepository;
        }

        public Architecture createArchitecture(Architecture architecture) {
            return architectureRepository.save(architecture);
        }

        public List<Architecture> getAllArchitectures() {
            return architectureRepository.findAll();
        }

        public Optional<Architecture> getArchitectureById(Long id) {
            return architectureRepository.findById(id);
        }

        public Architecture updateArchitecture(Long id, Architecture updatedArchitecture) {

            Architecture existing = architectureRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Architecture not found"));

            existing.setName(updatedArchitecture.getName());
            existing.setDescription(updatedArchitecture.getDescription());

            return architectureRepository.save(existing);
        }

        public void deleteArchitecture(Long id) {
            architectureRepository.deleteById(id);
        }
    }


