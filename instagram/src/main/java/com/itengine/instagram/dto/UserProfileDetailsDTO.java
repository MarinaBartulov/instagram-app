package com.itengine.instagram.dto;

import com.itengine.instagram.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserProfileDetailsDTO {

    private UserFollowDTO user;
    private List<PostDTO> posts;

    public UserProfileDetailsDTO(User user, boolean follow){
        this.posts = user.getPosts().stream().map(post -> new PostDTO(post)).collect(Collectors.toList());
        this.user = new UserFollowDTO(user);
        this.user.setFollow(follow);
    }

}
