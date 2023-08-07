package com.noi.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    public List<PostDTO> readAll(int pageIndex);

    public Page<Post> findLatestPosts(int pageElements);

    public Page<Post> findAllByOrderByCreatedAtDesc(Pageable page);

    public Page<Post> findAllByOrderByLikesDesc(Pageable page);




}
