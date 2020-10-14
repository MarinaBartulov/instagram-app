package com.itengine.instagram.service;

import com.itengine.instagram.dto.LikeDTO;

import java.util.List;

public interface LikeService {

    List<LikeDTO> getLikesOfUser();
    List<LikeDTO> getLikesForPost(Long id);
    int getNumberOfLikesForPost(Long id);
    LikeDTO likePost(Long id);
    LikeDTO unlikePost(Long id);


}
