package com.itengine.instagram.controller;

import com.itengine.instagram.dto.CommentNewDTO;
import com.itengine.instagram.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/comment")
@CrossOrigin
public class CommentController {


    @Autowired
    private CommentService commentService;

    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<?> getComment(@PathVariable("id") Long id){

        return new ResponseEntity(this.commentService.getComment(id), HttpStatus.OK);
    }

    @GetMapping(value="/post/{id}", produces = "application/json")
    public ResponseEntity<?> getPostComment(@PathVariable("id") Long id){

        return new ResponseEntity(this.commentService.getCommentsForPost(id), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> addCommentForPost(@RequestBody CommentNewDTO commentNewDTO){

        return new ResponseEntity(this.commentService.addCommentForPost(commentNewDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id){

        return new ResponseEntity(this.commentService.deleteComment(id), HttpStatus.OK);
    }
}
