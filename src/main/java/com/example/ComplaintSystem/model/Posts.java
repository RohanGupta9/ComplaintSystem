package com.example.ComplaintSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String message;

    private int orderId;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users user;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<PostImages> images;

    private LocalDate postDate;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Comments> comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for message
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Getter and Setter for orderId
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    // Getter and Setter for user
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    // Getter and Setter for images
    public List<PostImages> getImages() {
        return images;
    }

    public void setImages(List<PostImages> images) {
        this.images = images;
    }

    // Getter and Setter for postDate
    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    // Getter and Setter for comments
    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}
