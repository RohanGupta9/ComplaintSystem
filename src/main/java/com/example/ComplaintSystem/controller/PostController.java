package com.example.ComplaintSystem.controller;

import com.example.ComplaintSystem.exception.ResourceNotFound;
import com.example.ComplaintSystem.model.Posts;
import com.example.ComplaintSystem.response.ApiResponse;
import com.example.ComplaintSystem.service.image.ImageService;
import com.example.ComplaintSystem.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/Post")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    ImageService imageService;

    public PostController() {
    }

    @PostMapping("/newPost/{userId}")
    public ResponseEntity<?> addPost(@RequestBody String message, @PathVariable int userId, @RequestParam() MultipartFile file) {
        try {
            Posts post = postService.newPost(message, userId);
            imageService.addImage(post.getId(), file);
            return ResponseEntity.status(HttpStatus.OK).body(post);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse());
        }
    }

    @DeleteMapping("/deletePost/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable int postId) {
        try {
            postService.deletePost(postId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFound e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse());
        }
    }


}
