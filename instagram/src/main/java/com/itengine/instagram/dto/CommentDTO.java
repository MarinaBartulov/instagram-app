package com.itengine.instagram.dto;

import com.itengine.instagram.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {

    private Long id;
    private String text;
    private Long userId;
    private String username;
    private Long postId;
    private LocalDateTime dateTime;

    public CommentDTO(Comment comment){
        this.id = comment.getId();
        this.text = comment.getText();
        this.userId = comment.getUser().getId();
        this.username = comment.getUser().getUsername();
        this.postId = comment.getPost().getId();
        this.dateTime = comment.getDateTime();
    }
}
