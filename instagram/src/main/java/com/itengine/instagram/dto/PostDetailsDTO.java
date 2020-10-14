package com.itengine.instagram.dto;

import com.itengine.instagram.model.Comment;
import com.itengine.instagram.model.Post;
import com.itengine.instagram.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class PostDetailsDTO {

    private Long id;
    private String description;
    private String photoPath;
    private LocalDateTime dateTime;
    private int numLikes;
    private UserResponseDTO user;
    private List<CommentDTO> comments;
    private List<LikeUserDTO> likes;
    private boolean liked;

    public PostDetailsDTO(Post post, Long id){
        this.id = post.getId();
        this.description = post.getDescription();
        this.photoPath = post.getPhotoPath();
        this.dateTime = post.getDateTime();
        this.numLikes = post.getLikes().size();
        this.user = new UserResponseDTO(post.getUser());
        this.comments = post.getComments().stream().map(comment -> new CommentDTO(comment)).collect(Collectors.toList());
        Collections.sort(this.comments, new SortByDate());
        this.likes = post.getLikes().stream().map(like -> new LikeUserDTO(like)).collect(Collectors.toList());
        this.liked = post.getLikes().stream().collect(Collectors.toList()).stream().filter(l -> l.getUser().getId() == id).findAny().isPresent();

    }

    static class SortByDate implements Comparator<CommentDTO> {
        @Override
        public int compare(CommentDTO a, CommentDTO b) {
            return a.getDateTime().compareTo(b.getDateTime());
        }
    }
}
