package com.itengine.instagram.dto;

import com.itengine.instagram.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFollowDTO {

    private Long id;
    private String username;
    private String name;
    private String email;
    private boolean follow;

    public UserFollowDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
