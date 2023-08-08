package com.noi.Post;


import exceptions.PostNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

@Autowired
private PostRepository repository;

    public List<PostDTO> readAll(int pageIndex){
        final int pageSize=10;
        Page<Post> page=repository.findAll(PageRequest.of(pageIndex, pageSize));
        Page<PostDTO> dtosPage = page.map((postEntity)->{
            PostDTO postDto= new PostDTO(
                    postEntity.getId(),
                    postEntity.getTitle(),
                    postEntity.getDescription(),
                    postEntity.getLikes(),
                    postEntity.getImgUrl());

            return postDto;
        });
        return dtosPage.toList();
    }


    @Override
    public Page<Post> findLatestPosts(int pageElements) {
        Page<Post> list = repository.findAllByOrderByCreatedAtDesc(PageRequest.of(0,pageElements));
        return list;
    }


    @Transactional
    public void create(String title, String description, String imgUrl) {
        Post newPost = new Post(title, description, 0, LocalDate.now(),imgUrl);
        repository.save(newPost);
    }

    @Transactional
    public void delete(Long id){
        Post foundentity=repository.findById(id).orElseThrow(()->
                new PostNotFoundException());
        repository.delete(foundentity);
    }
}