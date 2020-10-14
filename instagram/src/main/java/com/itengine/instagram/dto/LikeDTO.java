package com.itengine.instagram.dto;

import com.itengine.instagram.model.Like;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeDTO {

    private Long id;
    private PostResponseDTO post;
    private UserResponseDTO user;

    public LikeDTO(Like like){
        this.id = like.getId();
        this.post = new PostResponseDTO(like.getPost());
        this.user = new UserResponseDTO(like.getUser());
    }
}
