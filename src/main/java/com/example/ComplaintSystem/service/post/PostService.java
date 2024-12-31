package com.example.ComplaintSystem.service.post;

import com.example.ComplaintSystem.model.Posts;
import com.example.ComplaintSystem.repository.PostsRepo;
import com.example.ComplaintSystem.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PostService implements IPostService{
    @Autowired
    PostsRepo postsRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public Posts newPost(String message, int userId) {
        Posts posts=new Posts();
        posts.setMessage(message);
        posts.setUser(userRepo.findById(userId).orElseThrow());
        posts.setPostDate(LocalDate.now());
        return postsRepo.save(posts);
    }

    @Override
    public void deletePost(int postId) {
        postsRepo.deleteById(postId);
    }
}
