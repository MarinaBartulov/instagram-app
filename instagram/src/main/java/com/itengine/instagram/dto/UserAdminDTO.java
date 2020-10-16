package com.itengine.instagram.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAdminDTO {

    private Long id;
    private String username;
    private String email;
    private String name;
    private boolean active;
}
