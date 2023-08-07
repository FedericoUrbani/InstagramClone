package com.noi.Post;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    PostRepository repository;

    public List<PostDTO> readAll(int pageIndex){
        final int pageSize=10;
        Page<Post> page=repository.findAll(PageRequest.of(pageIndex, pageSize));
        Page<PostDTO> dtosPage = page.map((postEntity)->{
            UserDTO userDTO= new UserDTO(postEntity.getUserEntity().getId(),
                    postEntity.getUserEntity().getFirstname(),
                    postEntity.getUserEntity().getLastname()
            );
            return new PostDTO(
                    postEntity.getTitle(),
                    postEntity.getDescription(),
                    postEntity.getLikes(),
                    postEntity.getImgUrl()
            );
        });
        return dtosPage.toList();
    }

    @Override
    public Page<Post> findLatestPosts(int pageElements) {
        return null;
    }

    @Override
    public Page<Post> findAllByOrderByCreatedAtDesc(Pageable page) {
        return null;
    }

    @Override
    public Page<Post> findAllByOrderByLikesDesc(Pageable page) {
        return null;
    }

}