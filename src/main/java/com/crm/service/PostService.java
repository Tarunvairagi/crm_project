package com.crm.service;

import com.crm.entity.Comment;
import com.crm.entity.Post;
import com.crm.exception.ResourceNotFound;
import com.crm.payload.CommentDto;
import com.crm.payload.EmployeeDto;
import com.crm.payload.PostDto;
import com.crm.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository postRepository;
    private ModelMapper modelMapper;
    public PostService(PostRepository postRepository,ModelMapper modelMapper){
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    public PostDto mapToDto(Post post){ return modelMapper.map(post,PostDto.class); }
    public Post mapToEntity(PostDto postDto){
        return modelMapper.map(postDto,Post.class);
    }

    public PostDto addPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post record = postRepository.save(post);
        return mapToDto(record);
    }

    public void deletePost(long postId) {
        postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post is not present!")
        );
        postRepository.deleteById(postId);
    }

    public PostDto updatePost(long postId, PostDto postDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Record not present! : " + postId)
        );
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
//        post.setComments(postDto.getComments());
        Post save = postRepository.save(post);
        return mapToDto(save);
    }

    public List<PostDto> listOfPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    }
}
