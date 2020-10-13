package com.itengine.instagram.controller;

import com.itengine.instagram.model.User;
import com.itengine.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id){

        return new ResponseEntity<>(this.userService.findUser(id), HttpStatus.OK);
    }

    @GetMapping("/currentUser")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> user() {

        return new ResponseEntity<>(this.userService.getCurrentUser(), HttpStatus.OK);
    }

}
