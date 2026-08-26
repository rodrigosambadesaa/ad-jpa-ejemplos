package dev.rodrigosambade.jpa;
import jakarta.persistence.*;
import java.util.*;
@Entity @Table(name="professors") public class Professor {@Id @GeneratedValue(strategy=GenerationType.IDENTITY)private Long id;@Column(nullable=false)private String name;@OneToMany(mappedBy="professor",cascade=CascadeType.ALL,orphanRemoval=true)private final List<EmailAddress> emails=new ArrayList<>();protected Professor(){name=null;}public Professor(String name){if(name==null||name.isBlank())throw new IllegalArgumentException("Professor name is required");this.name=name.trim();}public void addEmail(String address){emails.add(new EmailAddress(address,this));}public Long getId(){return id;}public String getName(){return name;}public List<EmailAddress> getEmails(){return List.copyOf(emails);}}
