package com.itengine.instagram.service.impl;

import com.itengine.instagram.dto.CommentAdminDTO;
import com.itengine.instagram.dto.CommentDTO;
import com.itengine.instagram.dto.CommentNewDTO;
import com.itengine.instagram.exception.BadRequestException;
import com.itengine.instagram.exception.NotFoundException;
import com.itengine.instagram.model.Comment;
import com.itengine.instagram.model.Post;
import com.itengine.instagram.model.User;
import com.itengine.instagram.repository.CommentRepository;
import com.itengine.instagram.service.CommentService;
import com.itengine.instagram.service.PostService;
import com.itengine.instagram.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;


    @Override
    public CommentDTO getComment(Long id) {
        if(id <= 0){
            throw new BadRequestException("Comment id can't be equal or less than 0.");
        }
        Comment comment = this.commentRepository.findById(id).orElse(null);
        if(comment == null){
            throw new NotFoundException("Comment with id " + id + " doesn't exist.");
        }


        return new CommentDTO(comment);
    }

    @Override
    public List<CommentDTO> getCommentsForPost(Long id) {

        if(id <= 0){
            throw new BadRequestException("Post id can't be equal or less than 0.");
        }
        Post post = this.postService.findById(id);
        if(post == null){
            throw new NotFoundException("Post with id " + id + " doesn't exist.");
        }

        List<Comment> comments = this.commentRepository.findCommentsForPost(id);
        return comments.stream().map(c -> new CommentDTO(c)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO addCommentForPost(CommentNewDTO commentNewDTO) {

        if(commentNewDTO.getPostId() <= 0){
            throw new BadRequestException("Post id can't be equal or less than 0.");
        }

        if(commentNewDTO.getText().equals("") || commentNewDTO.getText() == null){
            throw new BadRequestException("Comment can't be empty.");
        }

        Post post = this.postService.findById(commentNewDTO.getPostId());
        if(post == null){
            throw new NotFoundException("Post with id " + commentNewDTO.getPostId() + " doesn't exist.");
        }

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        User user = this.userService.findByUsername(username);

        //check if logged in user is an owner of post or follow the owner

        Comment comment = new Comment();
        comment.setText(commentNewDTO.getText());
        comment.setPost(post);
        comment.setUser(user);
        comment.setDateTime(LocalDateTime.now());
        this.commentRepository.save(comment);

        return new CommentDTO(comment);
    }

    @Override
    public CommentDTO deleteComment(Long id) {

        if(id <= 0){
            throw new BadRequestException("Comment id can't be equal or less than 0.");
        }
        Comment comment = this.commentRepository.findById(id).orElse(null);
        if(comment == null){
            throw new NotFoundException("Comment with id " + id + " doesn't exist.");
        }

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        User user = this.userService.findByUsername(username);

        if(comment.getUser().getId() == user.getId() || comment.getPost().getUser().getId() == user.getId()){
            comment.setDeleted(true);
            this.commentRepository.save(comment);
        }else{
            throw new BadRequestException("You are not allowed to delete this comment.");
        }

        return new CommentDTO(comment);
    }

    @Override
    public List<CommentAdminDTO> getAllComments() {

        List<Comment> comments = this.commentRepository.findAll();
        return comments.stream().map(c -> new CommentAdminDTO(c)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO deleteCommentAdmin(Long id) {
        if(id <= 0){
            throw new BadRequestException("Comment id can't be equal or less than 0.");
        }
        Comment comment = this.commentRepository.findById(id).orElse(null);
        if(comment == null){
            throw new NotFoundException("Comment with id " + id + " doesn't exist.");
        }

        comment.setDeleted(true);
        this.commentRepository.save(comment);
        return new CommentDTO(comment);
    }
}
