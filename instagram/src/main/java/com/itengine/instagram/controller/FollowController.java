package com.itengine.instagram.controller;

import com.itengine.instagram.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="api/follow")
@CrossOrigin
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping(value="/user/{id}")
    public ResponseEntity<?> followUser(@PathVariable("id") Long id){

        return new ResponseEntity(this.followService.followUser(id), HttpStatus.OK);
    }

    @DeleteMapping(value="/user/{id}")
    public ResponseEntity<?> unfollowUser(@PathVariable("id") Long id){

        return new ResponseEntity(this.followService.unfollowUser(id), HttpStatus.OK);
    }
}
