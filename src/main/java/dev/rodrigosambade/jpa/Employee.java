package dev.rodrigosambade.jpa;
import jakarta.persistence.*;
import java.util.Objects;
@Entity @Table(name="employees") public class Employee {@Id @GeneratedValue(strategy=GenerationType.IDENTITY)private Long id;@Column(nullable=false)private String name;@ManyToOne(fetch=FetchType.LAZY,optional=false)private Department department;protected Employee(){} public Employee(String name,Department department){if(name==null||name.isBlank())throw new IllegalArgumentException("Employee name is required");this.name=name.trim();this.department=Objects.requireNonNull(department);}public Long getId(){return id;}public String getName(){return name;}public Department getDepartment(){return department;}}
