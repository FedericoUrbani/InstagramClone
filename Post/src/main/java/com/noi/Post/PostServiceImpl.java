package com.noi.Post;


import com.noi.User.DTO.UserDTO;
import exceptions.PostNotFoundException;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.noi.User.Entity.UserEntity;

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
                    postEntity.getImgUrl(),
                    postEntity.getUserId());

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
    public void create(Long user, Post post) {
        Post newPost = new Post(user, post.getTitle(), post.getDescription(), post.getImgUrl());
        repository.save(newPost);
    }

    @Transactional
    public void delete(Long id){
        Post foundentity=repository.findById(id).orElseThrow(()->
                new PostNotFoundException());
        repository.delete(foundentity);
    }

    @Transactional
    public PostDTO fullUpdate(Long id, PostDTO postDTO) {

        Post post = repository.findById(id).orElseThrow(()->new PostNotFoundException());
        if(isNotNull(postDTO.getDescription())){
            post.setDescription(postDTO.getDescription());
        }
        if(isNotNull(postDTO.getTitle())){
            post.setTitle(post.getTitle());
        }
        if(isNotNull(postDTO.getImgUrl())){
            post.setImgUrl(postDTO.getImgUrl());
        }
        repository.save(post);
        return postDTO;
    }

    public PostDTO findById(Long id){
        Post postFound=repository.findById(id).orElseThrow(()->new PostNotFoundException());
        PostDTO postDTO = PostDTO.builder()
                .title(postFound.getTitle())
                .description(postFound.getDescription())
                .imgUrl(postFound.getImgUrl())
                .likes(postFound.getLikes())
                .build();
        return postDTO;
    }

    private <T> boolean isNotNull (T value){
        if(value!=null){
            return true;
        }
        return false;
    }
}