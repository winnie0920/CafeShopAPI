package com.cafeshop.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String upload(MultipartFile file);

    void deleteByUrl(String imageUrl);
}