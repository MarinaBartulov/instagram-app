package com.itengine.instagram.service.impl;

import com.itengine.instagram.dto.PostDTO;
import com.itengine.instagram.dto.PostNewDTO;
import com.itengine.instagram.dto.PostUpdateDTO;
import com.itengine.instagram.exception.BadRequestException;
import com.itengine.instagram.exception.NotFoundException;
import com.itengine.instagram.model.Post;
import com.itengine.instagram.model.User;
import com.itengine.instagram.repository.PostRepository;
import com.itengine.instagram.service.PostService;
import com.itengine.instagram.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;


    @Override
    public Post findById(Long id) {
        return this.postRepository.findById(id).orElse(null);
    }

    @Override
    public List<PostDTO> getUserPosts(Long id) {

        if(id <= 0){
            throw new NotFoundException("User with id " + id + " doesn't exist in the system.");
        }
        List<Post> posts = this.postRepository.findUserPosts(id);
        return posts.stream().map(post -> mapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO getPost(Long id) {

        if(id <= 0){
            throw new BadRequestException("User id can't be equal or less than 0.");
        }
        Post post = this.postRepository.findById(id).orElse(null);
        if(post == null){
            throw new NotFoundException("User with id " + id + " doesn't exist in th system.");
        }

        return mapper.map(post, PostDTO.class);
    }

    @Override
    public PostDTO addPost(PostNewDTO postNewDTO) {

        String errorMsg = "";
        if(postNewDTO.getDescription().equals("") || postNewDTO.getDescription() == null){
            errorMsg += "Description is required.";
        }
        if(postNewDTO.getPath().equals("") || postNewDTO.getPath() == null){
            errorMsg += "Photo is required.";
        }
        if(!errorMsg.equals("")){
            throw new BadRequestException("Description and photo are required.");
        }
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        User user = this.userService.findByUsername(username);

        Post post = new Post();
        post.setDescription(postNewDTO.getDescription());
        post.setPhotoPath(postNewDTO.getPath());
        post.setDateTime(LocalDateTime.now());
        post.setUser(user);

        this.postRepository.save(post);
        return mapper.map(post, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(Long id, PostUpdateDTO postUpdateDTO) {
        if(id <= 0){
            throw new BadRequestException("User id can't be equal or less than 0.");
        }

        if(postUpdateDTO.getDescription().equals("") || postUpdateDTO.getDescription() == null){
            throw new BadRequestException("Description is required.");
        }

        Post post = this.postRepository.findById(id).orElse(null);
        if(post == null){
            throw new NotFoundException("User with id " + id + " doesn't exist in th system.");
        }

        post.setDescription(postUpdateDTO.getDescription());
        this.postRepository.save(post);

        return mapper.map(post, PostDTO.class);
    }

    @Override
    public PostDTO deletePost(Long id) {

        if(id <= 0){
            throw new BadRequestException("User id can't be equal or less than 0.");
        }
        Post post = this.postRepository.findById(id).orElse(null);
        if(post == null){
            throw new NotFoundException("User with id " + id + " doesn't exist in th system.");
        }
        post.setDeleted(true);
        this.postRepository.save(post);
        return mapper.map(post, PostDTO.class);
    }
}
