package com.crm.entity;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name="user_name", length = 100, nullable = false)
    private String userName;

    @Column(name = "password", length = 6, nullable = false)
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserProfile userProfile;

}
