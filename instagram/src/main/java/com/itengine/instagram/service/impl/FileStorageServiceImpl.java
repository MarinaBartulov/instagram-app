package com.itengine.instagram.service.impl;

import com.itengine.instagram.model.Photo;
import com.itengine.instagram.repository.PhotoRepository;
import com.itengine.instagram.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Photo storeFile(MultipartFile image) throws IOException {

        String img_ts = image.getOriginalFilename().split("\\.")[0] + "_" + System.currentTimeMillis() + "." + image.getOriginalFilename().split("\\.")[1];
        System.out.println(image.getOriginalFilename());
        System.out.println(img_ts);
        System.out.println(image.getContentType());
        File convertFile = new File("C:\\MarinaB-Praksa\\instagram-app\\instagram-client\\src\\assets\\images\\" + img_ts);
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(image.getBytes());
        fout.close();
        Photo photo = new Photo();
        photo.setPath("assets\\images\\" + img_ts);
        photo.setMimeType(image.getContentType());
        photo.setExtension(image.getOriginalFilename().split("\\.")[1]);
        this.photoRepository.save(photo);
        return photo;
    }
}
