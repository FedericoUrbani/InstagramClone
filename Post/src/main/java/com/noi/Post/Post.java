package com.noi.Post;

import com.noi.User.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.noi.User.Entity.UserEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Integer likes;
    
//    @ManyToOne
//    @JoinColumn(name = "user_id")
    private Long userId;
    
    private LocalDate createdAt;
    private String imgUrl;
    /*
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    */

    public Post(Long userId, String title, String description, String imgUrl) {
    	this.userId = userId;
        this.title = title;
        this.description = description;
        this.likes = 0;
        this.createdAt = LocalDate.now();
        this.imgUrl = imgUrl;
    }
}