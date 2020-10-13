package com.itengine.instagram.dto;

import com.itengine.instagram.model.Authority;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    private String username;
    private String email;
    private String name;
    private List<Authority> authorities;
}
