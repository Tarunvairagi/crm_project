package com.crm.controller;

import com.crm.payload.EmployeeDto;
import com.crm.payload.PostDto;
import com.crm.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {
    private PostService postService;
    public PostController(PostService postService){
        this.postService = postService;
    }

    //http://localhost:8080/api/v1/posts/addPost
    @PostMapping("/addPost")
    public ResponseEntity<PostDto> createPost(
        @RequestBody PostDto postDto
    ){
        PostDto record = postService.addPost(postDto);
        return new ResponseEntity<>(record, HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/posts/{post_id}
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePostRecord(
            @PathVariable long postId
    ){
        postService.deletePost(postId);
        return new ResponseEntity<>("Record is deleted by id : "+postId,HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/posts/{post_id}
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePostRecord(
            @PathVariable long postId,
            @RequestBody PostDto postDto
    ){
        PostDto updateRecord = postService.updatePost(postId, postDto);
        return new ResponseEntity<>(updateRecord,HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/posts
    @GetMapping
    public ResponseEntity<List<PostDto>> listOfPost(){
        List<PostDto> record = postService.listOfPosts();
        return new ResponseEntity<>(record,HttpStatus.OK);
    }

}
