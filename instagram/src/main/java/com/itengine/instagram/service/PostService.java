package com.itengine.instagram.service;

import com.itengine.instagram.dto.PostDTO;
import com.itengine.instagram.dto.PostDetailsDTO;
import com.itengine.instagram.dto.PostNewDTO;
import com.itengine.instagram.dto.PostUpdateDTO;
import com.itengine.instagram.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {

    Post findById(Long id);
    List<PostDTO> getUserPosts(Long id);
    PostDTO getPost(Long id);
    PostDTO addPost(MultipartFile file, String description) throws IOException;
    PostDTO updatePost(Long id, PostUpdateDTO postUpdateDTO);
    PostDTO deletePost(Long id);
    PostDetailsDTO getPostDetails(Long id);
    List<PostDetailsDTO> getUserPostsFeed();

}
