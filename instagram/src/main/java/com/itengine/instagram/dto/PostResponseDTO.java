package com.itengine.instagram.dto;

import com.itengine.instagram.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDTO {

    private Long id;
    private String description;
    private String photoPath;
    private LocalDateTime dateTime;
    private UserResponseDTO user;
    private int numLikes;

    public PostResponseDTO(Post post){
        this.id = post.getId();
        this.description = post.getDescription();
        this.photoPath = post.getPhotoPath();
        this.dateTime = post.getDateTime();
        this.numLikes = post.getLikes().size();
        this.user = new UserResponseDTO(post.getUser());
    }
}
