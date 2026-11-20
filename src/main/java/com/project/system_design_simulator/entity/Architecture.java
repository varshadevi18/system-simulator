package com.project.system_design_simulator.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "architectures")
public class Architecture {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        private String description;

        public Architecture() {
        }

        public Architecture(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }


}
