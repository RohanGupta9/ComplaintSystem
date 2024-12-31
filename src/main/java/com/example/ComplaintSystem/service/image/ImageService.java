package com.example.ComplaintSystem.service.image;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.example.ComplaintSystem.model.PostImages;
import com.example.ComplaintSystem.repository.ImageRepo;
import com.example.ComplaintSystem.repository.PostsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService implements IImageService{
    @Autowired
    private AmazonS3 client;

    @Value("${app.s3.bucket}")
    private String bucketName;

    @Autowired
    PostsRepo postRepo;

    @Autowired
    ImageRepo imageRepository;
    @Override
    public List<String> getImages(int postId) {
        List<String> allImagesUrl=imageRepository.findByPostsId(postId);
        return allImagesUrl;
    }

    @Override
    public String addImage(int postId, MultipartFile file) {
        String actualFileName=file.getOriginalFilename();
        String fileName= UUID.randomUUID().toString()+actualFileName.substring(actualFileName.lastIndexOf("."));
        ObjectMetadata metaData=new ObjectMetadata();
        metaData.setContentLength(file.getSize());
        try{
            PutObjectResult putObjectResult=client.putObject(new PutObjectRequest(bucketName,fileName,file.getInputStream(),metaData));
            PostImages postImage=new PostImages();
            postImage.setImageUrl(this.preSignedUrl(fileName));
            postImage.setPosts(postRepo.findById(postId).orElseThrow());
            imageRepository.save(postImage);
            return "Image Uploaded";
        } catch (IOException e) {
            return "Image Not Uploaded";
        }
    }

    @Override
    public void deleteImage(int imageId) {
        imageRepository.deleteById(imageId);
    }

    public String preSignedUrl(String fileName) {
        Date expirationDate=new Date();
        long time= expirationDate.getTime();
        int hour=2;
        time +=hour*60*60*1000;
        expirationDate.setTime(time);
        GeneratePresignedUrlRequest generatePresignedUrlRequest= new GeneratePresignedUrlRequest(bucketName,fileName)
                .withMethod(HttpMethod.GET)
                .withExpiration(expirationDate);
        URL url=client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }
}
