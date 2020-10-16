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


    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllUsers(){

        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id){

        return new ResponseEntity<>(this.userService.findUser(id), HttpStatus.OK);
    }

    @GetMapping("/currentUser")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getCurrentuser() {

        return new ResponseEntity<>(this.userService.getCurrentUser(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){

        return new ResponseEntity<>(this.userService.deleteUser(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/followers")
    public ResponseEntity<?> getFollowersForUser(@PathVariable("id") Long id){

        return new ResponseEntity<>(this.userService.getFollowersForUser(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/following")
    public ResponseEntity<?> getFollowingForUser(@PathVariable("id") Long id){

        return new ResponseEntity<>(this.userService.getFollowingForUser(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchUsers(@RequestParam("username") String username){

        return new ResponseEntity<>(this.userService.searchUsers(username), HttpStatus.OK);
    }

    @GetMapping("/{id}/profileDetails")
    public ResponseEntity<?> getUserProfileDetails(@PathVariable("id") Long id){

        return new ResponseEntity<>(this.userService.getUserProfileDetails(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/ban")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> banUser(@PathVariable("id") Long id){

        return new ResponseEntity<>(this.userService.banUser(id), HttpStatus.OK);
    }



}
