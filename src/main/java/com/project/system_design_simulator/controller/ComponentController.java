package com.project.system_design_simulator.controller;

import com.project.system_design_simulator.entity.Component;
import com.project.system_design_simulator.service.ComponentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/components")
@CrossOrigin(origins = "http://localhost:5173")
public class ComponentController {

    private final ComponentService componentService;

    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    @PostMapping
    public Component createComponent(
            @RequestBody Component component) {

        return componentService.createComponent(component);
    }

    @GetMapping
    public List<Component> getAllComponents() {

        return componentService.getAllComponents();
    }

    @DeleteMapping("/{id}")
    public void deleteComponent(@PathVariable Long id) {

        componentService.deleteComponent(id);
    }
}