package com.project.system_design_simulator.service;

import com.project.system_design_simulator.entity.Component;
import com.project.system_design_simulator.repository.ComponentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentService {

    private final ComponentRepository componentRepository;

    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    public Component createComponent(Component component) {
        return componentRepository.save(component);
    }

    public List<Component> getAllComponents() {
        return componentRepository.findAll();
    }

    public void deleteComponent(Long id) {
        if (!componentRepository.existsById(id)) {
            throw new RuntimeException("Component not found");
        }

        componentRepository.deleteById(id);
    }
}