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
    private ResponseEntity<?> getPost(@PathVariable Long id){

        return new ResponseEntity(this.postService.getPost(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/details")
    private ResponseEntity<?> getPostDetails(@PathVariable Long id){

        return new ResponseEntity(this.postService.getPostDetails(id), HttpStatus.OK);
    }


    @GetMapping(value="/user/{id}")
    //@PreAuthorize("hasRole('ROLE_USER')")
    private ResponseEntity<?> getUserPosts(@PathVariable Long id){

        return new ResponseEntity(this.postService.getUserPosts(id), HttpStatus.OK);
    }

    /*@PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    private ResponseEntity<?> addPost(@RequestBody PostNewDTO postNewDTO){

        return new ResponseEntity(this.postService.addPost(postNewDTO), HttpStatus.CREATED);
    }*/

    @PostMapping("/upload")
    private ResponseEntity<?> addPost(@RequestParam("file") MultipartFile image) throws IOException {


        if(image == null){
            System.out.println("Image is null");
        }

        File convertFile = new File("C:\\MarinaB-Praksa\\instagram-app\\instagram-client\\src\\assets\\images\\" + image.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(image.getBytes());
        fout.close();

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(value="/{id}")
    //@PreAuthorize("hasRole('ROLE_USER')")
    private ResponseEntity<?> updatePost(@PathVariable("id") Long id, @RequestBody PostUpdateDTO postUpdateDTO){

        return new ResponseEntity(this.postService.updatePost(id, postUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    private ResponseEntity<?> deletePost(@PathVariable("id") Long id){

        return new ResponseEntity(this.postService.deletePost(id), HttpStatus.OK);
    }

    @GetMapping(value="/feed")
    //@PreAuthorize("hasRole('ROLE_USER')")
    private ResponseEntity<?> getUserPostsFeed(){

        return new ResponseEntity(this.postService.getUserPostsFeed(), HttpStatus.OK);
    }



}
