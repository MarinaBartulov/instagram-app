package com.itengine.instagram.service;

import com.itengine.instagram.dto.UserDTO;
import com.itengine.instagram.dto.UserRequest;
import com.itengine.instagram.dto.UserTokenState;
import com.itengine.instagram.model.User;
import com.itengine.instagram.security.auth.JwtAuthenticationRequest;

import javax.mail.MessagingException;

public interface AuthService {

    UserTokenState login(JwtAuthenticationRequest authenticationRequest);
    User singUp(UserRequest userRequest) throws MessagingException, InterruptedException;
    UserDTO confirmRegistration(String token);
}
