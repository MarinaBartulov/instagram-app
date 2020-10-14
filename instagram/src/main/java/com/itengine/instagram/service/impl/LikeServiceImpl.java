package com.itengine.instagram.service.impl;

import com.itengine.instagram.dto.LikeDTO;
import com.itengine.instagram.exception.BadRequestException;
import com.itengine.instagram.exception.NotFoundException;
import com.itengine.instagram.model.Like;
import com.itengine.instagram.model.Post;
import com.itengine.instagram.model.User;
import com.itengine.instagram.repository.LikeRepository;
import com.itengine.instagram.service.LikeService;
import com.itengine.instagram.service.PostService;
import com.itengine.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @Override
    public List<LikeDTO> getLikesOfUser() {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        User user = this.userService.findByUsername(username);

        return user.getLikes().stream().map(like -> new LikeDTO(like)).collect(Collectors.toList());
    }

    @Override
    public List<LikeDTO> getLikesForPost(Long id) {

        if(id <= 0){
            throw new BadRequestException("Post id can't be equal or less than 0.");
        }
        Post post = this.postService.findById(id);
        if(post == null){
            throw new NotFoundException("Post with id " + id + " doesn't exist in th system.");
        }
        return post.getLikes().stream().map(like -> new LikeDTO(like)).collect(Collectors.toList());
    }

    @Override
    public int getNumberOfLikesForPost(Long id) {
        if(id <= 0){
            throw new BadRequestException("Post id can't be equal or less than 0.");
        }
        Post post = this.postService.findById(id);
        if(post == null){
            throw new NotFoundException("Post with id " + id + " doesn't exist in th system.");
        }
        return post.getLikes().size();
    }

    @Override
    public LikeDTO likePost(Long id) {

        if(id <= 0){
            throw new BadRequestException("Post id can't be equal or less than 0.");
        }
        Post post = this.postService.findById(id);
        if(post == null){
            throw new NotFoundException("Post with id " + id + " doesn't exist in th system.");
        }
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        User user = this.userService.findByUsername(username);

        Like like = this.likeRepository.findByPostAndUser(post,user);
        if(like != null){
            throw new BadRequestException("You have already liked this post.");
        }

        Like newLike = new Like();
        newLike.setUser(user);
        newLike.setPost(post);
        newLike.setDateTimeLiked(LocalDateTime.now());
        this.likeRepository.save(newLike);
        return new LikeDTO(newLike);
    }

    @Override
    public LikeDTO unlikePost(Long id) {
        if(id <= 0){
            throw new BadRequestException("Post id can't be equal or less than 0.");
        }
        Post post = this.postService.findById(id);
        if(post == null){
            throw new NotFoundException("Post with id " + id + " doesn't exist in th system.");
        }
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        User user = this.userService.findByUsername(username);

        Like like = this.likeRepository.findByPostAndUser(post,user);
        if(like == null){
            throw new BadRequestException("You haven't liked this post so can't unlike it.");
        }

        this.likeRepository.delete(like);
        return new LikeDTO(like);
    }
}
