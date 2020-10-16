package com.itengine.instagram.dto;

import com.itengine.instagram.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentAdminDTO {

    private Long id;
    private String text;
    private String posted;
    private Long postedId;
    private String commented;
    private Long commentedId;
    private Long postId;
    private String description;
    private String photoPath;

    public CommentAdminDTO(Comment comment){
        this.id = comment.getId();
        this.text = comment.getText();
        this.posted = comment.getPost().getUser().getUsername();
        this.postedId = comment.getPost().getUser().getId();
        this.commented = comment.getUser().getUsername();
        this.commentedId = comment.getUser().getId();
        this.postId = comment.getPost().getId();
        this.description = comment.getPost().getDescription();
        this.photoPath = comment.getPost().getPhoto().getPath();
    }

}
