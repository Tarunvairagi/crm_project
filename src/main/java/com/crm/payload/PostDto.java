package com.crm.payload;

import com.crm.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDto {
    private long post_id;
    private String title;
    private String description;
    private List<Comment> comments;
}
