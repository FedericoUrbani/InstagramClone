package com.noi.User.Service;

import com.noi.User.DTO.UserDTO;
import com.noi.User.Entity.UserEntity;
import com.noi.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

@Autowired
    private UserRepository userRepository;



    public UserDTO getById(Long id) {
        UserEntity foundentity = userRepository.findById(id).orElseThrow(()->
                new RuntimeException()); // UserNotFoundExcpetion

        UserDTO dto = UserDTO.builder()
                .email(foundentity.getEmail())
                .username(foundentity.getUsername())
                .build();

        return dto;
        }

    public void create(String username, String email, String password) {


        UserEntity userEntity = new UserEntity( username,  email,  password, LocalDateTime.now(), true);

         userRepository.save(userEntity);


    }

    public void delete(Long id) {

        UserEntity foundentity=userRepository.findById(id).orElseThrow(()->
                new RuntimeException()); // UserNotFoundExcpetion

            userRepository.delete(foundentity);
    }

    public List<UserEntity> listAll() {
        return userRepository.findAll();
    }
}

