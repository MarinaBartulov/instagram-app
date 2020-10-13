package com.itengine.instagram.service;

import com.itengine.instagram.dto.UserDTO;
import com.itengine.instagram.dto.UserRequest;
import com.itengine.instagram.model.User;

import javax.mail.MessagingException;

public interface AuthService {

    User singUp(UserRequest userRequest) throws MessagingException, InterruptedException;
    UserDTO confirmRegistration(String token);
}
