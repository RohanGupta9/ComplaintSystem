package com.example.ComplaintSystem.service.comment;

public interface ICommentService {
    void addComment(String message,int userId,int postId);
    void deleteComment(int commentId);
}
