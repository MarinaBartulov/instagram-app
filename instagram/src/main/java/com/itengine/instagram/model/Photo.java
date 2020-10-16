package com.itengine.instagram.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;
    private String mimeType;
    private String extension;

    @OneToOne(mappedBy = "photo")
    private Post post;




}
