package com.itengine.instagram.controller;

import com.itengine.instagram.dto.UserRequest;
import com.itengine.instagram.dto.UserTokenState;
import com.itengine.instagram.model.User;
import com.itengine.instagram.model.VerificationToken;
import com.itengine.instagram.security.TokenUtils;
import com.itengine.instagram.security.auth.JwtAuthenticationRequest;
import com.itengine.instagram.service.AuthService;
import com.itengine.instagram.service.UserService;
import com.itengine.instagram.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value="/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
                                                       HttpServletResponse response) throws AuthenticationException, IOException {

        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        if(!user.isActive()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Your Instagram account is deactivated.");
        }
        String jwt = tokenUtils.generateToken(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();

        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@RequestBody UserRequest userRequest) throws MessagingException, InterruptedException {

        return new ResponseEntity(this.authService.singUp(userRequest), HttpStatus.CREATED);

    }

    @RequestMapping(value = "/confirmRegistration/{token}", method = RequestMethod.GET)
    public ResponseEntity<?> confirmRegistration(@PathVariable("token") String token) {

        return new ResponseEntity(this.authService.confirmRegistration(token), HttpStatus.OK);
    }

}
