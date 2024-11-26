package com.crm.entity;

import jakarta.persistence.*;

@Entity
@Table(name="user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userProfileId;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "email", length = 250, nullable = false,unique = true)
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
