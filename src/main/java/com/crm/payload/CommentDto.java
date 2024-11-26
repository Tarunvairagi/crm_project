package com.crm.payload;

import com.crm.entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private long comment_id;
    private String name;
    private String description;
    private Long postId;
}
