package com.itengine.instagram.dto;

import com.itengine.instagram.model.Follow;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FollowResponseDTO {

    private Long id;
    private UserResponseDTO follower;
    private UserResponseDTO followed;
    private LocalDateTime dateTimeFollowed;

    public FollowResponseDTO(Follow follow){
        this.id = follow.getId();
        this.follower = new UserResponseDTO(follow.getFollower());
        this.followed = new UserResponseDTO(follow.getFollowed());
        this.dateTimeFollowed = follow.getDateTimeFollowed();
    }
}
