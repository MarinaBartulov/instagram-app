package com.itengine.instagram.service.impl;

import com.itengine.instagram.dto.UserDTO;
import com.itengine.instagram.dto.UserRequest;
import com.itengine.instagram.exception.BadRequestException;
import com.itengine.instagram.model.User;
import com.itengine.instagram.model.VerificationToken;
import com.itengine.instagram.service.AuthService;
import com.itengine.instagram.service.EmailService;
import com.itengine.instagram.service.UserService;
import com.itengine.instagram.service.VerificationTokenService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private EmailService emailService;

    @Override
    public User singUp(UserRequest userRequest) throws MessagingException, InterruptedException {

        User existUser = this.userService.findByUsername(userRequest.getUsername());
        if(existUser != null) {
            throw new BadRequestException("This username already exists.");
        }

        User user = this.userService.save(userRequest);

        this.emailService.sendConfirmRegMail(user);
        return user;
    }

    @Override
    public UserDTO confirmRegistration(String token) {

        VerificationToken verificationToken = this.verificationTokenService.findToken(token);
        if (verificationToken == null) {
            String message = "Verification token doesn't exist.";
            throw new BadRequestException(message);
        }
        User user = verificationToken.getUser();
        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            String messageValue = "Token expired.";
            throw new BadRequestException(messageValue);
        }
        user.setEnabled(true);
        userService.update(user);
        return this.mapper.map(user, UserDTO.class);
    }
}
