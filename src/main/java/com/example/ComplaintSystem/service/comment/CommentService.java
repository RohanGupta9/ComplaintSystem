package com.example.ComplaintSystem.service.comment;

import com.example.ComplaintSystem.model.Comments;
import com.example.ComplaintSystem.repository.CommentRepo;
import com.example.ComplaintSystem.repository.PostsRepo;
import com.example.ComplaintSystem.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CommentService implements ICommentService{
    @Autowired
    CommentRepo commentRepo;

    @Autowired
    UserRepo userRepo;
    @Autowired
    PostsRepo postRepo;
    @Override
    public void addComment(String message,int userId,int postId) {
        Comments comment=new Comments();
        comment.setMessage(message);
        comment.setCommentDate(LocalDate.now());
        comment.setUser(userRepo.findById(userId).orElseThrow());
        comment.setPost(postRepo.findById(postId).orElseThrow());
        commentRepo.save(comment);
    }

    @Override
    public void deleteComment(int commentId) {
        commentRepo.deleteById(commentId);
    }
}
