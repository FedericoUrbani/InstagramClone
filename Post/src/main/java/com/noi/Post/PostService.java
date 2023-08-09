package com.noi.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.noi.User.Entity.UserEntity;

import java.util.List;

@Service
public interface PostService {

    public List<PostDTO> readAll(int pageIndex);

    public Page<Post> findLatestPosts(int pageElements);

    void create(Long user, Post post);
    
    //public List<PostDTO> getPostFromUser();

    public void delete(Long id);

    public PostDTO fullUpdate (Long id, PostDTO postDTO);

    public PostDTO findById (Long id);
}
