package com.itengine.instagram.controller;

import com.itengine.instagram.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/like")
@CrossOrigin
public class LikeController {

    @Autowired
    private LikeService likeService;


    @GetMapping
    public ResponseEntity<?> getLikesOfUser(){

        return new ResponseEntity<>(this.likeService.getLikesOfUser(), HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getLikesForPost(@PathVariable("id") Long id){

        return new ResponseEntity<>(this.likeService.getLikesForPost(id), HttpStatus.OK);
    }

    @GetMapping("post/{id}/number")
    public ResponseEntity<?> getNumberOfLikesForPost(@PathVariable("id") Long id){

        return new ResponseEntity<>(this.likeService.getNumberOfLikesForPost(id), HttpStatus.OK);
    }

    @PostMapping("/post/{id}")
    public ResponseEntity<?> likePost(@PathVariable("id") Long id){

        return new ResponseEntity<>(this.likeService.likePost(id), HttpStatus.CREATED);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> unlikePost(@PathVariable("id") Long id){

        return new ResponseEntity<>(this.likeService.unlikePost(id), HttpStatus.OK);
    }
}
