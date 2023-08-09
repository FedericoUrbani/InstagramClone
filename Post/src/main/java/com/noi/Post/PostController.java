package com.noi.Post;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.noi.User.Entity.UserEntity;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @GetMapping
    public List<PostDTO> readAll(@RequestParam(value = "pageIndex") Integer index){
        return postService.readAll(index);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
    		@RequestParam(value = "id") Long id,
    		@RequestBody Post post) {
//            @RequestParam(value="title") String title,
//            @RequestParam(value ="description")String description,
//            @RequestParam(value = "imgUrl") String imgUrl) {

        postService.create(id, post);

    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(Long id) {
        postService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO update(@PathVariable("id")Long id, @RequestBody PostDTO postDTO){
        return postService.fullUpdate(id,postDTO);
    }

    @GetMapping("/{id}")
    public PostDTO readById(@PathVariable("id") Long id){
    return postService.findById(id);
    }
    //to-do: delete, update, readByPostId, "create userRelation with method getPostsByUserId"
}
