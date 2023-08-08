package com.noi.User.Controller;

import com.noi.User.DTO.UserDTO;
import com.noi.User.Entity.UserEntity;
import com.noi.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    List<UserDTO> all() {
        List<UserEntity> allUsers = userService.listAll();

        List<UserDTO> filteredUsers = allUsers.stream()
                .map(userEntity -> new UserDTO(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getCreatedAt()))
                .collect(Collectors.toList());

        return filteredUsers;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getById(@PathVariable long id) {

        return userService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password) {

        userService.create(username, email, password);

    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(Long id) {
        userService.delete(id);
    }
}
