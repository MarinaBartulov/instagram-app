package com.itengine.instagram.service;

import com.itengine.instagram.dto.FollowResponseDTO;
import com.itengine.instagram.model.User;

import java.util.List;

public interface FollowService {

    FollowResponseDTO followUser(Long id);
    FollowResponseDTO unfollowUser(Long id);
    List<User> getFollowersForUser(Long id);
    List<User> getFollowingForUser(Long id);
}
