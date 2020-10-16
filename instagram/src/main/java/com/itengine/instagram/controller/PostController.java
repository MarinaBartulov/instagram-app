package com.itengine.instagram.controller;

import com.itengine.instagram.dto.PostNewDTO;
import com.itengine.instagram.dto.PostUpdateDTO;
import com.itengine.instagram.exception.BadRequestException;
import com.itengine.instagram.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@RestController
@RequestMapping(value="/api/post")
@CrossOrigin
public class PostController {

    @Autowired
    private PostService postService;


    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getPost(@PathVariable Long id){

        return new ResponseEntity(this.postService.getPost(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<?> getPostDetails(@PathVariable Long id){

        return new ResponseEntity(this.postService.getPostDetails(id), HttpStatus.OK);
    }


    @GetMapping(value="/user/{id}")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getUserPosts(@PathVariable Long id){

        return new ResponseEntity(this.postService.getUserPosts(id), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> addPost(@RequestParam("file") MultipartFile image, @RequestParam("description") String description) throws IOException {

        System.out.println(description);
        return new ResponseEntity(this.postService.addPost(image, description), HttpStatus.CREATED);
    }

    @PutMapping(value="/{id}")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> updatePost(@PathVariable("id") Long id, @RequestBody PostUpdateDTO postUpdateDTO){

        return new ResponseEntity(this.postService.updatePost(id, postUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id){

        return new ResponseEntity(this.postService.deletePost(id), HttpStatus.OK);
    }

    @GetMapping(value="/feed")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getUserPostsFeed(){

        return new ResponseEntity(this.postService.getUserPostsFeed(), HttpStatus.OK);
    }



}
