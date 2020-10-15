package com.itengine.instagram.dto;

import com.itengine.instagram.model.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDTO {

    private Long id;
    private String description;
    private String photoPath;
    private LocalDateTime dateTime;


    public PostDTO(){

    }

    public PostDTO(Post post){
        this.id = post.getId();
        this.description = post.getDescription();
        this.photoPath = post.getPhotoPath();
        this.dateTime = post.getDateTime();
    }



}
