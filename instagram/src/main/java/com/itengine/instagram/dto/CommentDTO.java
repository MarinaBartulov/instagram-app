package com.itengine.instagram.dto;

import com.itengine.instagram.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {

    private Long id;
    private String text;
    private Long userID;
    private String username;

    public CommentDTO(Comment comment){
        this.id = comment.getId();
        this.text = comment.getText();
        this.userID = comment.getUser().getId();
        this.username = comment.getUser().getUsername();
    }
}
