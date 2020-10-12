package com.itengine.instagram.service;

import com.itengine.instagram.dto.UserRequest;
import com.itengine.instagram.model.User;

public interface UserService {

    User findByUsername(String username);
    User save(UserRequest userRequest);
}
