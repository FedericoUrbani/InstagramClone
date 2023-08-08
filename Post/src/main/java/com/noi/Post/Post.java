package com.noi.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

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
    private LocalDate createdAt;
    private String imgUrl;

    public Post(String title, String description, Integer likes, LocalDate createdAt, String imgUrl) {
        this.title = title;
        this.description = description;
        this.likes = likes;
        this.createdAt = createdAt;
        this.imgUrl = imgUrl;
    }
}