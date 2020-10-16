package com.itengine.instagram.model;

import com.itengine.instagram.dto.PostDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Where(clause="deleted=0")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDateTime dateTime;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;
    private boolean deleted;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Like> likes;

    @OneToOne(cascade = CascadeType.ALL)
    private Photo photo;




}
