package com.noi.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM PostEntity p ORDER BY p.likes DESC")
    Page<Post> findMostLikedPosts(Pageable pageable);

    @Query("SELECT p FROM PostEntity p ORDER BY p.createdAt DESC")
    Page<Post> findLatestPosts(Pageable pageable);

    Page<Post> findAllByOrderByCreatedAtDesc(Pageable page);

    Page<Post> findAllByOrderByLikesDesc(Pageable page);


}