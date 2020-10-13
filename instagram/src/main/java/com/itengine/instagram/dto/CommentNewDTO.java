package com.itengine.instagram.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentNewDTO {

    private String text;
    private Long postId;
}
