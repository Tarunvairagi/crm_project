package com.crm.controller;

import com.crm.payload.CommentDto;
import com.crm.repository.CommentRepository;
import com.crm.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {

    private CommentService commentService;
    private final CommentRepository commentRepository;

    public CommentController(CommentService commentService,CommentRepository commentRepository){
        this.commentService = commentService;
        this.commentRepository = commentRepository;
    }

    //http://localhost:8080/api/v1/comments/addComments?postId=1
    @PostMapping("/addComments")
    public ResponseEntity<CommentDto> addPostComment(
            @RequestBody CommentDto commentDto,
            @RequestParam Long postId
    ){
        CommentDto addRecord = commentService.addComment(commentDto, postId);
        return new ResponseEntity<>(addRecord, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/v1/comments/{commentId}
    @DeleteMapping
    public ResponseEntity<String> deleteComment(
            @PathVariable long commentId
    ){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("Record is deleted by id : "+commentId,HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/comments/{commentId}
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateCommentRecord(
            @RequestBody CommentDto commentDto,
            @PathVariable long commentId
    ){
        CommentDto commentRecord = commentService.updateRecord(commentId, commentDto);
        return new ResponseEntity<>(commentRecord,HttpStatus.OK);
    }




}
