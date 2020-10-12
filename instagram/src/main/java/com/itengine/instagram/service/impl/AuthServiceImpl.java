package com.itengine.instagram.service.impl;

import com.itengine.instagram.dto.UserRequest;
import com.itengine.instagram.model.User;
import com.itengine.instagram.service.AuthService;
import com.itengine.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Override
    public User singUp(UserRequest userRequest) {

        User existUser = this.userService.findByUsername(userRequest.getUsername());
        if(existUser != null) {
            return null;
        }

        User user = this.userService.save(userRequest);

        return user;
    }
}
