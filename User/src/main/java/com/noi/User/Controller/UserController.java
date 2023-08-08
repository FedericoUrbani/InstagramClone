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


@RestController
public class UserController {


    @Autowired
    private UserService userService;



    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    List<UserEntity> all() {
        List<UserEntity> allUsers = userService.listAll();
        return allUsers;
    }

    /*
    @RequestMapping("/all")
    public List<UserDTO> readAll(int pageIndex){
        final int pageSize =10;
        Page<UserEntity> list=userService.findAll(PageRequest.of(pageIndex,pageSize));
        List<UserDTO> listDTO=new ArrayList();

        for (UserEntity ue: list){
            listDTO.add(new UserDTO(
                    ue.getUsername(),
                    ue.getEmail()
            ));
        }
        return listDTO;

    }*/


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getById(@PathVariable long id) {

      return userService.getById(id);
    }

    //Checked
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(
            @RequestParam(value="username") String username,
            @RequestParam(value = "email" )String email,
            @RequestParam(value = "password") String password) {

    userService.create(username, email, password);

    }
}
