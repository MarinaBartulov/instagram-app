package com.itengine.instagram.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Entity
public class Post {

    @Id
    private Long id;
    private String description;
    //private List<String> photos;
    private LocalDateTime dateTime;
    //private HashSet<User> likes;
}
