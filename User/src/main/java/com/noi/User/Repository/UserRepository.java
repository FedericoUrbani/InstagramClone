package com.noi.User.Repository;

import com.noi.User.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Page<UserEntity> findByUsername(String lastname, Pageable pageable);

    Optional<UserEntity> findByEmail(String email);

    Page<UserEntity> findAll(Pageable page);

}