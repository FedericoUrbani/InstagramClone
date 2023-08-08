package com.noi.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    public List<PostDTO> readAll(int pageIndex);

    public Page<Post> findLatestPosts(int pageElements);

    void create(String title, String description, String imgUrl);

    public void delete(Long id);
}
