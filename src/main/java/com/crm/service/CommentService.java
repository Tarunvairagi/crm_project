package com.crm.service;

import com.crm.entity.Comment;
import com.crm.entity.Post;
import com.crm.exception.ResourceNotFound;
import com.crm.payload.CommentDto;
import com.crm.repository.CommentRepository;
import com.crm.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
@Service
public class CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public CommentService(CommentRepository commentRepository,PostRepository postRepository,ModelMapper modelMapper){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    public CommentDto mapToDto(Comment comment){
        CommentDto record = modelMapper.map(comment, CommentDto.class);
        if (comment.getPost() != null) {
            record.setPostId(comment.getPost().getPost_id()); // Assuming Post has a `getPost_id()` method
        }
        return record;
    }

    public Comment mapToEntity(CommentDto commentDto){
        return modelMapper.map(commentDto,Comment.class);
    }

    public CommentDto addComment(CommentDto commentDto, Long postId) {
        Post record = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Record not found!")
        );

        Comment comment = mapToEntity(commentDto);
        comment.setPost(record);
        Comment recordSave = commentRepository.save(comment);
        return mapToDto(recordSave);
    }

    public void deleteComment(long commentId) {
        commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFound("Record not present!")
        );
        commentRepository.deleteById(commentId);
    }

    public CommentDto updateRecord(long commentId,CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFound("Record not present!")
        );
        comment.setName(commentDto.getName());
        comment.setDescription(commentDto.getDescription());
//        comment.setPost(commentDto.getPostId());
        Comment record = commentRepository.save(comment);
        return mapToDto(record);
    }
}
