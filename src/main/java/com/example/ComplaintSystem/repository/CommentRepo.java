package com.example.ComplaintSystem.repository;

import com.example.ComplaintSystem.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comments,Integer> {
}
