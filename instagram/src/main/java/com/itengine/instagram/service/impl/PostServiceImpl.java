package com.itengine.instagram.service.impl;

import com.itengine.instagram.dto.*;
import com.itengine.instagram.exception.BadRequestException;
import com.itengine.instagram.exception.NotFoundException;
import com.itengine.instagram.model.Follow;
import com.itengine.instagram.model.Photo;
import com.itengine.instagram.model.Post;
import com.itengine.instagram.model.User;
import com.itengine.instagram.repository.PostRepository;
import com.itengine.instagram.service.FileStorageService;
import com.itengine.instagram.service.PostService;
import com.itengine.instagram.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    @Autowired
    private FileStorageService fileStorageService;


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
    public PostDTO addPost(MultipartFile file, String description) throws IOException {

        String errorMsg = "";
        if(description.equals("") || description == null){
            errorMsg += "Description is required.";
        }
        if(file == null){
            errorMsg += "Photo is required.";
        }
        if(!errorMsg.equals("")){
            throw new BadRequestException("Description and photo are required.");
        }
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        User user = this.userService.findByUsername(username);

        Photo photo = this.fileStorageService.storeFile(file);
        Post post = new Post();
        post.setDescription(description);
        post.setPhoto(photo);
        post.setDateTime(LocalDateTime.now());
        post.setUser(user);

        this.postRepository.save(post);
        return new PostDTO(post);
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

    @Override
    public PostDetailsDTO getPostDetails(Long id) {
        if(id <= 0){
            throw new BadRequestException("User id can't be equal or less than 0.");
        }
        Post post = this.postRepository.findById(id).orElse(null);
        if(post == null){
            throw new NotFoundException("User with id " + id + " doesn't exist in th system.");
        }

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        User user = this.userService.findByUsername(username);

        PostDetailsDTO postDetailsDTO = new PostDetailsDTO(post, user.getId());
        return postDetailsDTO;
    }

    @Override
    public List<PostDetailsDTO> getUserPostsFeed() {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        User user = this.userService.findByUsername(username);
        List<Post> posts = new ArrayList<>();
        for(Follow following: user.getFollowing()){
            posts.addAll(following.getFollowed().getPosts());
        }
        Collections.sort(posts, new SortByDate());
        return posts.stream().map(post -> new PostDetailsDTO(post, user.getId())).collect(Collectors.toList());
    }

    static class SortByDate implements Comparator<Post> {
        @Override
        public int compare(Post a, Post b) {
            return b.getDateTime().compareTo(a.getDateTime());
        }
    }
}
