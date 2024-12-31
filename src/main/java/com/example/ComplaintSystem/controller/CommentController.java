package com.example.ComplaintSystem.controller;


import com.example.ComplaintSystem.response.ApiResponse;
import com.example.ComplaintSystem.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Comment")
public class CommentController {

    @Autowired
    CommentService commentService;


    @PostMapping("/addComment/{userId}/{postId}")
    public ResponseEntity<?> addComment(@RequestBody String message,int userId,int postId){
        try{
            commentService.addComment(message, userId, postId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse());
        }
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int commentId){
        try{
            commentService.deleteComment(commentId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
