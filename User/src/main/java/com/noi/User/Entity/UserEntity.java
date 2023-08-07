package com.noi.User.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    private LocalDateTime createdAt;
    private boolean isActive;

    public UserEntity(String username, String email, String password, LocalDateTime createdAt, boolean isActive) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }
}
