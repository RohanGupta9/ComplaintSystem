package com.example.ComplaintSystem.repository;

import com.example.ComplaintSystem.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepo extends JpaRepository<Posts,Integer> {
}
