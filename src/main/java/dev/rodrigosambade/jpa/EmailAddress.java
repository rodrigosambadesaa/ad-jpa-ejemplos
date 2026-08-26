package dev.rodrigosambade.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.Objects;

@Entity
@Table(
        name = "email_addresses",
        uniqueConstraints = @UniqueConstraint(columnNames = {"professor_id", "address"}))
public class EmailAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    protected EmailAddress() {
    }

    EmailAddress(String address, Professor professor) {
        if (address == null || address.isBlank() || !address.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.address = address.trim();
        this.professor = Objects.requireNonNull(professor, "professor");
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public Professor getProfessor() {
        return professor;
    }
}
