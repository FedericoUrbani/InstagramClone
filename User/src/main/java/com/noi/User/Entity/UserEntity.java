package com.noi.User.Entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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

<<<<<<< Updated upstream
    /** da risolvere. Genera circular Dependecy se importi nel pom entrambe le dependecy.
        User possiede Post
        Post possiede User
        Genera: User -> Post -> User...
        Una soluzione sarebbe referenziare piu che con uno User in se per se solo tramite id.
        il problema è che si perde il mapping di hibernate e la generazione della tabella
        con questo approccio.
        è Difficile stabile una ManyToOne in post perchè dall'altra parte (in user)
        deve essere presente un riferimento alla classe post tramite OneToMany
        per creare la relation.
     */
    /*
    @OneToMany(mappedBy="userEntity",cascade = CascadeType.PERSIST)
    private List<Post> posts;
     */
    public UserEntity(String username, String email, String password, LocalDateTime createdAt, boolean isActive) {
=======
    public UserEntity(String username, String email, String password) {
>>>>>>> Stashed changes
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
