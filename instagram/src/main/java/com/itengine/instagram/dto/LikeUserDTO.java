package com.itengine.instagram.dto;

import com.itengine.instagram.model.Like;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeUserDTO {

    private Long id;
    private String username;
    private Long userId;

    public LikeUserDTO(Like like){
        this.id = like.getId();
        this.username = like.getUser().getUsername();
        this.userId = like.getUser().getId();
    }
}
