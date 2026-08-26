package dev.rodrigosambade.jpa;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "professors")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailAddress> emails = new ArrayList<>();

    protected Professor() {
    }

    public Professor(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Professor name is required");
        }
        this.name = name.trim();
    }

    public void addEmail(String address) {
        emails.add(new EmailAddress(address, this));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<EmailAddress> getEmails() {
        return List.copyOf(emails);
    }
}
