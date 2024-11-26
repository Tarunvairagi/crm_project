package com.crm.entity;

import com.crm.payload.CommentDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long post_id;

    @Column(name="title", length=100, nullable = false)
    private String title;

    @Column(name="description", length=500, nullable = false)
    private String description;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Comment> comments;

}
