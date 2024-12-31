package com.example.ComplaintSystem.repository;

import com.example.ComplaintSystem.model.PostImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepo extends JpaRepository<PostImages,Integer> {
    List<String> findByPostsId(int postId);
}
