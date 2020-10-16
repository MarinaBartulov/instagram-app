package com.itengine.instagram.service.impl;

import com.itengine.instagram.model.Photo;
import com.itengine.instagram.repository.PhotoRepository;
import com.itengine.instagram.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;


    @Override
    public Photo savePhoto(Photo photo) {
        return this.photoRepository.save(photo);
    }
}
