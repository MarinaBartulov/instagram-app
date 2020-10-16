package com.itengine.instagram.service.impl;

import com.itengine.instagram.dto.FollowResponseDTO;
import com.itengine.instagram.exception.BadRequestException;
import com.itengine.instagram.exception.NotFoundException;
import com.itengine.instagram.model.Follow;
import com.itengine.instagram.model.User;
import com.itengine.instagram.repository.FollowRepository;
import com.itengine.instagram.service.FollowService;
import com.itengine.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {


    @Autowired
    private UserService userService;
    @Autowired
    private FollowRepository followRepository;

    @Override
    public FollowResponseDTO followUser(Long id) {

        if(id<=0){
            throw new BadRequestException("User id can't be equal or less than 0.");
        }

        User followed = this.userService.findById(id);
        if(followed == null){
            throw new NotFoundException("User with id " + id + " doesn't exist in the system.");
        }

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        User user = this.userService.findByUsername(username);

        if(user.getId() == followed.getId()){
            throw new BadRequestException("You can't follow yourself.");
        }

        Follow existsFollow = this.followRepository.findByFollowedAndFollower(followed, user);
        if(existsFollow != null){
            throw new BadRequestException("You already follow this user.");
        }
        Follow follow = new Follow();
        follow.setFollower(user);
        follow.setFollowed(followed);
        follow.setDateTimeFollowed(LocalDateTime.now());
        this.followRepository.save(follow);
        return new FollowResponseDTO(follow);
    }

    @Override
    public FollowResponseDTO unfollowUser(Long id) {
        if(id<=0){
            throw new BadRequestException("User id can't be equal or less than 0.");
        }

        User followed = this.userService.findById(id);
        if(followed == null){
            throw new NotFoundException("User with id " + id + " doesn't exist in the system.");
        }
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        User user = this.userService.findByUsername(username);

        if(user.getId() == followed.getId()){
            throw new BadRequestException("You can't unfollow yourself.");
        }

        Follow follow = this.followRepository.findByFollowedAndFollower(followed, user);
        if(follow == null){
            throw new BadRequestException("You don't follow this user.");
        }

        this.followRepository.delete(follow);

        return new FollowResponseDTO(follow);
    }

    @Override
    public List<User> getFollowersForUser(Long id) {

        return this.followRepository.getFollowersForUser(id);
    }

    @Override
    public List<User> getFollowingForUser(Long id) {

        return this.followRepository.getFollowingForUser(id);
    }
}
