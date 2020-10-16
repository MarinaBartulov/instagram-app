package com.itengine.instagram.service;

import com.itengine.instagram.model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileStorageService {

    Photo storeFile(MultipartFile file) throws IOException;
}
