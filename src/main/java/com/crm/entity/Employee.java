package com.crm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "email_id", nullable = false, unique = true, length = 200)
    private String emailId;

    @Column(name = "mobile", nullable = false, unique = true, length = 10)
    private String mobile;
}