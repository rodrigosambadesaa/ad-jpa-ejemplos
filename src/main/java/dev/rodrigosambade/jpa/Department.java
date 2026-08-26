package dev.rodrigosambade.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    protected Department() {
    }

    public Department(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Department name is required");
        }
        this.name = name.trim();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
