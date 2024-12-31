package com.example.ComplaintSystem.service.post;

import com.example.ComplaintSystem.model.Posts;

public interface IPostService {
    Posts newPost(String message, int orderId);
    void deletePost(int postId);
}
