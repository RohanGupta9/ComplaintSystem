package com.example.ComplaintSystem.service.image;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    List<String> getImages(int postId);
    String addImage(int postId, MultipartFile file);
    void deleteImage(int imageId);
}
