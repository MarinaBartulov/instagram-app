package com.itengine.instagram.service;

import com.itengine.instagram.dto.FollowResponseDTO;

public interface FollowService {

    FollowResponseDTO followUser(Long id);
    FollowResponseDTO unfollowUser(Long id);
}
