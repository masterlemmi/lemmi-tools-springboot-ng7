package com.lemzki.tools.security.model;


import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String googleId;
    private String name;
    private String email;
    private String link;
    private String picture;
    private String gender;
    private String accessToken;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(String name){
        this.name = name;
    }

    public static User ANONYMOUS = new User("Anonymous");
}
