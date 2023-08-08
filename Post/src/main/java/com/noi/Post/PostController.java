package com.noi.Post;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @GetMapping("/all")
    public List<PostDTO> readAll(@RequestParam(value = "pageindex") Integer index){
        return postService.readAll(index);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @RequestParam(value="title") String title,
            @RequestParam(value ="description")String description,
            @RequestParam(value = "imgUrl") String imgUrl) {

        postService.create(title,description,imgUrl);

    }

    //to-do: delete, update, readByPostId, "create userRelation with method getPostsByUserId"
}
