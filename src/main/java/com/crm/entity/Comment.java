package com.crm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long comment_id;

    @Column(name="name", length = 100, nullable = false)
    private String name;

    @Column(name="description", length = 150, nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @JsonBackReference
    private Post post;

}
